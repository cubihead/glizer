package com.beecub.execute;

import org.bukkit.entity.Player;


import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Note {
    
    public static boolean note(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                String sender = player.getName();
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    // send note to krim
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean notes(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                // get notes from krim
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
