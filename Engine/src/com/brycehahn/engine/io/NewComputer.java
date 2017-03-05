package com.brycehahn.engine.io;

import java.io.File;
import java.io.IOException;

import com.brycehahn.engine.main.Game;

public class NewComputer {
	public static String appdata = System.getenv("APPDATA");
	public static int songs = 5;
	public static int effects = 1;
	//set up the initial folders
	public final static String appdataDirectory			= new String(appdata			+ "\\hahn2014Studios");
	public final static String SoundsDirectory 			= new String(appdataDirectory 	+ "\\Sounds");
	public final static String savesDirectory 			= new String(appdataDirectory	+ "\\saves");
	public final static String dumpDirectory			= new String(appdataDirectory	+ "\\Dumps");
	public final static String SoundEffectDirectory		= new String(SoundsDirectory 	+ "\\Effects");
	public final static String settingsFile				= new String(appdataDirectory	+ "\\settings.ini");
	
	public void checkForMissingFiles() {
		if (!checkIfDirExists(appdataDirectory)) {
			new File(appdataDirectory).mkdir();
		}
		//we need to check if the files exist
		if (!checkIfDirExists(SoundsDirectory)) {
			Logger.debug("Sound directory was not found... we need to download everything");
			new File(SoundsDirectory).mkdir();
		}
		if (!checkIfDirExists(SoundEffectDirectory)) {
			Logger.debug("Sound Effect directory was not found... we need to download everything");
			new File(SoundEffectDirectory).mkdir();
		}
		if (!checkIfDirExists(dumpDirectory)) {
			Logger.debug("Dump directory was not found... we need to create it");
			new File(dumpDirectory).mkdir();
		}
		//check if settings file is missing
		if (!new File(settingsFile).exists()) {
			Logger.debug("Settings file is missing. We will recreate it");
			try {
				new File(settingsFile).createNewFile();
				Game.getReferences().needIni = true;
			} catch (IOException e) {
				CrashDumping.DumpCrash(e);
			}
		}
	}
	
	private boolean checkIfDirExists(String dir) {
		File f = new File(dir);
		if (f.exists() && f.isDirectory() && !f.isFile()) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private boolean checkIfFileExists(String file) {
		File f = new File(file);
		if (f.exists() && !f.isDirectory() && f.isFile()) {
			return true;
		}
		return false;
	}
}