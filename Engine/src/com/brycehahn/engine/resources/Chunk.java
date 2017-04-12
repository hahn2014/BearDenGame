package com.brycehahn.engine.resources;

import java.awt.Graphics;

public class Chunk {
	
	private int[][] zone;
	
	private Block[][] blocks;
	
	public Chunk() {
		blocks = new Block[20][20];
	}
	
	public void render(Graphics g) {
		for (int xx = 0; xx < blocks.length; xx++) {
			for (int yy = 0; yy < blocks[0].length; yy++) {
				blocks[xx][yy].render(g);;
			}
		}
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	public int[][] getZone() {
		return zone;
	}
	public Block[][] getBlocks() {
		return blocks;
	}
	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}
}