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
    
    public void run() {
        Player [] players = glizer.getServer().getOnlinePlayers();
        heartbeat(players);
        Timer scheduler = new Timer();
        bTimer scheduleMe = new bTimer(glizer, scheduler);
        scheduler.schedule(scheduleMe, 5 * 60 * 1000);
    }
    
    public void heartbeat(Player [] players) {
        
        HashMap<String, String> url_items = new HashMap<String, String>();        
        url_items.put("exec", "heartbeat");
        url_items.put("ip", "1.1.1.1");
        url_items.put("account", "server");
        
        
        String users = "";
        //Player [] players = glizer.getServer().getOnlinePlayers();
        for(Player player : players) {
            users = player.getName() + ",";
            url_items.put("ip_" + player.getName(), bConnector.getPlayerIPAddress(player));
        }
        users.substring(1, users.length() - 1);
        url_items.put("users", users);        
        
        JSONObject result = bConnector.hdl_com(url_items);
        if(result.toString() == "ok") {
            bChat.log("[glizer] Heartbeat.");
        }
        else {
            bChat.log("[glizer] Heartbeat failed.", 2);
        }
    }
}
