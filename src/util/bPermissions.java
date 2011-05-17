package util;

import org.bukkit.entity.Player;

import com.beecub.glizer.glizer;

public class bPermissions {

    public static boolean checkPermission(Player player, String command) {
        if(player.isOp()) {
            return true;
        }
        if(command.equalsIgnoreCase("")) {
            if(glizer.Permissions.permission(player, "glizer")) {
                // not used
                return true;
            }
        }
        else if(command.equalsIgnoreCase("ban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.ban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("globalban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.localban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("localban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.localban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("tempban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.tempban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("addbanwhitelist")) {
            if(glizer.Permissions.permission(player, "glizer.ban.banwhitelist")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("removebanwhitelist")) {
            if(glizer.Permissions.permission(player, "glizer.ban.banwhitelist")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("unban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.unban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("note")) {
            if(glizer.Permissions.permission(player, "glizer.moderator.note")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("notes")) {
            if(glizer.Permissions.permission(player, "glizer.moderator.notes")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("warn")) {
            if(glizer.Permissions.permission(player, "glizer.moderator.warn")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("warnings")) {
            if(glizer.Permissions.permission(player, "glizer.moderator.warnings")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("register")) {
            if(glizer.Permissions.permission(player, "glizer.other.register")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("comment")) {
            if(glizer.Permissions.permission(player, "glizer.comment.comment")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("comments")) {
            if(glizer.Permissions.permission(player, "glizer.comment.comments")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("rateplayer")) {
            if(glizer.Permissions.permission(player, "glizer.rate.rateplayer")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("rateserver")) {
            if(glizer.Permissions.permission(player, "glizer.rate.rateserver")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("profile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.profile")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("editprofile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.editprofile")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("clearprofile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.clearprofile")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("gm")) {
            if(glizer.Permissions.permission(player, "glizer.other.gm")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("friends")) {
            if(glizer.Permissions.permission(player, "glizer.friends.friends")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("addfriend")) {
            if(glizer.Permissions.permission(player, "glizer.ban.unban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("removefriend")) {
            if(glizer.Permissions.permission(player, "glizer.ban.unban")) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("glizer")) {
            if(glizer.Permissions.permission(player, "glizer.admin.reload")) {
                return true;
            }
        }
        else {
            bChat.sendMessageToPlayer(player, glizer.messagePermissions);
            return false;
        }
        bChat.sendMessageToPlayer(player, glizer.messagePermissions);
        return false;
    }
}
