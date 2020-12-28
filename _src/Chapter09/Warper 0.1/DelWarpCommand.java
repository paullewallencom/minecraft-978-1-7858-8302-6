package com.codisimus.warper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Verify that there are the correct amount of arguments
        if (args.length != 1) {
            return false;
        }

        //Delete the specified Warp
        Warper.delWarp(args[0]);
        sender.sendMessage("Warp deleted!");

        //Return true because the command was executed successfully
        return true;
    }
}
