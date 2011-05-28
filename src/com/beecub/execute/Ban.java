package com.beecub.execute;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;
import com.beecub.util.bBackupManager;
import com.beecub.util.bChat;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Ban {
    
    public static boolean ban(String command, Player player, String[] args) {
        bChat.sendMessageToPlayer(player, "&6Use /globalban, /tempban and /localban to ban players!");
        return true;
    }
    
    public static boolean globalban(String command, Player player, String[] args) {
        if(args.length >= 2) {
            String message = "";
            String recipient = args[0];
            for(int i = 1; i < args.length; i++) {
                message += args[i] + " ";
            }
            if(message != null && message != "") {
                addNote(player, recipient, "0", "1", "0", message, "-100", "0");
                bBackupManager.addBanBackup(recipient);
            } else {
                bChat.sendMessageToPlayer(player, "&6Wrong command usage. Type &f /glizer help&6.");
            }
        }
        return true;
    }
    
    public static boolean localBan(String command, Player player, String[] args) {
        if(args.length >= 2) {
            String message = "";
            String recipient = args[0];
            for(int i = 1; i < args.length; i++) {
                message += args[i] + " ";
            }
            if(message != null && message != "") {
                addNote(player, recipient, "0", "0", "0", message, "-100", "0");
                bBackupManager.addBanBackup(recipient);
            }
        }
        return true;
    }
    
    public static boolean tempban(String command, Player player, String[] args) {
        if(args.length >= 2) {
            String message = "";
            String recipient = args[0];
            String time = args[1];
            if(args.length >= 3) {
                for(int i = 2; i < args.length; i++) {
                    message += args[i] + " ";
                }
            }
            if(message != null && message != "") {
                addNote(player, recipient, "0", "0", "0", message, "-100", time);
            }
        }
        return true;
    }
    
    public static boolean unban(String command, Player player, String[] args) {
        if(args.length == 1) {
            String recipient = args[0];
            addNote(player, recipient, "0", "0", "0", "Unban", "100", "0");
            bBackupManager.removeBanBackup(recipient);
        }
        return true;
    }
    
    public static boolean addbanwhitelist(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bBackupManager.addbanwhitelist(name)) {
                    bChat.sendMessageToPlayer(player, "&6Completed.");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "&6Requested player is already on ban whitelist.");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean removebanwhitelist(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bBackupManager.removebanwhitelist(name)) {
                    bChat.sendMessageToPlayer(player, "Completed.");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "Requested player is not on ban whitelist!");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    
    public static boolean addNote(Player player, String recipient, String fhide, String fglobal, String fprivate, String message, String reputation, String timelimit) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "notes");
        url_items.put("do", "add");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);
        url_items.put("fhide", fhide);
        url_items.put("fglobal", fglobal);
        url_items.put("fprivate", fprivate);
        url_items.put("message", message);
        url_items.put("reputation", reputation);
        url_items.put("timelimit", timelimit);
        
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
    
    public static String getNote(Player player, String recipient, String fhide, String fglobal, String fprivate, String message, String reputation, String timelimit) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "notes");
        url_items.put("do", "add");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);
        url_items.put("fhide", fhide);
        url_items.put("fglobal", fglobal);
        url_items.put("fprivate", fprivate);
        url_items.put("message", message);
        url_items.put("reputation", reputation);
        url_items.put("timelimit", timelimit);
        
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
