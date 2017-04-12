package com.brycehahn.levelmaker.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
	public static String fileDir = "./";
	public static String[] worlds;
	
	public static void Save(String filename) {
		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter(fileDir + filename + ".wld"));
			String[][] tosave = getData();
			
			for (int i = 0; i < tosave.length; i++) {
				for (int j = 0; j < tosave[0].length; j++) {
					outputWriter.write(tosave[i][j]);
					outputWriter.newLine();
				}
			}
			
			outputWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully exported world to " + fileDir + filename + ".wld");
	}
	
	public static void Load(String filename) {
		//clear the level
		LevelStorage.clearLevel();
		
		//load the level
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new FileReader(fileDir + filename));
			
			String line = "";
			while ((line = inputReader.readLine()) != null) {
				
				String arrayCoords = line.substring(1, line.indexOf("]"));
				String xy = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
				String id = line.substring(line.indexOf("{") + 2, line.indexOf("}") - 1);
				
				int x = Integer.parseInt(arrayCoords.substring(0, arrayCoords.indexOf(",")));
				int y = Integer.parseInt(arrayCoords.substring(arrayCoords.indexOf(",") + 1, arrayCoords.length()));
				
				int xx = Integer.parseInt(xy.substring(0, xy.indexOf(",")));
				int yy = Integer.parseInt(xy.substring(xy.indexOf(",") + 1, xy.length()));
				
				int idX = Integer.parseInt(id.substring(0, id.indexOf(",")));
				int idY = Integer.parseInt(id.substring(id.indexOf(",") + 1, id.length()));
				int[] ID = new int[2]; ID[0] = idX; ID[1] = idY;
				
				LevelStorage.setBlock(x, y, xx, yy, ID);
			}
			LevelStorage.updateRenderBlocks(0, 0);
			
			inputReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateWorldList() {
		
		File folder = new File("./");
		File[] listOfFiles = folder.listFiles();
		List<File> properFiles = new ArrayList<File>();

		//go through the list of files and dirs
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) { //make sure the current name is a file, not a dir
				if (listOfFiles[i].getName().endsWith(".wld")) { //make sure the current file is a proper world file for the engine
					properFiles.add(listOfFiles[i]); //add to list of worlds
				}
			}
		}
		worlds = new String[properFiles.size()];
		
		for (int i = 0; i < properFiles.size(); i++) {
			worlds[i] = properFiles.get(i).getName();
		}
	}
	
	private static String[][] getData() {
		String[][] blocks = new String[LevelStorage.blocks.length][LevelStorage.blocks[0].length];
		for (int x = 0; x < LevelStorage.blocks.length; x++) {
			for (int y = 0; y < LevelStorage.blocks[0].length; y++) {
				blocks[x][y] = new String("[" + x + "," + y + "]("
						+ (int)LevelStorage.blocks[x][y].getX() + "," + (int)LevelStorage.blocks[x][y].getY() + "){"
						+ LevelStorage.blocks[x][y].getIDforSave() + "}");
			}
		}
	    return blocks;
	}
}