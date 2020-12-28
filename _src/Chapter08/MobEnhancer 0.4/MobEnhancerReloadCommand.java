package com.codisimus.mobenhancer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MobEnhancerReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        MobEnhancer.plugin.reloadConfig();
        sender.sendMessage("MobEnhancer config has been reloaded");
        return true; //The command executed successfully
    }
}
