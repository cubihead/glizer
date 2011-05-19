package com.beecub.util;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;

import com.beecub.glizer.glizer;

public class bTimer {
    public class bWarmUpTimer extends TimerTask {
        
        glizer glizer;
        Player player;
        String pre;
        String message;
        
        public bWarmUpTimer(glizer glizer, Timer timer){
            this.glizer = glizer;
        }
        public bWarmUpTimer() {
        }
        
        public void run() {            
            
        }
        
        public void heartbeat() {
            
        }
    }
}
