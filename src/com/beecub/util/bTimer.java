package com.beecub.util;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import com.beecub.glizer.glizer;

public class bTimer extends TimerTask {
    
    glizer glizer;
    
    public bTimer(glizer glizer, Timer timer){
        this.glizer = glizer;
    }
    
    @SuppressWarnings("static-access")
    public void run() {
        Player [] players = glizer.getServer().getOnlinePlayers();
        if(heartbeat(players, 3)) {
            Timer scheduler = new Timer();
            bTimer scheduleMe = new bTimer(glizer, scheduler);
            scheduler.schedule(scheduleMe, 5 * 60 * 1000);
        }
        else {
            if(glizer.D) bChat.log("Heartbeat too fast, cancel"); 
        }
    }
    
    @SuppressWarnings("static-access")
    public boolean heartbeat(Player [] players, int count) {        
        if(count > 0) {
            HashMap<String, String> url_items = new HashMap<String, String>();        
            url_items.put("exec", "heartbeat");
            url_items.put("ip", "1.1.1.1");
            url_items.put("account", "server");
            
            String users = "";
            //Player [] players = glizer.getServer().getOnlinePlayers();
            for(Player player : players) {
                users += player.getName() + ",";
                url_items.put("ip_" + player.getName(), bConnector.getPlayerIPAddress(player));
            }
            if(players.length > 0) users = users.substring(0, users.length() - 1);
            url_items.put("users", users);
            
            JSONObject result = bConnector.hdl_com(url_items);
            String check = bConnector.checkResult(result);
            
            if(check.equalsIgnoreCase("ok")) {
                bChat.log("Heartbeat, Online Players: " + players.length);
                glizer.offline = false;
                return true;
            }
            else if(check.equalsIgnoreCase("to fast")) {
                return false;
            }
            else if(check.equalsIgnoreCase("null")) {
                bChat.log("Heartbeat failed, trying again", 2);
                heartbeat(players, count - 1);
                return true;
            }
            else {
                bChat.log("Heartbeat: Status unkown");
                return true;
            }
            
            
            /*
            boolean ok = false;          
            
            try {
                // check response
                try {
                    if(result.getString("response").equalsIgnoreCase("ok")) {
                        ok = true;
                    }
                } catch (JSONException e) {
                    if(glizer.D) e.printStackTrace();
                    bChat.log("Unable to do heartbeat", 2);
                }
                // if response != ok, check errno
                if(!ok) {
                    try {
                        // "errno":1005
                        if(result.getInt("errno") == 1005) {
                            return false;
                        }
                    } catch(JSONException e) {
                        if(glizer.D) e.printStackTrace();
                    }
                }
            } catch(Exception e) {
                if(glizer.D) bChat.log("Heartbeat, critical error", 2);
                ok = false;
            }
            
            // print
            if(ok) {
                bChat.log("Heartbeat, Online Players: " + players.length);
            }
            else {
                bChat.log("Heartbeat failed", 2);
                heartbeat(players, count - 1);
            }*/
        }
        return true;
    }
}
