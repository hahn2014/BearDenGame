package com.brycehahn.engine.io;

import java.io.File;
import java.io.IOException;

import com.brycehahn.engine.main.Game;

public class NewComputer {
	public static int songs = 5;
	public static int effects = 1;
	//set up the initial folders
	public final static String settingsFile	= new String("./settings.ini");
	
	public void checkForMissingFiles() {
		//check if settings file is missing
		if (!new File(settingsFile).exists()) {
			Logger.debug("Settings file is missing. We will recreate it");
			try {
				new File(settingsFile).createNewFile();
				Game.r.needIni = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}