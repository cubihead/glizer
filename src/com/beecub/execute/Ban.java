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
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    if(addNote(player, recipient, "0", "1", "0", message, "-100", "0", "0")) {
                        bBackupManager.addBanBackup(recipient);
                        bChat.broadcastMessage("&6" + player.getName() + " banned player: &e" + recipient);
                        Player result = null;
                        result = glizer.plugin.getServer().getPlayer(recipient);
                        if(result != null) {
                            result.kickPlayer("You were banned from this server. Check glizer.net");
                        }
                        return true;
                    }
                } else {
                    bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
                    bChat.sendMessageToPlayer(player, "&6/globalban&e [playername] [message]");
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/globalban&e [playername] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean localBan(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    if(addNote(player, recipient, "0", "0", "0", message, "-200", "0", "1")) {
                        bBackupManager.addBanBackup(recipient);
                        bChat.broadcastMessage("&6" + player.getName() + " banned player: &e" + recipient);
                        Player result = null;
                        result = glizer.plugin.getServer().getPlayer(recipient);
                        if(result != null) {
                            result.kickPlayer("You are banned from this server. Check glizer.net");
                        }
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/localban&e [playername] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean tempban(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
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
                    if(addNote(player, recipient, "0", "0", "0", message, "-100", time, "0")) {
                        bChat.broadcastMessage("&6" + player.getName() + " banned player: &e" + recipient);
                        Player result = null;
                        result = glizer.plugin.getServer().getPlayer(recipient);
                        if(result != null) {
                            result.kickPlayer("You were banned from this server. Check glizer.net");
                        }
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/tempban&e [playername] [seconds] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean unban(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                boolean global = true;
                String message = "";
                String recipient = args[0];
                String rep = getRep(player, recipient);
                if(rep.equalsIgnoreCase("local") || rep.equalsIgnoreCase("not")) {
                    global = false;
                }
                
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    String glob = "0";
                    if(global) glob = "1";
                    if(addNote(player, recipient, "0", glob, "0", message, "100", "0", "0")) {
                        bBackupManager.addBanBackup(recipient);
                        bChat.broadcastMessage("&6" + player.getName() + " unbanned player: &e" + recipient);
                        return true;
                    }
                } else {
                    bChat.sendMessageToPlayer(player, "&6Wrong command usage. Type &f /glizer help&6.");
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/unban&e [playername] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean addbanwhitelist(String command, Player player, String[] args) {        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bBackupManager.addBanWhiteList(name)) {
                    bChat.sendMessageToPlayer(player, "&6Completed");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "&6Requested player is already on ban whitelist");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/addbanwhitelist&e [playername]");
            return true;
        }
        return true;
    }
    
    public static boolean removebanwhitelist(String command, Player player, String[] args) {        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bBackupManager.removeBanWhiteList(name)) {
                    bChat.sendMessageToPlayer(player, "&6Completed");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "&6Requested player is not on ban whitelist");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/removebanwhitelist&e [playername]");
            return true;
        }
        return true;
    }
    
    
    public static boolean addNote(Player player, String recipient, String fhide, String fglobal, String fprivate, String message, String reputation, String timelimit, String ban) {
        
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
        url_items.put("ban", ban);
        
        JSONObject result = bConnector.hdl_com(url_items);
        String ok = null;
        try {
            ok = result.getString("response");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return false;
        }
        if(ok.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("Note add action done.");
            return true;
        }
        else if(ok.equalsIgnoreCase("nochange")) {
            if(glizer.D) bChat.log("Note action nochange reputation");
            return true;
        }
        else {
            if(glizer.D) bChat.log("Note add action cant be done", 2);
            return false;
        }
    }
    
    public static String getNote(Player player, String recipient, String type, String local, String start, String limit) {
        int page = Integer.valueOf(start);
        page = page * 5;
        start = String.valueOf(page);
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "notes");
        url_items.put("do", "list");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);
        url_items.put("type", type);
        url_items.put("local", local);
        url_items.put("start", start);
        url_items.put("limit", limit);
        
        JSONObject result = bConnector.hdl_com(url_items);
        
        try {
            int length = result.getInt("_size");
            //String friends = "&6Your Notes: &e";
            for(int i = 0; i < length; i++) {
                JSONObject result2 = result.getJSONObject(String.valueOf(i));
                String note = result2.getString("message");
                if(!note.equalsIgnoreCase("")) {
                    //friends += result2.getString("message") + ", ";
                    bChat.sendMessageToPlayer(player, "&6" + result2.getString("message"));
                }
            }
            //friends = friends.substring(0,  friends.length() - 2);
            //return friends;
            return "Ok";
        } catch (JSONException e3) {
            e3.printStackTrace();
            return "&6Nothing here!";
        }
    }
    
    public static String getRep(Player player, String recipient) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "notes");
        url_items.put("do", "rep");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("username", recipient);
        
        
        JSONObject result = bConnector.hdl_com(url_items);
        boolean ok = true;
        try {
            ok = result.getBoolean("banned");
            int res;
            if(!ok) {
                return "not";
            }
            else {
                res = result.getInt("local");
                if(res == -100) {
                    return "local";
                }
                return "global";
            }
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            return "not";
        }        
    }
}
