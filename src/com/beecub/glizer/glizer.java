package com.beecub.glizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Logger;

import org.bukkit.Server;
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
import com.beecub.util.bBackupManager;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bConnector;
import com.beecub.util.bMessageManager;
import com.beecub.util.bTimer;
import com.beecub.util.bWhitelist;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


public class glizer extends JavaPlugin {
	private final glizerPlayerListener playerListener = new glizerPlayerListener(this);
	public static Logger log = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile pdfFile;
	public static PermissionHandler Permissions;
	public static boolean permissions = false;
	public static String messagePluginName;
	public static boolean onlinemode = false;
	public static String serverip;
	public static String serverport;
	public static boolean offline = true;
	public static boolean D;
	public static glizer plugin;
	
	@SuppressWarnings({ "unused", "static-access" })
    public void onEnable() {

		pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_PRELOGIN, playerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Lowest, this);
	    
		bConfigManager bConfigManager = new bConfigManager(this);
		bBackupManager bBackupManager = new bBackupManager(this);
		bMessageManager bTextManager = new bMessageManager(this);
		bWhitelist bWhitelist = new bWhitelist(this);
		bChat bChat = new bChat(this.getServer());
		
		plugin = this;
		
		serverport = /*this.getServer().getIp() +*/ String.valueOf(this.getServer().getPort());
		
        if(setupMessages()) {
        }
        
        if(checkOnlineMode()) {
        }
		
		if(setupPermissions()){
		}
		
		if(serverLogin()) {
		}
		
		if(heartbeat(this)) {
		}
		    
		PluginDescriptionFile pdfFile = this.getDescription();
		bChat.log(messagePluginName + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
	    if(serverLogout()) {
	    }
		bChat.log(messagePluginName + " version " + pdfFile.getVersion() + " disabled!");
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
                log.info(messagePluginName + " Permission system found");
                permissions = true;
                return true;
            }
            else {
                log.info(messagePluginName + " Permission system not detected, plugin disabled!");
                this.getServer().getPluginManager().disablePlugin(this);
                permissions = false;
                return false;
            }
        }
        return false;
    }
    
    private boolean setupMessages() {
        messagePluginName = "[" + pdfFile.getName() + "]";        
        return true;
    }
	
	private boolean serverLogin() {
	    Server server = this.getServer();
	    String serverversion = server.getVersion();
	    String pluginversion = pdfFile.getVersion().replaceAll("\\.", "");
	    String slots = Integer.toString(server.getMaxPlayers());
	    String servername = bConfigManager.servername;
	    String owner = bConfigManager.owner;      
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "start");
        url_items.put("account", "server");
        url_items.put("ip", "1.1.1.1");
        url_items.put("lastip", "1.1.1.1");
        url_items.put("port", serverport);
        url_items.put("name", servername);
        url_items.put("version", pluginversion);
        url_items.put("bukkit", serverversion);
        url_items.put("slots", slots);
        if(!checkWhiteList() || !bConfigManager.usewhitelist) url_items.put("whitelist", "0");
        else url_items.put("whitelist", "1");
        if(onlinemode == false) url_items.put("offlinemode", "0");
        else url_items.put("offlinemode", "1");
        url_items.put("banborder", bConfigManager.banborder);
        url_items.put("owner", owner);        
        
        String users = "";
        if(bConfigManager.usewhitelist) {
            for(String player : bWhitelist.whitelistPlayers) {
                users += player + ",";
            }
            if(users.length() > 0) users = users.substring(0, users.length() - 1);
        }
        url_items.put("whitelistusers", users);
        
              
        
        JSONObject result = bConnector.hdl_com(url_items);
        String ok = null;
        try {
            ok = result.getString("response");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            bChat.log("Cant establish a connection to glizer-server! glizer is now in offline mode", 2);
            offline = true;
            return false;
        } 
        if(ok.equalsIgnoreCase("ok")) {
            bChat.log("Connected to glizer-server");
            offline = false;
            return true;
        }
        else {
            bChat.log("Failure! Wrong server configuration data sent", 2);
            offline = true;
            return false;
        }
	}
	
   @SuppressWarnings("unused")
   private boolean serverLogout() {
        Server server = this.getServer();     
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "shutdown");
        url_items.put("account", "server");
        url_items.put("ip", "1.1.1.1");
        
        JSONObject result = bConnector.hdl_com(url_items);
        return true;
        /*String ok;
        try {
            ok = result.getString("response");
        } catch (JSONException e) {
            if(glizer.D) e.printStackTrace();
            bChat.log("&6 Cant establish a connection to glizer-server! glizer is now in offline mode.", 2);
            offline = true;
            return false;
        } 
        bChat.log(":::" + ok + ":::");
        if(ok.equalsIgnoreCase("ok")) {
            bChat.log("Connected to glizer-server.");
            return true;
        }
        else {
            bChat.log("Failure! Wrong server configuration data sent.", 2);
            offline = true;
            return false;
        }*/
    }
	
    private boolean checkOnlineMode() {
        Properties prop = new Properties();
        String f = "server.properties";
        try{
            FileInputStream in = new FileInputStream(new File(f));
            prop.load(in);
            String work = prop.getProperty("online-mode");
            if(work.equals("true")) {
                onlinemode = true;
                return true;
            } else {
                bChat.log(messagePluginName + " Online-mode false! glizer disabled.", 2);
                this.getServer().getPluginManager().disablePlugin(this);
                return false;
            }
        } catch(IOException e) {
            if(glizer.D) e.printStackTrace();
            bChat.log(messagePluginName + " Online-mode false! glizer disabled.", 2);
            this.getServer().getPluginManager().disablePlugin(this);
            return false;
        }
    }
    
    private boolean checkWhiteList() {
        Properties prop = new Properties();
        String f = "server.properties";
        try{
            FileInputStream in = new FileInputStream(new File(f));
            prop.load(in);
            String work = prop.getProperty("white-list");
            if(work.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        } catch(IOException e) {
            if(glizer.D) e.printStackTrace();
            return true;
        } 
    }
    
    public static boolean heartbeat(glizer glizer) {
        Timer scheduler = new Timer();
        bTimer scheduleMe = new bTimer(glizer, scheduler);
        scheduler.schedule(scheduleMe, 5 * 60 * 1000);
        return true;
    }
}
