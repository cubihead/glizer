package com.beecub.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.beecub.glizer.glizer;

public class bWhitelist {
    
    protected static glizer plugin;
    public static List<String> whitelistPlayers = new LinkedList<String>();
    
    public bWhitelist(glizer glizer) {
        plugin = glizer;
        setupWhitelist();
        //load();
    }
    
    private static void load() {
        setupWhitelist();        
    }
    
    public static void reload() {
        load();
    }
    

    /* WHITELIST */
    
    private static void setupWhitelist() {
        try {
            FileInputStream fstream  = new FileInputStream("white-list.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)      {
                whitelistPlayers.add(strLine);
            }
            in.close();
        } catch(Exception e) {
            if(glizer.D) e.printStackTrace();
        }
    }
    
    private static void writeWhitelist(String name) {
        try {
            FileWriter fstream = new FileWriter("white-list.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for(String strLine : whitelistPlayers) {
                bChat.log(strLine);
                out.write(strLine);
                out.newLine();
            }
            out.close();
        } catch(Exception e) {
            if(glizer.D) e.printStackTrace();
        }
    }
    
    public static boolean removeWhiteList(String name) {
        if(whitelistPlayers.contains(name.toLowerCase())) {
            whitelistPlayers.remove(whitelistPlayers.indexOf(name));
            writeWhitelist(name);
            return true;
        }
        return false;
    }
    
    public static boolean addWhiteList(String name) {
        if(!whitelistPlayers.contains(name.toLowerCase())) {
            whitelistPlayers.add(name);
            writeWhitelist(name);
            bChat.log(whitelistPlayers.toString());
            return true;
        }
        return false;
    }
    
    public static boolean checkWhiteList(String name) {
        if(whitelistPlayers.contains(name.toLowerCase())) {
            return true;
        }
        return false;
    }
}
