package execute;

import org.bukkit.entity.Player;

import util.bChat;
import util.bPermissions;

import com.beecub.glizer.glizer;

public class Comment {

    public static boolean comment(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length >= 2) {
                String message = "";
                String recipient = args[0];
                String sender = player.getName();
                for(int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }
                if(message != null && message != "") {
                    // send comment to krim
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
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
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
