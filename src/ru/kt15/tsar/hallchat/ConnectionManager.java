package ru.kt15.tsar.hallchat;

import ru.kt15.finomen.neerc.hall.ChatManager;
import ru.kt15.finomen.neerc.hall.TaskManager;
import ru.kt15.finomen.neerc.hall.xmpp.NeercXMPPConnection;

public class ConnectionManager {
	private static NeercXMPPConnection connection = new NeercXMPPConnection();
	
	public static ChatManager getChatManager() {
		return connection;
	}
	
	public static TaskManager getTaskManager() {
		return connection;
	}
}
