package com.beecub.execute;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import com.beecub.command.bPermissions;
import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;


public class Rating {

    public static boolean rateplayer(String command, Player player, String[] args) {
//        if(bPermissions.checkPermission(player, command)) {
//            if(args.length == 2) {
//                //String recipient = args[0];
//                int rating = Integer.valueOf(args[1]);
//                if(rating >= 0) {
//                    if(rating <= 10) {
//                        // send player rating to krim
//                        
//                        return true;
//                    }
//                }
//            }
//            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
//            return true;
//        }
        bChat.sendMessageToPlayer(player, "&6Not available in this version of glizer");
        return true;
    }
    
    public static boolean rateserver(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                try {
                    int rating = Integer.valueOf(args[0]);
                    if(rating >= 0) {
                        if(rating <= 10) {
                            if(rateServer(player, String.valueOf(rating))) {
                                bChat.sendMessageToPlayer(player, "Success");
                                return true;
                            }
                            else {
                                return true;
                            }
                        }
                    }
                    bChat.sendMessageToPlayer(player, "&6Rating has to be between &e0&6 and &e10&6 (10 is best)");
                    return true;
                }
                catch(Exception e) {
                    bChat.sendMessageToPlayer(player, "&6This is not a Integer value: &e" + args[0]);
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/rateserver &e[value|0-10 (10 is best)]");
            return true;
        }
        return true;
    }
    
    public static boolean rateServer(Player player, String value) {
        
        String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "rate");
        url_items.put("do", "add");
        url_items.put("account", player.getName());
        url_items.put("ip", ip);
        url_items.put("rep", value);
        
        JSONObject result = bConnector.hdl_com(url_items);
        String check = bConnector.checkResult(result);
        
        if(check.equalsIgnoreCase("ok")) {
            if(glizer.D) bChat.log("Server rate action done");
            return true;
        }
        else if(check.equalsIgnoreCase("wrong data type")) {
            if(glizer.D) bChat.log("Server rate action cant be done, wrong data type sent");
            bChat.sendMessageToPlayer(player, "&6Error, wrong data type");
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
            if(glizer.D) bChat.log("Server rate action done.");
            return true;
        }
        else {
            if(glizer.D) bChat.log("Server rate action cant be done", 2);
            return false;
        }
        */
    }
}
