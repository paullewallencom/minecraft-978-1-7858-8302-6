package com.codisimus.mobenhancer;

import org.bukkit.plugin.java.JavaPlugin;

public class MobEnhancer extends JavaPlugin {
    //Static plugin reference to allow access from other classes.
    static JavaPlugin plugin;

    @Override
    public void onEnable() {
        //Instantiate static JavaPlugin variable
        plugin = this;

        //Save the default config file if it does not already exist
        saveDefaultConfig();

        //Register all of the EventHandlers
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);

        //Register the Executor of the /mobenhancerreload command
        getCommand("mobenhancerreload").setExecutor(new MobEnhancerReloadCommand());
    }

    /**
     * Reloads the config from the config.yml file
     * Loads values from the newly loaded config
     * This method is automatically called when the plugin is enabled
     */
    @Override
    public void reloadConfig() {
        //Reload the config as this method would normally do if not overridden
        super.reloadConfig();

        //Load values from the config now that it has been reloaded
        MobSpawnListener.giveArmorToMobs = getConfig().getBoolean("GiveArmorToMobs");
        MobSpawnListener.zombieHolding = getConfig().getItemStack("Zombie.holding");
        MobSpawnListener.zombieHelmet = getConfig().getItemStack("Zombie.helmet");
        MobSpawnListener.zombieChestplate = getConfig().getItemStack("Zombie.chestplate");
        MobSpawnListener.zombieLeggings = getConfig().getItemStack("Zombie.leggings");
        MobSpawnListener.zombieBoots = getConfig().getItemStack("Zombie.boots");
        MobSpawnListener.skeletonHolding = getConfig().getItemStack("Skeleton.holding");
        MobSpawnListener.skeletonHelmet = getConfig().getItemStack("Skeleton.helmet");
        MobSpawnListener.skeletonChestplate = getConfig().getItemStack("Skeleton.chestplate");
        MobSpawnListener.skeletonLeggings = getConfig().getItemStack("Skeleton.leggings");
        MobSpawnListener.skeletonBoots = getConfig().getItemStack("Skeleton.boots");
    }
}
