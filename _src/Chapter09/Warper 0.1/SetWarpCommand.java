package com.codisimus.warper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Only Players may create Warps
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        //Verify that there are the correct amount of arguments
        if (args.length != 1) {
            return false;
        }

        //Set the Player's current Location as the Warp
        Warper.setWarp(args[0], player.getLocation());
        player.sendMessage("Warp set!");

        //Return true because the command was executed successfully
        return true;
    }
}
