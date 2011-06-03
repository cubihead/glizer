package com.beecub.util;

import org.bukkit.entity.Player;

import com.beecub.glizer.glizer;

public class bPermissions {

    public static boolean checkPermission(Player player, String command) {
        
        String admin = "glizer.admin";
        String moderator = "glizer.moderator";
        String user = "glizer.user";
        
        if(player.isOp()) {
            return true;
        }
        if(command.equalsIgnoreCase("")) {
            if(glizer.Permissions.permission(player, "")) {
                // not used
                return true;
            }
        }
        else if(command.equalsIgnoreCase("ban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.ban")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("globalban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.globalban")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("localban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.localban")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("tempban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.tempban")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("unban")) {
            if(glizer.Permissions.permission(player, "glizer.ban.unban")) {
                return true;
            }
            if(glizer.Permissions.permission(player, admin)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("note")) {
            if(glizer.Permissions.permission(player, "glizer.note.note")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("notes")) {
            if(glizer.Permissions.permission(player, "glizer.note.notes")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("warn")) {
            if(glizer.Permissions.permission(player, "glizer.warning.warn")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("warnings")) {
            if(glizer.Permissions.permission(player, "glizer.warning.warnings")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
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
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("comments")) {
            if(glizer.Permissions.permission(player, "glizer.comment.comments")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("rateplayer")) {
            if(glizer.Permissions.permission(player, "glizer.rate.rateplayer")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("rateserver")) {
            if(glizer.Permissions.permission(player, "glizer.rate.rateserver")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("profile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.profile")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("editprofile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.editprofile")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("clearprofile")) {
            if(glizer.Permissions.permission(player, "glizer.profile.clearprofile")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("gm")) {
            if(glizer.Permissions.permission(player, "glizer.other.gm")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("friends")) {
            if(glizer.Permissions.permission(player, "glizer.friends.friends")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("addfriend")) {
            if(glizer.Permissions.permission(player, "glizer.friends.addfriend")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("removefriend")) {
            if(glizer.Permissions.permission(player, "glizer.friends.removefriend")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("addwhitelist")) {
            if(glizer.Permissions.permission(player, "glizer.whitelist.add")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("removewhitelist")) {
            if(glizer.Permissions.permission(player, "glizer.whitelist.remove")) {
                return true;
            }
            if(glizer.Permissions.permission(player, moderator)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("glizer")) {
            if(glizer.Permissions.permission(player, "glizer.reload")) {
                return true;
            }
            if(glizer.Permissions.permission(player, admin)) {
                return true;
            }
        }
        else if(command.equalsIgnoreCase("glizerhelp")) {
            if(glizer.Permissions.permission(player, "glizer.help")) {
                return true;
            }
            if(glizer.Permissions.permission(player, user)) {
                return true;
            }
        }
        else {
            bChat.sendMessageToPlayer(player, bMessageManager.messagePermissions);
            return false;
        }
        bChat.sendMessageToPlayer(player, bMessageManager.messagePermissions);
        return false;
    }
}
