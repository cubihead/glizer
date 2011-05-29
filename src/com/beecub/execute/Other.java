package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;


public class Other {
    
    public static boolean register(String command, Player player, String[] args) {
        /*
        if(args.length == 1) {
            // not yet used
            //String password = args[0];
            return true;
        }
        */
        bChat.sendMessageToPlayer(player, "&6Not available in this version of glizer");
        return true;
    }
    
    public static boolean gm(String command, Player player, String[] args) {
        
        /*
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
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        */
        bChat.sendMessageToPlayer(player, "&6Not available in this version of glizer");
        return true;
    }
    
    public static boolean glizer(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length <= 1) {
                bConfigManager.reload();
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
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
                //showHelpPage(player, 0);
                bChat.sendMessageToPlayer(player, "&6Choose one of these help topics:");
                bChat.sendMessageToPlayer(player, "&e/glizerhelp + ");
                bChat.sendMessageToPlayer(player, "&6Bans   Notes   Warnings   Rating");
                bChat.sendMessageToPlayer(player, "&6Profile   Friends   Comments   Messaging");
                bChat.sendMessageToPlayer(player, "&6BanWhitelist   Whitelist");
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean theanswertolifetheuniverseandeverything(String command, Player player, String[] args) {
        bChat.sendMessageToPlayer(player, "&442");
        return true;
    }
    
    private static void showHelpPage(Player player, int page) {
        
    }
    private static void showHelpTopic(Player player, String topic) {
        if(topic.equalsIgnoreCase("")) {
            
        }
        else if(topic.equalsIgnoreCase("Bans")) {
            
        }
    }
}
