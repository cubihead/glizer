package com.beecub.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bConfigManager {
	
	protected static glizer plugin;
    protected static Configuration conf;
    public static List<String> bannedPlayers = new LinkedList<String>();
    public static List<String> banwhitelistPlayers = new LinkedList<String>();
    public static String key;
    public static String servername;
    public static String owner;
    public static String globalreputation;
    public static String banborder;
    
	
    public bConfigManager(glizer glizer) {
    	plugin = glizer;
    	setupconf();
    	load();
    }
    
	private static void load() {
    	conf.load();
    	
    	key = conf.getString("APIkey", null);
    	servername = conf.getString("servername", null);
    	owner = conf.getString("owner", null);
    	banborder = conf.getString("banborder", "-40");
    	glizer.D = conf.getBoolean("debugmode", false);
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
            conf.setProperty("APIkey", "0");
            conf.setProperty("servername", "name");
            conf.setProperty("owner", "playername");
            conf.setProperty("banborder", "-40");
            conf.setProperty("debugmode", false);
            conf.save();
        }
	}
}
