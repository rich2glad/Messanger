package org.richerd.messanger.database;

import java.util.HashMap;
import java.util.Map;

import org.richerd.messanger.model.Message;
import org.richerd.messanger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message>  messages= new HashMap<>();
	private static Map<String, Profile>  profiles= new HashMap<>();
	
	public DatabaseClass() {
		
	}
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
	
}
