package com.codisimus.warper;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Only Players are able to teleport
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        //Verify that there are the correct amount of arguments
        if (args.length != 1) {
            return false;
        }

        //Find the Location of the specified Warp
        Location loc = Warper.getWarp(args[0]);
        if (loc == null) {
            player.sendMessage("Warp " + args[0] + " does not exist");
            //Return true because we do not wish to display the usage message
            return true;
        }

        //Teleport the Player
        player.teleport(loc);

        //Return true because the command was executed successfully
        return true;
    }
}
