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
                bChat.sendMessageToPlayer(player, listFriends(player, player.getName(), "list"));
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
                if(friends(player, args[0], "add")) {
                    bChat.sendMessageToPlayer(player, "Added Friend");
                    return true;
                }
                else {
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean removefriend(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                if(friends(player, args[0], "remove")) {
                    bChat.sendMessageToPlayer(player, "Removed Friend");
                    return true;
                }
                else {
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean friends(Player player, String recipient, String doAction) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "friends");
        url_items.put("do", doAction);
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("name", recipient);

        JSONObject result = bConnector.hdl_com(url_items);
        String check = bConnector.checkResult(result);
        
        if(check.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("Friend action done");
            return true;
        }
        else if(check.equalsIgnoreCase("never connected")) {
            if(glizer.D) bChat.log("Friend action cant be done, player never connected to this server");
            bChat.sendMessageToPlayer(player, "&6Error, Player &e" + recipient + "&6 was never connected to this server");
            return false;
        }
        else if(check.equalsIgnoreCase("not yourself")) {
            if(glizer.D) bChat.log("Friend action cant be done, not to command user himself");
            bChat.sendMessageToPlayer(player, "&6Error, you cant do this to yourself");
            return false;
        }
        return true;
        
        /*
        String ok = null;
        try {
            ok = result.getString("response");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return false;
        }
        if(ok.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("[glizer] Friend action done.");
            return true;
        }
        else {
            if(glizer.D) bChat.log("[glizer] Friend action cant be done", 2);
            return false;
        }
        */
    }
    
    public static String listFriends(Player player, String recipient, String doAction) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "friends");
        url_items.put("do", doAction);
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("name", recipient);
        url_items.put("start", "0");
        url_items.put("limit", "5");

        // {"_size":2,"0":{"username":"beecub","friend":"mxE333xm","added":"1306588609"},"1"
        //:
        //{"username":"beecub","friend":"machtzentrale19","added":"1306589033"}}
        
        
        JSONObject result = bConnector.hdl_com(url_items);
        
        try {
            int length = result.getInt("_size");
            String friends = "&6Your Friends: &e";
            for(int i = 0; i < length; i++) {
                JSONObject result2 = result.getJSONObject(String.valueOf(i));
                friends += result2.getString("friend") + ", ";
            }
            friends = friends.substring(0,  friends.length() - 2);
            return friends;
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return "error";
        }
    }
}
