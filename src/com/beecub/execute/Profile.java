package com.beecub.execute;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;

public class Profile {

    public static boolean profile(String command, Player player, String[] args) {
        if(args.length == 1) {
            String recipient = args[0];
            showProfile(player, recipient);
            return true;
        }
        bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
        bChat.sendMessageToPlayer(player, "&6/profile&e [playername]");
        return true;
    }
    
    public static boolean editprofile(String command, Player player, String[] args) {
        if(args.length == 1) {
            String recipient = args[0];
            editProfile(player, recipient);
            return true;
        }
        bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
        bChat.sendMessageToPlayer(player, "&6/editprofile&e [playername]");
        return true;
    }
    
    public static boolean clearprofile(String command, Player player, String[] args) {
        bChat.sendMessageToPlayer(player, "&6Not available in this version of glizer");
        return true;
    }
    
    public static String editProfile(Player player, String recipient) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "profile");
        url_items.put("do", "edit");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("lastip", ip);
        url_items.put("name", "beecub");
        url_items.put("realname", "beecub");
        url_items.put("email", "noreply@beecub.com");
        url_items.put("age", "21");
        url_items.put("status", "developing glizer");
        url_items.put("mehr", "");
        
        JSONObject result = bConnector.hdl_com(url_items);
        bChat.log(result.toString());
        
        //{"name":"beecub","realname":"","email":"","age":"0","status":"","mehr":"","lastip":"91.11.230.223",
        //"reputation":"-1","userreputation":"0","lastserver":"1","lastseen":"1306603729","developer":"0"}
        
        try {
            if(result.getString("response").equalsIgnoreCase("ok")) {
                return "Ok";
            }
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
        }        
        return "error";
    }
    
    public static String showProfile(Player player, String recipient) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "profile");
        url_items.put("do", "list");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("name", recipient);
        
        JSONObject result = bConnector.hdl_com(url_items);
        
        //{"name":"beecub","realname":"","email":"","age":"0","status":"","mehr":"","lastip":"91.11.230.223",
        //"reputation":"-1","userreputation":"0","lastserver":"1","lastseen":"1306603729","developer":"0"}
        
        try {
            bChat.sendMessageToPlayer(player, "&6 --- Profile --- ");
            bChat.sendMessageToPlayer(player, "&6Name: &e" + result.getString("name"));
            bChat.sendMessageToPlayer(player, "&6Realname: &e" + result.getString("realname"));
            bChat.sendMessageToPlayer(player, "&6Age: &e" + result.getString("age"));
            bChat.sendMessageToPlayer(player, "&6Last Server: &e" + result.getString("lastserver"));
            bChat.sendMessageToPlayer(player, "&6Status: &e" + result.getString("status"));
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
        }
        
        return "Ok";
    }
}
