package com.beecub.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.json.JSONException;
import org.json.JSONObject;

import com.beecub.glizer.glizer;

public class bConnector {
    
    public static JSONObject hdl_com(HashMap<String, String> items) 
    {
        String url_req = urlParse(items);
        if(glizer.D) glizer.log.info("url_req " + url_req);
        String json_text = APIRequest(url_req);
        JSONObject output = getData(json_text);
        
        return output;
    }
    
    @SuppressWarnings("rawtypes")
    public static String urlParse(HashMap<String, String> items) {
        String data = "";
        try {
            for (Map.Entry entry : items.entrySet()) {
                String key = (String)entry.getKey();
                String val = (String)entry.getValue();
                //if(glizer.D) glizer.log.info(" ++++ KEY ++++ " + key);
                //if(glizer.D) glizer.log.info(" ++++ VAL ++++ " + val);
                if (data.equals(""))
                    data = URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
                else
                    data = data + "&" + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            glizer.log.info("Error parsing");
        }
        return data;
    }


    public static String APIRequest(String data) {
        try {
            
            URL url = new URL("http://api.glizer.de/" + bConfigManager.key + "/");
            
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            StringBuilder buf = new StringBuilder();
      
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                buf.append(line);
            }
      
            String result = buf.toString();
            
            // DEBUG
            if(glizer.D) bChat.log(result);
      
            wr.close();
            rd.close();
            return result;
        } catch (Exception e) {
            System.out.println("Error on request from API");
        }
        return "";
    }
      
    public static JSONObject getData(String json_text) {
        try {
            JSONObject json = new JSONObject(json_text);
            return json;
        } catch (JSONException e) {
            glizer.log.info("Receiving data failed");
        }
        return null;
    }
    
    public static String getPlayerIPAddress(Player player) {
        InetAddress addr = player.getAddress().getAddress();
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i=0; i<ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i]&0xFF;
        }
        return ipAddrStr;
    }
    
    public static String checkResult(JSONObject result) {
        String ret = "";               
        if(result != null) {
            try {
                if(result.getString("error").equalsIgnoreCase("1")) {
                    int errno = result.getInt("errno");
                    switch(errno) {
                        case 1000:
                            ret = "wrong ip";
                        case 1001:
                            ret = "wrong ip";
                        case 1002:
                            ret = "no record";
                        case 1003:
                            ret = "sql error";
                        case 1004:
                            ret = "missing variable";
                        case 1005:
                            ret = "to fast";
                        case 1006:
                            ret = "never connected";
                        case 1007:
                            ret = "exec not found";
                        case 1008:
                            ret = "wrong data type";
                        case 1009:
                            ret = "server as user";
                        case 1010:
                            ret = "not allowed";
                        case 1011:
                            ret = "not from here";
                        case 1012:
                            ret = "not yourself";
                        default:
                            ret = "unknown error";
                    }
                }
            } catch(JSONException e) {
                if(glizer.D) e.printStackTrace();
                try {
                    if(result.getString("response").equalsIgnoreCase("ok")) {
                        ret = "ok";
                    }
                } catch(JSONException e1) {
                    if(glizer.D) e1.printStackTrace();
                    return ret = "unkown";
                }
            }
        }
        else {
            ret = "null";
        }
        if(glizer.D) bChat.log("[glizer debug] " + ret);
        return ret;
    }
}
