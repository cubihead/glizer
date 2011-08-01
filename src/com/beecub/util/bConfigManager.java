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
    public static String key;
    public static String servername;
    public static String owner;
    public static String globalreputation;
    public static String banborder;
    
    // features
    public static boolean usewhitelist;
    public static boolean useglobalbans;
    public static boolean usebansystem;
    public static boolean useprofiles;
    public static boolean usecomments;
    public static boolean useratings;   
    
	
    public bConfigManager(glizer glizer) {
    	plugin = glizer;
    	setupconf();
    	load();
    }
    
	private static void load() {
    	conf.load();
    	
    	key = conf.getString("APIkey", null);
    	if(key == null) conf.setProperty("APIkey", "");
    	
    	servername = conf.getString("servername", null);
    	if(servername == null) conf.setProperty("servername", "please change");
    	
    	owner = conf.getString("owner", null);
    	if(owner == null) conf.setProperty("owner", "please change");
    	
    	
    	// options
    	banborder = conf.getString("options.banborder", "1000");
    	if(banborder == "1000") conf.setProperty("options.banborder", "-40");
    	
    	glizer.D = conf.getBoolean("options.debugmode", false);
    	
    	
    	// features    	
    	List<String> features = conf.getKeys("features");
    	bChat.log(features.toString());
    	if(!features.contains("usewhitelist")) conf.setProperty("features.usewhitelist", false);
    	if(!features.contains("useglobalbans")) conf.setProperty("features.useglobalbans", true);
	    if(!features.contains("usebansystem")) conf.setProperty("features.usebansystem", true);
        if(!features.contains("useprofiles")) conf.setProperty("features.useprofiles", true);
        if(!features.contains("usecomments")) conf.setProperty("features.usecomments", true);
        if(!features.contains("useratings")) conf.setProperty("features.useratings", true);
    	
    	usewhitelist = conf.getBoolean("features.usewhitelist", false);    	
    	useglobalbans = conf.getBoolean("features.useglobalbans", false);        
        usebansystem = conf.getBoolean("features.usebansystem", false);        
        useprofiles = conf.getBoolean("features.useprofiles", false);
        usecomments = conf.getBoolean("features.usecomments", false);        
        useratings = conf.getBoolean("features.useratings", false);
        
        conf.save();
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
            conf.setProperty("APIkey", "Add your API-Key here");
            conf.setProperty("servername", "Add your servername here");
            conf.setProperty("owner", "Add your name here");
            
            // options
            conf.setProperty("options.banborder", "-40");
            conf.setProperty("options.debugmode", false);
            
            // features
            conf.setProperty("features.usewhitelist", false);
            conf.setProperty("features.useglobalbans", true);
            conf.setProperty("features.usebansystem", true);
            conf.setProperty("features.useprofiles", true);
            conf.setProperty("features.usecomments", true);
            conf.setProperty("features.useratings", true);
            conf.save();
        }
	}
}
