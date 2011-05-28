package com.beecub.glizer;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.json.JSONException;
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
	    if(player.getName().equalsIgnoreCase("beecub") || player.getName().equalsIgnoreCase("wmchris")) {
	        bChat.broadcastMessage("&2" + player.getName() + "&6 is a glizer developer!");
	    }
	    else {
            String ip = bConnector.getPlayerIPAddress(player);
            
            HashMap<String, String> url_items = new HashMap<String, String>();
            url_items.put("exec", "login");
            url_items.put("ip", "1.1.1.1");
            url_items.put("account", "server");
            url_items.put("username", player.getName());
            url_items.put("userip", ip);
            
            JSONObject result = bConnector.hdl_com(url_items);
            String ok = null;
            try {
                ok = result.getString("response");
            } catch (JSONException e) {
                if(glizer.D) e.printStackTrace();
                bChat.log("Unable to check player!", 2);
            }
            if(ok.equalsIgnoreCase("ok")) {
                if(glizer.D) bChat.log("Player " + player.getName() + " logged into glizer.");
            }
            else {
                if(glizer.D) bChat.log("Player " + player.getName() + " cant be connected into glizer.", 2);
            }
	    }
        
        bChat.sendMessageToPlayer(player, "&2glizer &6is running on this server.");
	}
}

