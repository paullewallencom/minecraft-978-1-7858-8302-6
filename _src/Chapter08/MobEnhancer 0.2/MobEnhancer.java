package com.codisimus.mobenhancer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MobEnhancer extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    public void onEnable() {
        //Save the default config file if it does not already exist
        saveDefaultConfig();

        //Register all of the EventHandlers within this class
        getServer().getPluginManager().registerEvents(this, this);

        //Register this class as the Executor of the /merl command
        getCommand("mobenhancerreload").setExecutor(this);
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        //Find the type of the Entity that spawned
        String type = event.getEntityType().name();

        //Retrieve the custom health amount for the EntityType
        //This will be 0 if the EntityType is not included in the config
        double health = getConfig().getDouble(type);

        //Mobs cannot have negative health
        if (health > 0) {
            event.getEntity().setMaxHealth(health);
            event.getEntity().setHealth(health);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
    String alias, String[] args) {
        reloadConfig();
        sender.sendMessage("MobEnhancer config has been reloaded");
        return true; //The command was executed successfully
    }
}
