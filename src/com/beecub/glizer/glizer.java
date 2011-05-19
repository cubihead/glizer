package com.beecub.glizer;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;


import com.beecub.command.bCommandRouter;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bConnector;
import com.beecub.util.bTextManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import java.util.HashMap;
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
		
		if(setupPermissions()){
		}
		
		if(setupMessages()) {
		}
		
		if(serverlogin()) {
		}
		
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(messagePluginName + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
		log.info(messagePluginName + " version " + pdfFile.getVersion() + " disabled!");
	}
	
	// onCommand
	@Override
	public boolean onCommand(CommandSender sender, Command c, String commandLabel, String[] args) {	    
	    return bCommandRouter.handleCommands(sender, c, commandLabel, args);	    
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
	
	private boolean serverlogin() {
	    Server server = this.getServer();
	    String serverversion = server.getVersion();
	    String pluginversion = pdfFile.getVersion().replaceAll(".", "");
	    boolean onlinemode = true;
	    boolean whitelist = false;
	    String slots = Integer.toString(server.getMaxPlayers());
	    String servername = bConfigManager.servername;
	    String owner = bConfigManager.owner;
	    //String key = bConfigManager.key;
        serverip = this.getServer().getIp() + this.getServer().getPort();
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "start");
        url_items.put("owner", owner);
        url_items.put("servername", servername);
        url_items.put("serverip", serverip);
        url_items.put("serverversion", serverversion);
        url_items.put("pluginversion", pluginversion);
        url_items.put("offlinemode", Boolean.toString(onlinemode));
        url_items.put("whitelist", Boolean.toString(whitelist));
        url_items.put("slots", slots);
        
        JSONObject result = bConnector.hdl_com(url_items);
        if(result.toString() == "ok") {
            bChat.log("&6 Connected to glizer-server.");
            return true;
        }
        else {
            bChat.log("&6 Cant establish a connection to glizer-server!", 2);
            offline = true;
            return false;
        }
	}
}
