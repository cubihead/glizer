package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.util.bChat;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Comment {

    public static boolean comment(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    Ban.addNoteAction(player, recipient, "0", "0", "0", message, "0", "0");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean comments(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                // get comments from krim
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
