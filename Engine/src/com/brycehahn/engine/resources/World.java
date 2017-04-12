package com.brycehahn.engine.resources;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class World {
	public List<Chunk> chunks;
	
	public World() {
		chunks = new ArrayList<Chunk>();
		spawnChunks();
	}
	
	private void spawnChunks() {
		for (int i = 0; i < 1; i++) {
			Chunk c = new Chunk();
			Block[][] b = new Block[30][30];
			
			for (int x = 0; x < 30; x++) {
				for (int y = 0; y < 30; y++) {
					b[x][y] = new Block((x * 32), (y * 32), 32, 32, Tile.path);
				}
			}
			
			c.setBlocks(b);
			chunks.add(c);
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < chunks.size(); i++) {
			chunks.get(i).render(g);
		}
	}
	
	public List<Chunk> getChunks() {
		return chunks;
	}
	
	public void setChunks(ArrayList<Chunk> chunks) {
		this.chunks = chunks;
	}
}