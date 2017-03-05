package com.brycehahn.engine.io;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashDumping {
	public static void DumpCrash(Exception exception) {
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
			Date date = new Date();
			
			PrintWriter writer = new PrintWriter(NewComputer.dumpDirectory + "\\" + dateFormat.format(date) + ".log", "UTF-8");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			writer.print(sw.toString());
			writer.close();
			
			
			Logger.error("Sorry! and unexpected error occured. " + exception + " A log file has been created as " + dateFormat.format(date) + ".log");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}