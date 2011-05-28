package com.beecub.glizer;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.json.JSONObject;

import com.beecub.util.bChat;
import com.beecub.util.bConnector;


public class glizerPlayerListener extends PlayerListener {
	@SuppressWarnings("unused")
	private final glizer plugin;

	public glizerPlayerListener(glizer instance) {
		plugin = instance;
	}
	
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {

	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
	    Player player = event.getPlayer();
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "login");
        url_items.put("ip", "1.1.1.1");
        url_items.put("account", "server");
        url_items.put("username", player.getName());
        url_items.put("userip", ip);
        
        JSONObject result = bConnector.hdl_com(url_items);
        bChat.log(result.toString());
        if(result.toString() == "ok") {
            bChat.log("&6 Connected to glizer-server.");
        }
        else {
            bChat.log("&6 Cant establish a connection to glizer-server!", 2);
        }
	}
}

