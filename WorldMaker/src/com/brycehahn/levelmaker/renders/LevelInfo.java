package com.brycehahn.levelmaker.renders;

public class LevelInfo {
	String filename = "";
	String savetime = "";
	double filesize = 0;
	
	public LevelInfo() {
		
	}
	public void setName(String name) {
		filename = name;
	}
	public String getName() {
		return filename;
	}
	public void setSaveTime(String time) {
		savetime = time;
	}
	public String getSaveTime() {
		return savetime;
	}
	public void setSize(long size) {
		filesize = (size / 1000);
	}
	public String getSize() {
		if (filesize < 1000) {
			return Double.toString(filesize) + " KB";
		} else {
			return Double.toString(filesize / 1000) + " MB";
		}
	}
}