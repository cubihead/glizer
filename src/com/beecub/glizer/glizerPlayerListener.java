package com.beecub.glizer;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;


public class glizerPlayerListener extends PlayerListener {
	@SuppressWarnings("unused")
	private final glizer plugin;

	public glizerPlayerListener(glizer instance) {
		plugin = instance;
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
	    
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
	    
	}
	
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
	    
	}
	
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        
    }
}

