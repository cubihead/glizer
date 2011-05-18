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
	
	public static JSONObject hdl_com(HashMap<String, String> items) 
    {
        //HashMap<String, String> out = new HashMap<String, String>(); //Ausgebende Hashmap
        String url_req = urlparse(items); //Wandelt die Hashmap um in einen String, welcher dem Request mit übergeben wird (funktion siehe unten)
        log.info("url_req " + url_req);
        String json_text = request_from_api(url_req); // Stellt die Anfrage an die API (sendet also den Request - erhält String der Rückgabe vom Server (noch encoded als json)
        JSONObject output = get_data(json_text); //Wandelt den Json Code in ein Json Objekt um
        
        
        return output;
//        
//        //Verifikation ob Objekt in Ordnung ist und wandelt in HashMap um
//        if (output != null)
//        {
//            Iterator<?> i = output.keys();
//            if (i != null) {
//                while (i.hasNext())
//                {
//                    String next = (String)i.next();
//                    try 
//                    {
//                        out.put(next, output.getString(next));
//                    } catch (JSONException e) {
//                        System.out.println("error");
//                    }
//                }
//            }
//        }
//        return out;
    }
	
	public static HashMap<String, String> hdl_com(String items) {
	    HashMap<String, String> out = new HashMap<String, String>(); //Ausgebende Hashmap
        //String url_req = urlparse(items); //Wandelt die Hashmap um in einen String, welcher dem Request mit übergeben wird (funktion siehe unten)
        String json_text = request_from_api(items); // Stellt die Anfrage an die API (sendet also den Request - erhält String der Rückgabe vom Server (noch encoded als json)
        JSONObject output = get_data(json_text); //Wandelt den Json Code in ein Json Objekt um
        
        //Verifikation ob Objekt in Ordnung ist und wandelt in HashMap um
        if (output != null)
        {
            Iterator<?> i = output.keys();
            if (i != null) {
                while (i.hasNext())
                {
                    String next = (String)i.next();
                    try 
                    {
                        out.put(next, output.getString(next));
                    } catch (JSONException e) {
                        System.out.println("bans error");
                    }
                }
            }
        }
        return out;
	}
	
    @SuppressWarnings("rawtypes")
    public static String urlparse(HashMap<String, String> items) {
        String data = "";
        try {
            for (Map.Entry entry : items.entrySet()) {
                String key = (String)entry.getKey();
                String val = (String)entry.getValue();
                if (data.equals(""))
                    data = URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
                else
                    data = data + "&" + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            log.info(e.toString());
        }
        return data;
    }


    public static String request_from_api(String data) {
        try {
            
            //URL url = new URL("http://api.glizer.de/index.php/" + key);
            URL url = new URL("http://dev.wmchris.de/projects/bans/rpc.php" /*+ key*/);
            
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);
            
            //Öffne den Stream zum Server und sende den String "data" an diesen. Für ein POST fehlen einige Daten
            //Vermute, dass der Server diese RAW empfängt. Eventuell umstellen auf GET (siehe Oben)
            //Müssen wir einfach mal testen indem du eine Anfrage gegen den Server machst und ich schaue ob ich
            //die Daten via den üblichen verdächtigten erfassen kann. Get hat den Nachteil, dass es nur eine
            //bestimmte maximallänge ermöglicht, sollten es also auf jedenfall ausprobieren oder die funktion um die
            //Post header erweitern (siehe ftp vom dev.wmchris.de unter www projects bans test test.php für beispiele
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            StringBuilder buf = new StringBuilder();
      
            //Buffered Reader ist das gegenstück, liest den Response vom Server aus
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) //Zeile für zeile, etwas umständlich, aber naja
            {
                buf.append(line);
            }
      
            String result = buf.toString();
            log.info(result);
      
            wr.close();
            rd.close();
            return result;
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
      
    public static JSONObject get_data(String json_text) {
        try {
            JSONObject json = new JSONObject(json_text);
            return json;
        } catch (JSONException e) {
            log.info("Retrieval of data failed.");
        }
        return null;
    }
}
