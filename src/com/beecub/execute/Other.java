package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bPermissions;


public class Other {
    
    public static boolean register(String command, Player player, String[] args) {
        if(args.length == 1) {
            // not yet used
            //String password = args[0];
            return true;
        }
        return true;
    }
    
    public static boolean gm(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                String sender = player.getName();
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    // send message to krim
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean glizer(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length <= 1) {
                bConfigManager.reload();
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }

    public static boolean glizerhelp(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                try {
                    int page = Integer.valueOf(args[0]);
                    if(page > 0) {
                        if(page < 10) {
                            showHelpPage(player, page);
                        }
                    }
                }
                catch(Exception e) {
                    String topic = args[0];
                    showHelpTopic(player, topic);
                }
            }
            else if(args.length == 0) {
                showHelpPage(player, 0);
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean theanswertolifetheuniverseandeverything(String command, Player player, String[] args) {
        bChat.sendMessageToPlayer(player, "&442");
        return true;
    }
    
    /*
     * additional functions
     */
    
    private static void showHelpPage(Player player, int page) {
        
    }
    private static void showHelpTopic(Player player, String topic) {
        
    }
}
