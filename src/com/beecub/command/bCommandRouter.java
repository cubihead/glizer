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
import com.beecub.util.bChat;



public class bCommandRouter {
    public static boolean handleCommands(CommandSender sender, Command c, String commandLabel, String[] args) {
        
        String command = c.getName().toLowerCase();
        //glizer.log.info("command: " + command);
        
        if(sender instanceof Player) {
            Player player = (Player) sender;
            
            if(command.equals("")) {
                // not used
                return true;
            }
            else if(command.equals("glizer")) {
                Other.glizer(command, player, args);
                return true;
            }
            else if(command.equals("glizerhelp")) {
                Other.glizerhelp(command, player, args);
                return true;
            }
            else if(command.equals("ban")) {
                Ban.ban(command, player, args);
                return true;
            }
            else if(command.equals("globalban")) {
                Ban.globalban(command, player, args);
                return true;
            }
            else if(command.equals("localban")) {
                Ban.localBan(command, player, args);
                return true;
            }
            else if(command.equals("tempban")) {
                Ban.tempban(command, player, args);
                return true;
            }
            else if(command.equals("unban")) {
                Ban.unban(command, player, args);
                return true;
            }
            else if(command.equals("addbanwhitelist")) {
                Ban.addbanwhitelist(command, player, args);
                return true;
            }
            else if(command.equals("removebanwhitelist")) {
                Ban.removebanwhitelist(command, player, args);
                return true;
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
                Comment.comment(command, player, args);
                return true;
            }            
            else if(command.equals("comments")) {
                Comment.comments(command, player, args);
                return true;
            }
            else if(command.equals("rateplayer")) {
                Rating.rateplayer(command, player, args);
                return true;
            }
            else if(command.equals("rateserver")) {
                Rating.rateserver(command, player, args);
                return true;
            }
            else if(command.equals("profile")) {
                Profile.profile(command, player, args);
                return true;
            }
            else if(command.equals("editprofile")) {
                Profile.editprofile(command, player, args);
                return true;
            }
            else if(command.equals("clearprofile")) {
                Profile.clearprofile(command, player, args);
                return true;
            }
            else if(command.equals("gm")) {
                Other.gm(command, player, args);
                return true;
            }
            else if(command.equals("addfriend")) {
                Friend.addfriend(command, player, args);
                return true;
            }
            else if(command.equals("removefriend")) {
                Friend.removefriend(command, player, args);
                return true;
            }
            else if(command.equals("friends")) {
                Friend.friends(command, player, args);
                return true;
            }
            else {
                bChat.sendMessageToPlayer(player, "Not a glizer command");
                return true;
            }
            
        } else {
            bChat.sendMessageToServer("&6Cannot use this command from console");
            return true;
        }
    }
}
