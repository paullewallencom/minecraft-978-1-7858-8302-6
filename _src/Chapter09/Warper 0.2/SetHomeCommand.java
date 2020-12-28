package com.codisimus.warper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Only Players may have homes
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        //Verify that there are the correct amount of arguments
        if (args.length != 0) {
            return false;
        }

        //Set the Player's current Location as their home
        Warper.setHome(player.getName(), player.getLocation());
        player.sendMessage("Home set!");

        //Return true because the command was executed successfully
        return true;
    }
}
