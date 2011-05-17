package execute;

import org.bukkit.entity.Player;

import util.bChat;
import util.bConfigManager;
import util.bPermissions;

import com.beecub.glizer.glizer;

public class Ban {
    
    public static boolean ban(String command, Player player, String[] args) {
        
        return true;
    }
    
    public static boolean globalban(String command, Player player, String[] args) {
        
        return true;
    }
    
    public static boolean localBan(String command, Player player, String[] args) {
        
        return true;
    }
    
    public static boolean tempban(String command, Player player, String[] args) {
        
        return true;
    }
    
    public static boolean unban(String command, Player player, String[] args) {
        
        return true;
    }
    
    public static boolean addbanwhitelist(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bConfigManager.addbanwhitelist(name)) {
                    bChat.sendMessageToPlayer(player, "Completed.");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "Requested player is already on ban whitelist.");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
    
    public static boolean removebanwhitelist(String command, Player player, String[] args) {
        
        if(bPermissions.checkPermission(player, command)) {
            if(args.length == 1) {
                String name = args[0];
                if(bConfigManager.removebanwhitelist(name)) {
                    bChat.sendMessageToPlayer(player, "Completed.");
                    return true;
                }
                else {
                    bChat.sendMessageToPlayer(player, "Requested player is not on ban whitelist!");
                    return true;
                }
            }
            bChat.sendMessageToPlayer(player, glizer.messageWrongCommandUsage);
            return true;
        }
        return true;
    }
}
