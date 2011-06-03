package com.beecub.glizer;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.util.bBackupManager;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bConnector;
import com.beecub.util.bWhitelist;


public class glizerPlayerListener extends PlayerListener {
	@SuppressWarnings("unused")
	private final glizer plugin;

	public glizerPlayerListener(glizer instance) {
		plugin = instance;
	}
	
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
	}
	
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Boolean kick = true;
        
        //String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "login");
        url_items.put("ip", "1.1.1.1");
        url_items.put("account", "server");
        url_items.put("username", player.getName());
        //url_items.put("userip", ip);
        
        JSONObject result = bConnector.hdl_com(url_items);
        
        
        // check whitelist
        if(bConfigManager.usewhitelist) {
            if(bWhitelist.checkWhiteList(player.getName())) {
                kick = false;
            }
            else {
                kick = true;
            }
        }
        
        // check ban
        boolean ok = true;
        try {
            ok = result.getBoolean("banned");
            if(!ok) {
                if(glizer.D) bChat.log("Player " + player.getName() + " logged into glizer.");
                kick = false;
            }
            else {
                bChat.log("Player " + player.getName() + " is banned from this server. Kick", 2);
                kick = true;
            }
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            bChat.log("Unable to check player " + player.getName() + "!", 2);
            if(!bBackupManager.checkBanList(player.getName())) {
                kick = false;
            }
        }
        // check developer
        try {
            int check = result.getInt("developer");
            if(check == 1) {
                bChat.broadcastMessage("&6Player &2" + player.getName() + "&6 is a &2glizer &6developer");
                kick = false;
            }
        }
        catch(Exception e) {
            //e.printStackTrace();
        }
        
        if(bWhitelist.checkWhiteList(player.getName())) {
            kick = false;
        }
        
        if(kick) {
            Result rresult;
            rresult = Result.KICK_BANNED;
            event.setKickMessage("You are banned from this server. Check glizer.net");
            event.setResult(rresult);
            //player.kickPlayer("You are banned from this server. Check glizer.net");
        }
        else {
            Result rresult;
            rresult = Result.ALLOWED;
            event.setResult(rresult);
        }
    }
	
    
    public void onPlayerJoin(PlayerJoinEvent event) {
	    Player player = event.getPlayer();
	    
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "ip");
        url_items.put("ip", "1.1.1.1");
        url_items.put("account", "server");
        url_items.put("username", player.getName());
        url_items.put("userip", ip);
        
        @SuppressWarnings("unused")
        JSONObject result = bConnector.hdl_com(url_items);
        
        bChat.sendMessageToPlayer(player, "&6This server is running &2glizer - the Minecraft Globalizer&6");
	}
	
}

