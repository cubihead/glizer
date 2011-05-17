package execute;

import org.bukkit.entity.Player;

import com.beecub.glizer.glizer;

import util.bChat;
import util.bPermissions;

public class Rating {

    public static boolean rateplayer(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 2) {
                String recipient = args[0];
                int rating = Integer.valueOf(args[1]);
                if(rating >= 0) {
                    if(rating <= 10) {
                        // send player rating to krim
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean rateserver(String command, Player player, String[] args) {
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                int rating = Integer.valueOf(args[0]);
                if(rating >= 0) {
                    if(rating <= 10) {
                        // send server rating to krim
                        return true;
                    }
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
