package com.beecub.glizer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONException;
import org.json.JSONObject;


import com.beecub.command.bCommandRouter;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bTextManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;


public class glizer extends JavaPlugin {
	private final glizerPlayerListener playerListener = new glizerPlayerListener(this);
	public static Logger log = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile pdfFile;
	public static PermissionHandler Permissions;
	public static boolean permissions = false;
	public static String messageWrongCommandUsage;
	public static String messagePermissions;
	public static String messagePluginName;
	public static boolean onlinemode = false;
	public static String serverip;
	public static boolean offline = true;

	@SuppressWarnings("unused")
	public void onEnable() {

		pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_PRELOGIN, playerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
	    
		bConfigManager bConfigManager = new bConfigManager(this);
		bTextManager bTextManager = new bTextManager(this);
		bChat bChat = new bChat(this.getServer());
		
		serverip = this.getServer().getIp();
		
		
		if(setupPermissions()){
		}
		
		if(setupMessages()) {
		}
		
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(messagePluginName + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
		log.info(messagePluginName + " version " + pdfFile.getVersion() + " disabled!");
	}
		
	
	// setup permissions
	private boolean setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
		if (glizer.Permissions == null) {
			if (test != null) {
				glizer.Permissions = ((Permissions)test).getHandler();
				log.info(messagePluginName + "Permission system found");
				permissions = true;
				return true;
			}
			else {
				log.info(messagePluginName + "Permission system not detected, plugin disabled");
				this.getServer().getPluginManager().disablePlugin(this);
				permissions = false;
				return false;
			}
		}
		return false;
	}
	
	private boolean setupMessages() {
	    messageWrongCommandUsage = "&6Wrong command usage!";
	    messagePermissions = "&6You dont have permission to this command";
	    messagePluginName = "[" + pdfFile.getName() + "]";
	    return true;
	}
	
	// onCommand
	@Override
	public boolean onCommand(CommandSender sender, Command c, String commandLabel, String[] args) {	    
	    return bCommandRouter.handleCommands(sender, c, commandLabel, args);	    
	}
}
