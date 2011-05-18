package com.beecub.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bConfigManager {
	
	protected static glizer plugin;
    protected static Configuration conf;
    protected static Configuration backupban;
    
    public static List<String> bannedPlayers = new LinkedList<String>();
    public static List<String> banwhitelistPlayers = new LinkedList<String>();
    
	
    public bConfigManager(glizer glizer) {
    	plugin = glizer;
    	setupconf();
    	setupbackupban();
    	load();
    }    
    
	private static void load() {
    	conf.load();
    	backupban.load();
    	
    	banwhitelistPlayers = backupban.getKeys("banwhitelist");
    }
	
	public static void reload() {
		load();
	}
	
    private static void setupconf() {
        File f = new File(plugin.getDataFolder(), "config.yml");
        conf = null;
        
        if (f.exists())
        {
            conf = new Configuration(f);
            conf.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder(), "config.yml");
            conf = new Configuration(confFile);
            conf.save();
        }
	}
	
	@SuppressWarnings("static-access")
    private void setupbackupban() {
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
	
	public static boolean removebanwhitelist(String name) {
	    if(banwhitelistPlayers.contains(name)) {
	        backupban.removeProperty("banwhitelist." + name);
	        backupban.save();
	        banwhitelistPlayers = backupban.getKeys("banwhitelist");
	        return true;
	    }
	    return false;
	}
	
	public static boolean addbanwhitelist(String name) {
	    if(!banwhitelistPlayers.contains(name)) {
	        backupban.setProperty("banwhitelist." + name, true);
	        backupban.save();
	        banwhitelistPlayers = backupban.getKeys("banwhitelist");
            return true;
        }
        return false;
	}
}
