package com.beecub.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.beecub.execute.Ban;
import com.beecub.execute.Comment;
import com.beecub.execute.Friend;
import com.beecub.execute.Note;
import com.beecub.execute.Other;
import com.beecub.execute.Profile;
import com.beecub.execute.Rating;
import com.beecub.execute.Warning;
import com.beecub.execute.Whitelist;
import com.beecub.glizer.glizer;
import com.beecub.util.bChat;
import com.beecub.util.bConfigManager;



public class bCommandRouter {
    public static boolean handleCommands(CommandSender sender, Command c, String commandLabel, String[] args) {
        
        String ret = "disabled";
        String command = c.getName().toLowerCase();
        if(glizer.D) bChat.log("[glizer] Command: " + command);
        
        if(sender instanceof Player) {
            Player player = (Player) sender;
            
            if(command.equals("")) {
                // not used
                return true;
            }
            else if(command.equals("glizerreload")) {
                Other.glizerreload(command, player, args);
                return true;
            }
            else if(command.equals("glizerhelp")) {
                Other.glizerhelp(command, player, args);
                return true;
            }
            else if(command.equals("ban")) {
                if(bConfigManager.usebansystem) {
                    Ban.ban(command, player, args);
                    return true;
                }
            }
            else if(command.equals("globalban")) {
                if(bConfigManager.usebansystem && bConfigManager.useglobalbans) {
                    Ban.globalban(command, player, args);
                    return true;
                }
            }
            else if(command.equals("localban")) {
                if(bConfigManager.usebansystem) {
                    Ban.localBan(command, player, args);
                    return true;
                }
            }
            else if(command.equals("tempban")) {
                if(bConfigManager.usebansystem) {
                    Ban.tempban(command, player, args);
                    return true;
                }
            }
            else if(command.equals("unban")) {
                if(bConfigManager.usebansystem) {
                    Ban.unban(command, player, args);
                    return true;
                }
            }
            else if(command.equals("note")) {
                Note.note(command, player, args);
                return true;
            }
            else if(command.equals("notes")) {
                Note.notes(command, player, args);
                return true;
            }
            else if(command.equals("warn")) {
                Warning.warn(command, player, args);
                return true;
            }
            else if(command.equals("warnings")) {
                Warning.warnings(command, player, args);
                return true;
            }            
            else if(command.equals("register")) {
                Other.register(command, player, args);
                return true;
            }            
            else if(command.equals("comment")) {
                if(bConfigManager.usecomments) {
                    Comment.comment(command, player, args);
                    return true;
                }
            }            
            else if(command.equals("comments")) {
                if(bConfigManager.usecomments) {
                    Comment.comments(command, player, args);
                    return true;
                }
            }
            else if(command.equals("rateplayer")) {
                if(bConfigManager.useratings) {
                    Rating.rateplayer(command, player, args);
                    return true;
                }
            }
            else if(command.equals("rateserver")) {
                if(bConfigManager.useratings) {
                    Rating.rateserver(command, player, args);
                    return true;
                }
            }
            else if(command.equals("profile")) {
                if(bConfigManager.useprofiles) {
                    Profile.profile(command, player, args);
                    return true;
                }
            }
            else if(command.equals("editprofile")) {
                if(bConfigManager.useprofiles) {
                    Profile.editprofile(command, player, args);
                    return true;
                }
            }
            else if(command.equals("clearprofile")) {
                if(bConfigManager.useprofiles) {
                    Profile.clearprofile(command, player, args);
                    return true;
                }
            }
            else if(command.equals("gm")) {
                Other.gm(command, player, args);
                return true;
            }
            else if(command.equals("addfriend")) {
                if(bConfigManager.useprofiles) {
                    Friend.addfriend(command, player, args);
                    return true;
                }
            }
            else if(command.equals("removefriend")) {
                if(bConfigManager.useprofiles) {
                    Friend.removefriend(command, player, args);
                    return true;
                }
            }
            else if(command.equals("friends")) {
                if(bConfigManager.useprofiles) {
                    Friend.friends(command, player, args);
                    return true;
                }
            }
            else if(command.equals("addwhitelist")) {
                Whitelist.whitelistAdd(command, player, args);
                return true;
            }
            else if(command.equals("removewhitelist")) {
                Whitelist.whitelistRemove(command, player, args);
                return true;
            }
            else if(command.equals("theanswertolifetheuniverseandeverything")) {
                Other.theanswertolifetheuniverseandeverything(command, player, args);
                return true;
            }
            else {
                bChat.sendMessageToPlayer(player, "&6Not a glizer command");
                return true;
            }
            if(ret.equalsIgnoreCase("disabled")) {
                bChat.sendMessageToPlayer(player, "&Feature disabled");
                return true;
            }
            else {
                return true;
            }
            
        } else {
            bChat.sendMessageToServer("&6Commands from console: Coming soon");
            return true;
        }
    }
}
