package com.brycehahn.engine.main;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import com.brycehahn.engine.io.Logger;
import com.brycehahn.engine.io.NewComputer;
import com.brycehahn.engine.io.SettingsLoader;
import com.brycehahn.engine.maths.FPS;
import com.brycehahn.engine.menus.MainMenuRender;
import com.brycehahn.engine.menus.NewWorldRender;
import com.brycehahn.engine.menus.SettingsRender;
import com.brycehahn.engine.menus.controllers.KeyInputListener;
import com.brycehahn.engine.menus.controllers.MouseMoveListener;
import com.brycehahn.engine.menus.controllers.MouseScrolListener;
import com.brycehahn.engine.menus.game.ScreenRender;
import com.brycehahn.engine.player.Player;
import com.brycehahn.engine.resources.Tile;

/**
 * Main game class, starts all main processes, and holds main game loop
 * @author Bryce
 */
public class Game extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static References r;
	public static JFrame frame = new JFrame();
	private Graphics g;
	public Image screen;
	public static FPS fps;
	
	public static NewComputer nc;
	public static SettingsLoader settingsloader;
	
	
	//game scenes
	public MainMenuRender mainmenu;
	public SettingsRender settings;
	public NewWorldRender newworld;
	public ScreenRender ingame;
	
	public static Game game;
	public static Player player;
	
	public Game() {
		initClasses();
		frame.dispose();
		frame.pack();
		frame.setSize(1080, 1080);
		frame.setPreferredSize(new Dimension(1080, 1080));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setEnabled(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(r.NAME + " " + r.BUILDINITIALS + r.VERSION);
		initListeners();
	}
	
	// We will instantly initialize the listeners to the main menu,
	// hence being the first place the user will go to
	public void initListeners() { 
		addKeyListener(new KeyInputListener());
//		addMouseListener(new MouseInpListener());
		addMouseMotionListener(new MouseMoveListener());
		addMouseWheelListener(new MouseScrolListener());
	}
	
	public void start() {
		//start the game loop
		r.isRunning = true;
		new Thread(this).start();
		requestFocus();
		//start sound
		//set frame to visible
		frame.setVisible(true);

		Logger.info("Finished Settings Things Up");
		Logger.info("now reading settings");
	}
	
	private void initClasses() {
		nc = new NewComputer();
		nc.checkForMissingFiles();
		fps = new FPS();
		fps.alterFPSCap(60.0);
		
		//game scenes
		mainmenu = new MainMenuRender();
		settings = new SettingsRender();
		newworld = new NewWorldRender();
		ingame = new ScreenRender();
		
		
		player = new Player(100, 100);
		
		new Tile(); //loading images
		settingsloader = new SettingsLoader();
	}
	
	public void stop() {
		r.isRunning = false;
		System.exit(0);
	}

	public void tick() {
		switch (r.MENU) {
			case 0:
				mainmenu.tick();
				break;
			case 1:
				settings.tick();
				break;
			case 2:
				newworld.tick();
				break;
				
			case 5:
				ingame.tick();
				break;
		}
	}
	
	public void render() {
		g = screen.getGraphics();
		g.setColor(r.emptyBG);
		g.fillRect(0, 0, r.PIXEL.width, r.PIXEL.height);
		if (!r.development) {
			switch (r.MENU) {
				case 0:
					mainmenu.render(g);
					break;
				case 1:
					settings.render(g);
					break;
				case 2:
					newworld.render(g);
					break;
					
				case 5:
					ingame.render(g);
					break;
			}
			g.setFont(r.font2);
			g.setColor(r.color2);
			g.drawString(r.BUILD + " " + r.VERSION, 311, 8);
			FPS.render(g);
			
			g = getGraphics();
			g.drawImage(screen, 0, 0, r.SIZE.width, r.SIZE.height, 0, 0, r.PIXEL.width, r.PIXEL.height, null);
			g.dispose();
		}
	}
	
	@Override
	public void run() {
		screen = createVolatileImage(r.PIXEL.width, r.PIXEL.height);
		while(r.isRunning) {
			
			tick();
			render();
			fps.getFPS();
			
			try {
				Thread.sleep(5);
			} catch(Exception ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		r = new References();
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-d")) {
				Logger.setDebug(true);
				Logger.debug("Debug true");
			}
			if (arg.equalsIgnoreCase("-development")) {
				Logger.debug("Running game as developer");
				Logger.setDebug(true);
				r.development = true;
			}
		}
		
		game = new Game();
		frame.add(game);
		//start game
		game.start();
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static References getReferences() {
		return r;
	}
}