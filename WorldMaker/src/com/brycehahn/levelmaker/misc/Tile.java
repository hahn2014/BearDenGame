package com.brycehahn.levelmaker.misc;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Tile {
	public static final int tileSize 		= 32;
	public static final int dropSize 		= 8;
	public static final int invCellSize 	= 25;
	public static final int invWidth 		= 9;
	public static final int invHeight 		= 3;
	public static final int invCellSpace 	= 4;
	public static final int invBorderSpace 	= 4;
	public static final int invItemBorder 	= 2;
	public static final int chestLength 	= 9;
	public static final int chestHeight 	= 5;
	public static final int invCraftLength 	= 2;
	public static final int invCraftHeight 	= 2;
	public static final int craftingLength 	= 3;
	public static final int craftingHeight 	= 3;
	
	// blocks								//{x, y} on texture pack
	public static final int[] blank 		= {-1,-1};
	public static final int[] grass			= {0, 0};
	public static final int[] path	 		= {1, 0};
	public static final int[] water	 		= {2, 0};
	public static final int[] wood	 		= {3, 0};
	
	
	//colliders
	public static final int[] wallCollision	= {0, 1};
	public static final int[] deathCollision = {1, 1};
	//animations
	public static int[] player = {10, 13};
	
	//misc menu images
	public static BufferedImage empty;
	public static BufferedImage logo;
	
	public static BufferedImage texture;
	
	//items
	
	
	public Tile() {
		try {
			texture			= ImageIO.read(new FileInputStream("res/texture_default.png"));
			empty 			= ImageIO.read(new FileInputStream("res/null.png"));
		//	logo			= ImageIO.read(Tile.class.getResourceAsStream("logo.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex + "\nWe could not load the resources...\nTry re-intralling the program",
					"Whoa There!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}