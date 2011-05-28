package com.beecub.execute;

import org.bukkit.entity.Player;

import com.beecub.util.bChat;
import com.beecub.util.bMessageManager;
import com.beecub.util.bPermissions;

public class Note {
    
    public static boolean note(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    if(Ban.addNote(player, recipient, "0", "1", "0", message, "0", "0")) {
                        bChat.sendMessageToPlayer(player, "&6Note added");
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/note&e [playername] [message]");
            return true;
        }
        return true;
    }
    
    public static boolean notes(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 1) {
                int page = 0;
                if(args.length == 2) {
                    try {
                        page = Integer.valueOf(args[1]);
                        page = page - 1;
                    }
                    catch(Exception e) {
                        bChat.sendMessageToPlayer(player, "&6This is not a Integer value: &e" + args[1]);
                        return false;
                    }
                }
                String result = Ban.getNote(player, args[0], "admin", "2", String.valueOf(page), "5");
                bChat.sendMessageToPlayer(player, result);
                return true;
            }
            bChat.sendMessageToPlayer(player, bMessageManager.messageWrongCommandUsage);
            bChat.sendMessageToPlayer(player, "&6/notes&e [playername] [(page)]");
            return true;
        }
        return true;
    }
}
