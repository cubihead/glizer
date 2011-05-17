package com.beecub.execute;

import org.bukkit.entity.Player;


import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bPermissions;

public class Warning {

    public static boolean warn(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 3) {
                String message = "";
                String recipient = args[0];
                String sender = player.getName();
                int value = Integer.valueOf(args[1]);
                for(int i = 2; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    // send warning to krim
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean warnings(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                // get warnings from krim
                return true;
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
