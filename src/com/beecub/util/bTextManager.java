package com.beecub.util;

import java.io.File;

import org.bukkit.util.config.Configuration;

import com.beecub.glizer.glizer;

public class bTextManager {

    protected static glizer plugin;
    protected static Configuration conf;
    
    
    public bTextManager(glizer glizer) {
        plugin = glizer;
        setupconf();
        load();
    }    
    
    private static void load() {
        conf.load();
        
    }
    
    public static void reload() {
        load();
    }
    
    private void setupconf() {
        File f = new File(plugin.getDataFolder(), "texts.yml");
        conf = null;
        
        if (f.exists())
        {
            conf = new Configuration(f);
            conf.load();            
        }
        else {
            File confFile;
            confFile = new File(plugin.getDataFolder(), "texts.yml");
            conf = new Configuration(confFile);
            conf.save();
        }
    }
}
