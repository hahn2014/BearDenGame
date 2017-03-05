package com.brycehahn.engine.main;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.brycehahn.engine.io.CrashDumping;
import com.brycehahn.engine.io.Logger;
import com.brycehahn.engine.io.NewComputer;
import com.brycehahn.engine.io.SettingsLoader;
import com.brycehahn.engine.maths.FPS;
import com.brycehahn.engine.menus.MainMenuRender;

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
	MainMenuRender mainmenu;
	
	private static Game game;
	
	public Game() {
		initClasses();
		frame.dispose();
		//frame.setUndecorated(true); //windowed borderless
		frame.pack();
		frame.setSize(r.SIZE);
		frame.setPreferredSize(r.SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setEnabled(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		setFrameSize(r.fullscreen);
		frame.setTitle(r.NAME + " " + r.BUILD + " " + r.VERSION);
		setPreferredSize(r.SIZE);
		initListeners();
	}
	
	public void initListeners() {
//		addKeyListener(new KeyInputListener());
//		addMouseListener(new MouseInpListener());
//		addMouseMotionListener(new MouseMotListener());
//		addMouseWheelListener(new MouseWhlListener());
	}
	
	public void start() {
		//start the game loop
		r.isRunning = true;
		if (!r.development) {
			r.MENU = 0;
		} else {
			r.MENU = 6;
			//level.generateLevel();
		}
		new Thread(this).start();
		requestFocus();
		//start sound
		//set frame to visible
		frame.setVisible(true);

		Logger.info("Finished Settings Things Up");
		Logger.info("now reading settings");
		settingsloader.loadSettings(NewComputer.settingsFile);
	}
	
	private void initClasses() {
		nc = new NewComputer();
		nc.checkForMissingFiles();
		fps = new FPS();
		fps.alterFPSCap(60.0);
		
		//game scenes
		mainmenu = new MainMenuRender();
		
		//new Tile(); //loading images
		settingsloader = new SettingsLoader();
	}
	
	public void stop() {
		r.isRunning = false;
		System.exit(0);
	}

	public void tick() {
		if (!r.development) { //menu is in development, so go to main menu
			switch (r.MENU) {
				case 0:
					mainmenu.tick();
					break;
				case 1:
					
					break;
				case 2:
					
					break;
			}
		} else {
			//since menu is in development, jump straight to game for non-devs
		}
		r.mosX = MouseInfo.getPointerInfo().getLocation().x;
		r.mosY = MouseInfo.getPointerInfo().getLocation().y;
	}
	
	public void render() {
		if (r.dragging != true) {
			g = screen.getGraphics();
			g.setColor(r.emptyBG);
			g.fillRect(0, 0, r.PIXEL.width, r.PIXEL.height);
			if (!r.development) {
				switch (r.MENU) {
					case 0:
						mainmenu.render(g);
						break;
					case 1:
						
						break;
					case 2:
						
						break;
				}
			} else {
				//same thing, just render the game if not developer
				
				g.setFont(r.font2);
				g.setColor(r.color2);
				g.drawString(r.BUILD + " " + r.VERSION, 311, 8);
			}
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
				CrashDumping.DumpCrash(ex);
				System.exit(0);
			}
		}
	}
	
	public static void setFrameSize(boolean fullScreen) {
		if (fullScreen) {
			//set it to fullscreen (no duhhh)
			r.SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
			frame.setSize(r.SIZE);
			frame.getContentPane().setPreferredSize(r.SIZE);
			frame.setLocationRelativeTo(null);
		} else {
			//set it back to original size
			r.SIZE = new Dimension(1200, 700);
			frame.setSize(r.SIZE);
			frame.getContentPane().setPreferredSize(r.SIZE);
			frame.setLocation(r.framePos);
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