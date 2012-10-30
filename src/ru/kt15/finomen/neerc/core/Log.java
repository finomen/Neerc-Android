package ru.kt15.finomen.neerc.core;

import java.util.Date;

public abstract class Log implements ILog{
	private static ILog logImpl = new SystemLog();
	
	public static void writeError(String s, Exception e) {
		writeError(s + ":" + e.getLocalizedMessage());
	}
	
	public static void writeError(String s) {
		logImpl.writeError(new Date(), s);
	}
	
	public static void writeInfo(String s) {
		logImpl.writeInfo(new Date(), s);
	}
	
	public static void writeDebug(String s) {
		logImpl.writeDebug(new Date(), s);
	}
	
	public static void setImpl(ILog impl) {
		logImpl = impl;
	}
		
	private static class SystemLog extends Log {

		public void writeError(Date time, String s) {
			android.util.Log.e("HallChat", "[ " + time.toString() + " ] ERROR: " + s);
		}

		public void writeInfo(Date time, String s) {
			android.util.Log.i("HallChat", "[ " + time.toString() + " ] ERROR: " + s);
		}

		public void writeDebug(Date time, String s) {
			android.util.Log.d("HallChat", "[ " + time.toString() + " ] ERROR: " + s);
		}
		
	}
}
