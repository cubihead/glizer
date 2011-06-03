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
    
    
    public bBackupManager(glizer glizer) {
        plugin = glizer;
        setupBackupBan();
 
        load();
    }
    
    private static void load() {
        backupban.load();
        
        bannedPlayers = backupban.getStringList("", bannedPlayers);
    }
    
    public static void reload() {
        load();
    }
    
    /* BANS */
    
    private void setupBackupBan() {
        File f = new File(plugin.getDataFolder() + "/banbackup/", "backupban.yml");
        backupban = null;
        
        if (f.exists())
        {
            backupban = new Configuration(f);
            backupban.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder()+ "/banbackup/", "backupban.yml");
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
