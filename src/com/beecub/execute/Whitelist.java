package com.beecub.execute;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.command.bPermissions;
import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;
import com.beecub.util.bWhitelist;

public class Whitelist {
    
    public static boolean whitelistAdd(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String recipient = args[0];
                if(whitelist(player, recipient, "add")) {
                    if(bWhitelist.addWhiteList(recipient)) {
                        bChat.sendMessageToPlayer(player, "&6Added to Whitelist");
                        return true;
                    }
                    return true;
                }
                bChat.sendMessageToPlayer(player, "&6Cannot add this player to whitelist");
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/note&e [playername]");
            return true;
        }
        return true;
    }
    
    public static boolean whitelistRemove(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String recipient = args[0];
                if(whitelist(player, recipient, "remove")) {
                    if(bWhitelist.removeWhiteList(recipient)) {
                        bChat.sendMessageToPlayer(player, "&6Removed from Whitelist");
                        return true;
                    }
                    return true;
                }
                bChat.sendMessageToPlayer(player, "&6Cannot remove this player from whitelist");
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/note&e [playername]");
            return true;
        }
        return true;
    }
    
    public static boolean whitelist(Player player, String recipient, String action) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "whitelist");
        url_items.put("do", action);
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("name", recipient);
        
        JSONObject result = bConnector.hdl_com(url_items);
        String ok = null;
        try {
            ok = result.getString("response");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return false;
        }
        if(ok.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("Whitelist action done.");
            return true;
        }
        else {
            if(glizer.D) bChat.log("Whitelist action cant be done", 2);
            return false;
        }
    }
}
