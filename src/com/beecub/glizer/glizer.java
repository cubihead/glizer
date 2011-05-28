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
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bConnector;
import com.beecub.util.bTextManager;
import com.beecub.util.bTimer;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


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
	public static String serverport;
	public static boolean offline = true;
	public static boolean D;
	
	@SuppressWarnings({ "unused", "static-access" })
	public void onEnable() {

		pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_PRELOGIN, playerListener, Event.Priority.Lowest, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
	    
		bConfigManager bConfigManager = new bConfigManager(this);
		bTextManager bTextManager = new bTextManager(this);
		bChat bChat = new bChat(this.getServer());
		
		serverport = this.getServer().getIp() + this.getServer().getPort();
		
        if(setupMessages()) {
        }
        
        if(checkOnlineMode()) {
        }
		
		if(setupPermissions()){
		}
		
		if(serverlogin()) {
		}
		
		if(heartbeat(this)) {
		}
		    
		PluginDescriptionFile pdfFile = this.getDescription();
		bChat.log(messagePluginName + " version " + pdfFile.getVersion() + " is enabled!" );
	}
	public void onDisable() {
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
        messageWrongCommandUsage = "&6Wrong command usage!";
        messagePermissions = "&6You dont have permission to this command";
        messagePluginName = "[" + pdfFile.getName() + "]";        
        return true;
    }
	
	private boolean serverlogin() {
	    Server server = this.getServer();
	    String serverversion = server.getVersion();
	    String pluginversion = pdfFile.getVersion().replaceAll("\\.", "");
	    boolean whitelist = false;
	    String slots = Integer.toString(server.getMaxPlayers());
	    String servername = bConfigManager.servername;
	    String owner = bConfigManager.owner;
	    //String key = bConfigManager.key;        
        
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
        if(whitelist = false) url_items.put("whitelist", "0");
        else url_items.put("whitelist", "1");
        //url_items.put("whitelist", Boolean.toString(whitelist));
        if(onlinemode = false) url_items.put("offlinemode", "0");
        else url_items.put("offlinemode", "1");
        url_items.put("banborder", bConfigManager.banborder);
        url_items.put("owner", owner);
        
        JSONObject result = bConnector.hdl_com(url_items);
        try {
            if(result.getString("response") == "ok") {
                bChat.log("&6 Connected to glizer-server.");
                return true;
            }
            else {
                bChat.log("&6 Failure! Wrong server configuration data sent.", 2);
                offline = true;
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            bChat.log("&6 Cant establish a connection to glizer-server! glizer is now in offline mode.", 2);
            return false;
        }
	}
	
    private boolean checkOnlineMode() {
        Properties prop = new Properties();
        String f = "server.properties";
        log.info(f);
        try{
            FileInputStream in = new FileInputStream(new File(f));
            prop.load(in);
            String work = prop.getProperty("online-mode");
            if(work.equals("true")) {
                onlinemode = true;
                return true;
            } else {
                bChat.log(messagePluginName + " Online-mode false!", 2);
                this.getServer().getPluginManager().disablePlugin(this);
                return false;
            }
        } catch(IOException e) {
            this.getServer().getPluginManager().disablePlugin(this);
            e.printStackTrace();
            bChat.log(messagePluginName + " Online-mode false!", 2);
            this.getServer().getPluginManager().disablePlugin(this);
            return false;
        }
    }
    
    public static boolean heartbeat(glizer glizer) {
        Timer scheduler = new Timer();
        bTimer scheduleMe = new bTimer();
        scheduler.schedule(scheduleMe, 5 * 60 * 1000);
        return true;
    }
}
