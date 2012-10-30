package ru.kt15.tsar.hallchat;

import ru.kt15.finomen.neerc.hall.ChatManager;
import ru.kt15.finomen.neerc.hall.TaskManager;
import ru.kt15.finomen.neerc.hall.xmpp.NeercXMPPConnection;

public class ConnectionManager {
	private static NeercXMPPConnection connection = new NeercXMPPConnection();
	private static boolean started = false;
	
	public static void Start() {
		if (!started) {
			connection.Start();
			started = true;
		}
	}
	
	public static ChatManager getChatManager() {
		if (!started) {
			connection.Start();
			started = true;
		}
		return connection;
	}
	
	public static TaskManager getTaskManager() {
		if (!started) {
			connection.Start();
			started = true;
		}
		return connection;
	}
}
