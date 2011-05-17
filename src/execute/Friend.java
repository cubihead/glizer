package execute;

import org.bukkit.entity.Player;

import util.bChat;
import util.bPermissions;

import com.beecub.glizer.glizer;

public class Friend {

    public static boolean friends(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 0) {
                // get friends from krim
                return true;
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean addfriend(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                // send new friend to krim
                return true;
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean removefriend(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                // send krim to remove friend
                return true;
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
