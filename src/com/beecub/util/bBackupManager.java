package com.beecub.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bBackupManager {
    
    protected static glizer plugin;
    protected static Configuration backupban;
    protected static Configuration whitelist;
    public static List<String> bannedPlayers = new LinkedList<String>();
    public static List<String> whitelistPlayers = new LinkedList<String>();
    
    
    public bBackupManager(glizer glizer) {
        plugin = glizer;
        setupBackupBan();
        setupWhitelist();
        load();
    }    
    
    private static void load() {
        whitelist.load();
        backupban.load();
        
        bannedPlayers = backupban.getStringList("", bannedPlayers);
        whitelistPlayers = whitelist.getStringList("whitelist", whitelistPlayers);
    }
    
    public static void reload() {
        load();
    }
    

    /* WHITELIST */
    
    private void setupWhitelist() {
        File f = new File(plugin.getDataFolder() + "/banwhitelist/", "whitelist.yml");
        whitelist = null;
        
        if (f.exists())
        {
            whitelist = new Configuration(f);
            whitelist.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder()+ "/banwhitelist/", "whitelist.yml");
            whitelist = new Configuration(confFile);
            whitelist.save();
        }
    }
    
    public static boolean removeBanWhiteList(String name) {
        if(whitelistPlayers.contains(name.toLowerCase())) {
            whitelistPlayers.remove(whitelistPlayers.indexOf(name));
            whitelist.setProperty("whitelist", whitelistPlayers);
            whitelist.save();
            return true;
        }
        return false;
    }
    
    public static boolean addBanWhiteList(String name) {
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
    
    /* BANS */
    
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
            backupban = new Configuration(confFile);
            backupban.save();
        }
    }
    
    public static boolean removeBanBackup(String name) {
        if(bannedPlayers.contains(name.toLowerCase())) {
            bannedPlayers.remove(bannedPlayers.indexOf(name));
            backupban.setProperty("ban", bannedPlayers);
            backupban.save();
            return true;
        }
        return false;
    }
    
    public static boolean addBanBackup(String name) {
        if(bannedPlayers.contains(name.toLowerCase())) {
            bannedPlayers.add(name);
            backupban.setProperty("ban", bannedPlayers);
            backupban.save();
            return true;
        }
        return false;
    }
    
    public static boolean checkBanList(String name) {
        if(bannedPlayers.contains(name.toLowerCase())) {
            return true;
        }
        return false;
    }
}
