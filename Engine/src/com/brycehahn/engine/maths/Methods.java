package com.brycehahn.engine.maths;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import com.brycehahn.engine.io.Logger;
import com.brycehahn.engine.resources.Tile;

public class Methods {
	/**
	 * This method will take in a String and will measure how long in pixels the string is
	 * then will return the length as an integer. This is useful for drawing text and
	 * positioning it without using hard coded numbers
	 * @param text A String of a word, or a sentence that you wish to find the length of
	 * @param font The font of the string used to allow the method to calculate the size
	 * @return the length of the given String as an integer.
	 */
	public static int getStringWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		return (int)(font.getStringBounds(text, frc).getWidth());
	}
	
	/**
	 * This method will take in a String and will measure how tall in pixels the string is
	 * then will return the height as an integer. This is useful for drawing text and
	 * positioning it without using hard coded numbers
	 * @param text A String of a word, or a sentence that you wish to find the length of
	 * @param font The font of the string used to allow the method to calculate the size
	 * @return the height of the given String as an integer.
	 */
	public static int getStringHeight(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		return (int)(font.getStringBounds(text, frc).getHeight());
	}
	
	/**
	 * This method will take in a x,y coord for the objects position, the current mouses
	 * x,y coord, the width and height of the object, and the buffer room (how much leniency
	 * the test will have with the mouse)
	 * @param x Objects x position on the screen
	 * @param y Objects y position on the screen
	 * @param mouseX Current mouse x position
	 * @param mouseY Current mouse y position
	 * @param width Objects width in pixels
	 * @param height Objects height in pixels
	 * @param buffer pixel leniency between the borders of the object
	 * @return
	 */
	public static boolean isMouseIn(int x, int y, int mouseX, int mouseY, int width, int height, int buffer) {
		x = x * Tile.tileSize;
		
		if (mouseX >= (x - buffer) && mouseX <= (x + width + buffer)) {
			if (mouseY >= (y - buffer) && mouseY <= (y + height + buffer)) {
				
				Logger.info("[" + mouseX + ", " + mouseY + "] is within [" + (x - buffer) + ", " + (y - buffer) + "] and [" + (x + width + buffer) + ", " + (y + height + buffer) + "]");
				return true;
			}
		}
		return false;
	}
}