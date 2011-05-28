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
    public bTimer() {
    }
    
    public void run() {
        heartbeat();
        Timer scheduler = new Timer();
        bTimer scheduleMe = new bTimer(glizer, scheduler);
        scheduler.schedule(scheduleMe, 5 * 60 * 1000);
    }
    
    public void heartbeat() {
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "heartbeat");
        url_items.put("ip", "1.1.1.1");
        url_items.put("account", "server");
        url_items.put("users", "beecub,user2,user3"); 
        url_items.put("ip_beecub", "10.20.30.40");
        
        JSONObject result = bConnector.hdl_com(url_items);
        bChat.log(result.toString());
        if(result.toString() == "ok") {
            bChat.log("&6 Connected to glizer-server.");
        }
        else {
            bChat.log("&6 Cant establish a connection to glizer-server!", 2);
        }
    }
}
