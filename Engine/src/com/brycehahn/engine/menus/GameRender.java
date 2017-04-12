package com.brycehahn.engine.menus;

import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.resources.World;

public class GameRender {
	References r = Game.r;
	World world;
	
	public GameRender() {
		world = new World();
	}
	
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics g) {
		world.render(g);
	}
}