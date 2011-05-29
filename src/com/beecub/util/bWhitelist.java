package com.beecub.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bWhitelist {
    
    protected static glizer plugin;
    protected static Configuration whitelist;
    public static List<String> whitelistPlayers = new LinkedList<String>();
    
    public bWhitelist(glizer glizer) {
        plugin = glizer;
        setupWhitelist();
        load();
    }
    
    private static void load() {
        whitelist.load();
        
        whitelistPlayers = whitelist.getStringList("whitelist", whitelistPlayers);
    }
    
    public static void reload() {
        load();
    }
    

    /* WHITELIST */
    
    private void setupWhitelist() {
        File f = new File(plugin.getDataFolder() + "/whitelist/", "whitelist.yml");
        whitelist = null;
        
        if (f.exists())
        {
            whitelist = new Configuration(f);
            whitelist.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder()+ "/whitelist/", "whitelist.yml");
            whitelist = new Configuration(confFile);
            whitelist.save();
        }
    }
    
    public static boolean removeWhiteList(String name) {
        if(whitelistPlayers.contains(name.toLowerCase())) {
            whitelistPlayers.remove(whitelistPlayers.indexOf(name));
            whitelist.setProperty("whitelist", whitelistPlayers);
            whitelist.save();
            return true;
        }
        return false;
    }
    
    public static boolean addWhiteList(String name) {
        if(!whitelistPlayers.contains(name.toLowerCase())) {
            whitelistPlayers.add(name);
            whitelist.setProperty("whitelist", whitelistPlayers);
            whitelist.save();
            return true;
        }
        return false;
    }
    
    public static boolean checkBanWhiteList(String name) {
        if(whitelistPlayers.contains(name.toLowerCase())) {
            return true;
        }
        return false;
    }
}
