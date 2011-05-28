package com.beecub.execute;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Friend {

    public static boolean friends(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 0) {
                listFriends(player, "", "list");
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean addfriend(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                friend(player, args[0], "add");
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean removefriend(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                friend(player, args[0], "remove");
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean friend(Player player, String recipient, String doAction) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "friends");
        url_items.put("do", doAction);
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);

        JSONObject result = bConnector.hdl_com(url_items);
        String ok = null;
        try {
            ok = result.getString("result");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return false;
        }
        if(ok.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("[glizer] Note action done.");
            return true;
        }
        else {
            if(glizer.D) bChat.log("[glizer] Note action cant be done", 2);
            return false;
        }
    }
    
    public static String listFriends(Player player, String recipient, String doAction) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "friends");
        url_items.put("do", doAction);
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);

        JSONObject result = bConnector.hdl_com(url_items);
        String ok = null;
        try {
            ok = result.getString("result");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return "error";
        }
        if(ok.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("[glizer] Note action done.");
            return result.toString();
        }
        else {
            if(glizer.D) bChat.log("[glizer] Note action cant be done", 2);
            return "error";
        }
    }
}
