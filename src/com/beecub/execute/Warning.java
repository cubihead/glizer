package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.util.bChat;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Warning {

    public static boolean warn(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 3) {
                String message = "";
                String recipient = args[0];
                int value = 0;
                try {
                    value = Integer.valueOf(args[1]);
                }
                catch(Exception e) {
                    bChat.sendMessageToPlayer(player, "&6This is not a Integer value: &e" + args[1]);
                    return false;
                }
                for(int i = 2; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    if(Ban.addNote(player, recipient, "0", "1", "0", message, String.valueOf(value), "0", "0")) {
                        bChat.sendMessageToPlayer(player, "&6Warning added");
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/warn&e [playername] -[reputation] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean warnings(String command, Player player, String[] args) {
        Note.notes(command, player, args);
        /*
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                //String result = Ban.getNote(player, args[0], "0", "1", "1", "", "0", "0");
                //bChat.sendMessageToPlayer(player, result);
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            return true;
        }
        */
        return true;
    }
}
