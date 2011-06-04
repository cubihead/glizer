package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.command.bPermissions;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;
import com.beecub.util.bMessageManager;


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
    
    public static boolean glizerreload(String command, Player player, String[] args) {
        
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
                    return true;
                }
            }
            else if(args.length == 0) {
                //showHelpPage(player, 0);
                bChat.sendMessageToPlayer(player, "&6Choose one of these help topics:");
                bChat.sendMessageToPlayer(player, "&e/glizerhelp + ");
                bChat.sendMessageToPlayer(player, "&6Bans   Notes   Warnings   Rating");
                bChat.sendMessageToPlayer(player, "&6Profile   Friends   Comments   Messaging");
                bChat.sendMessageToPlayer(player, "&6BanWhitelist   Whitelist");
                return true;
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
    private static boolean showHelpTopic(Player player, String topic) {
        if(topic.equalsIgnoreCase("")) {
            return true;
        }
        else if(topic.equalsIgnoreCase("Bans")) {
            bChat.sendMessageToPlayer(player, "&6/globalban&e [name] [reason]");
            bChat.sendMessageToPlayer(player, "&6/localban&e [name] [reason]");
            bChat.sendMessageToPlayer(player, "&6/tempban&e [playername] [seconds] [message]");
            bChat.sendMessageToPlayer(player, "&6/unban&e [name] [reason]");
            return true;
        }
        else if(topic.equalsIgnoreCase("Notes")) {
            bChat.sendMessageToPlayer(player, "&6/notes&e [name]");
            bChat.sendMessageToPlayer(player, "&6/note&e [name] [reputation|-100 to 100] [reason]");
            return true;
        }
        else if(topic.equalsIgnoreCase("Warnings")) {
            bChat.sendMessageToPlayer(player, "&6/warnings&e [name]");
            bChat.sendMessageToPlayer(player, "&6/warn&e [name] [reputation|-100 to 100] [reason]");
            return true;
        }
        else if(topic.equalsIgnoreCase("Rating")) {
            bChat.sendMessageToPlayer(player, "&6/rateserver&e [value|0 to 10 (10 is best)]");
            return true;
        }
        else if(topic.equalsIgnoreCase("Profile")) {
            bChat.sendMessageToPlayer(player, "&6/profile&e [name]");
            bChat.sendMessageToPlayer(player, "&6/editprofile&e [field] [value]");
            bChat.sendMessageToPlayer(player, "&6Available Fields:&e age | status | realname | more soon!");
            return true;
        }
        else if(topic.equalsIgnoreCase("Friends")) {
            bChat.sendMessageToPlayer(player, "&6/friends&e [name]");
            bChat.sendMessageToPlayer(player, "&6/addfriend&e [name]");
            bChat.sendMessageToPlayer(player, "&6/removefriend&e [name]");
            bChat.sendMessageToPlayer(player, "&6/friendsonline&e [name] &6- soon!");
            return true;
        }
        else if(topic.equalsIgnoreCase("Comments")) {
            bChat.sendMessageToPlayer(player, "&6/comments&e [name]");
            bChat.sendMessageToPlayer(player, "&6/comment&e [name] [text]");
            return true;
        }
        else if(topic.equalsIgnoreCase("Messaging")) {
            bChat.sendMessageToPlayer(player, "&6soon!");
            return true;
        }
        else if(topic.equalsIgnoreCase("BanWhitelist")) {
            
            return true;
        }
        else if(topic.equalsIgnoreCase("Whitelist")) {
            
            return true;
        }
        return true;
    }
}
