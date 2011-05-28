package com.beecub.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bBackupManager {
    
    protected static glizer plugin;
    protected static Configuration backupban;
    public static List<String> bannedPlayers = new LinkedList<String>();
    public static List<String> banwhitelistPlayers = new LinkedList<String>();
    public static String key;
    public static String servername;
    public static String owner;
    public static String banborder;
    public static String globalreputation;
    
    
    public bBackupManager(glizer glizer) {
        plugin = glizer;
        setupBackupBan();
        load();
    }    
    
    private static void load() {
        backupban.load();
        
        banwhitelistPlayers = backupban.getKeys("banwhitelist");
    }
    
    public static void reload() {
        load();
    }

    
    @SuppressWarnings("static-access")
    private void setupBackupBan() {
        File f = new File(plugin.getDataFolder() + "/backup/", "backupban.yml");
        backupban = null;
        
        if (f.exists())
        {
            backupban = new Configuration(f);
            backupban.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder()+ "/backup/", "backupban.yml");
            this.backupban = new Configuration(confFile);
            backupban.save();
        }
    }
    
    public static boolean removeBanWhiteList(String name) {
        if(banwhitelistPlayers.contains(name)) {
            backupban.removeProperty("banwhitelist." + name);
            backupban.save();
            banwhitelistPlayers = backupban.getKeys("banwhitelist");
            return true;
        }
        return false;
    }
    
    public static boolean addBanWhiteList(String name) {
        if(!banwhitelistPlayers.contains(name)) {
            backupban.setProperty("banwhitelist." + name, true);
            backupban.save();
            banwhitelistPlayers = backupban.getKeys("banwhitelist");
            return true;
        }
        return false;
    }
    
    public static void checkBanWhiteList(String name) {
        
    }
    
    public static boolean addBanBackup(String playername) {
        
        return true;
    }
    
    public static boolean removeBanBackup(String playername) {
        
        return true;
    }
}
