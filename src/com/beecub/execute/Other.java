package com.beecub.execute;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;


public class Other {
    
    public static boolean register(String command, Player player, String[] args) {
        if(args.length == 1) {
            // not yet used
            //String password = args[0];
            return true;
        }
        return true;
    }
    
    public static boolean gm(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                String sender = player.getName();
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    // send message to krim
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean glizer(String command, Player player, String[] args) {
        
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "ban");
        url_items.put("username","beecub");
        url_items.put("time","0");
        url_items.put("reason","hat rumgeflamed");
        //String items = "ban=user";
        //HashMap<String, String> result = glizer.hdl_com(items);
        
        //glizer.log.info(result.toString());
        
//        HashMap<String, String> url_items = new HashMap<String, String>();
//        url_items.put("exec", "server_premium");
        
        JSONObject result = bConnector.hdl_com(url_items);
        try {
            if(result.getString("response") == "ok");
                // output: "wurde erfolgreich gebannt"
//            String result2 = Integer.toString(result.getInt("result"));
//            JSONArray data = result.getJSONArray("response");
//            for(int i = 0; i < data.length(); i++) {
//                JSONObject obj = data.getJSONObject(i);
            //output: deine Kommentare #ITERATION
//                glizer.log.info(obj.getString("text"));
//            }

//            
//
//
//            glizer.log.info(result2);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //glizer.log.info(result.toString());
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length <= 1) {
                bConfigManager.reload();
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }

    public static boolean glizerhelp(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                try {
                    int page = Integer.valueOf(args[0]);
                    if(page > 0) {
                        if(page < 10) {
                            showHelpPage(player, page);
                        }
                    }
                }
                catch(Exception e) {
                    String topic = args[0];
                    showHelpTopic(player, topic);
                }
            }
            else if(args.length == 0) {
                showHelpPage(player, 0);
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean theanswertolifetheuniverseandeverything(String command, Player player, String[] args) {
        bChat.sendMessageToPlayer(player, "&442");
        return true;
    }
    
    /*
     * additional functions
     */
    
    private static void showHelpPage(Player player, int page) {
        
    }
    private static void showHelpTopic(Player player, String topic) {
        
    }
}
