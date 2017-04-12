package com.brycehahn.levelmaker;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import com.brycehahn.levelmaker.controllers.KeyInputListener;
import com.brycehahn.levelmaker.controllers.MouseInpListener;
import com.brycehahn.levelmaker.controllers.MouseMoveListener;
import com.brycehahn.levelmaker.controllers.MouseScrolListener;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Tile;
import com.brycehahn.levelmaker.renders.Render;

/**
 * Main game class, starts all main processes, and holds main game loop
 * @author Bryce
 */
public class Game extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final boolean dev = false;
	
	public static JFrame frame = new JFrame();
	private Graphics g;
	public Image screen;
	public final static Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
	public final static Font fontLarge = new Font(Font.DIALOG, Font.BOLD, 18);
	
	//game scenes
	public Render render;
	
	boolean isRunning = true;
	public static LevelStorage level;
	
	public static Game game;
	
	public Game() {
		initClasses();
		frame.dispose();
		//frame.setUndecorated(true); //windowed borderless
		frame.pack();
		frame.setSize(1080, 1080);
		frame.setPreferredSize(new Dimension(1080, 1080));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setEnabled(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("WorldMaker Pre-Alpha Build 1.00");
		initListeners();
	}
	
	// We will instantly initialize the listeners to the main menu,
	// hence being the first place the user will go to
	public void initListeners() { 
		addKeyListener(new KeyInputListener());
		addMouseListener(new MouseInpListener());
		addMouseMotionListener(new MouseMoveListener());
		addMouseWheelListener(new MouseScrolListener());
	}
	
	public void start() {
		//start the game loop
		isRunning = true;
		new Thread(this).start();
		requestFocus();
		//start sound
		//set frame to visible
		frame.setVisible(true);
	}
	
	private void initClasses() {
		//game scenes
		render = new Render();
		
		level = new LevelStorage();
		
		new Tile(); //loading images
	}
	
	public void stop() {
		isRunning = false;
		System.exit(0);
	}

	public void tick() {
		render.tick();
	}
	
	public void render() {
		g = screen.getGraphics();
		g.setColor(new Color(100, 200, 250));
		g.fillRect(0, 0, 1080, 1080);
		
		render.render(g);
		
		g = getGraphics();
		g.drawImage(screen, 0, 0, 1080, 1080, 0, 0, 1080, 1080, null);
		g.dispose();
	}
	
	@Override
	public void run() {
		screen = createVolatileImage(1080, 1080);
		while(isRunning) {
			
			tick();
			render();
			
			try {
				Thread.sleep(5);
			} catch(Exception ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		game = new Game();
		frame.add(game);
		//start game
		game.start();
	}
}