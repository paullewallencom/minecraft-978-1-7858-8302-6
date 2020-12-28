package com.codisimus.warper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Only Players have homes
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;

        //Verify that there are the correct amount of arguments
        if (args.length != 0) {
            return false;
        }

        //Find the Location of the Player's home
        Location loc = Warper.getHome(player.getName());
        if (loc == null) {
            //Send the Player to the Spawn because they do not yet have a home
            loc = Bukkit.getWorlds().get(0).getSpawnLocation();
        }

        //Teleport the Player
        player.teleport(loc);

        //Return true because the command was executed successfully
        return true;
    }
}
