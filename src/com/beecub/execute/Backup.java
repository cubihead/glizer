package com.beecub.execute;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;
import com.beecub.util.bBackupManager;
import com.beecub.util.bConnector;

public class Backup {
    
    
    public static String getPlayers() {
        
        //String ip = bConnector.getPlayerIPAddress(player);
        
        HashMap<String, String> url_items = new HashMap<String, String>();
        url_items.put("exec", "export");
        url_items.put("do", "list");
        url_items.put("account", "server");
        url_items.put("ip", "1.1.1.1");
        //url_items.put("account", player.getName());
        //url_items.put("ip", ip);
        
        JSONObject result = bConnector.hdl_com(url_items);
        
        try {
            int length = result.getInt("_size");
            if(length > 0) {
                bBackupManager.clearBanList();
                for(int i = 0; i < length; i++) {
                    String player = result.getString(String.valueOf(i));
                    bBackupManager.addBanBackup(player);
                }
            }
            return "Ok";
        } catch (JSONException e3) {
            if(glizer.D) e3.printStackTrace();
            return "&6Nothing here!";
        }
    }
}
