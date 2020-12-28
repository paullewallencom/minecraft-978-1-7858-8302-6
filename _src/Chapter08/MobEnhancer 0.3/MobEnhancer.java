package com.codisimus.mobenhancer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MobEnhancer extends JavaPlugin implements Listener, CommandExecutor {
    private boolean giveArmorToMobs;
    private ItemStack zombieHolding;
    private ItemStack zombieHelmet;
    private ItemStack zombieChestplate;
    private ItemStack zombieLeggings;
    private ItemStack zombieBoots;
    private ItemStack skeletonHolding;
    private ItemStack skeletonHelmet;
    private ItemStack skeletonChestplate;
    private ItemStack skeletonLeggings;
    private ItemStack skeletonBoots;

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

        if (giveArmorToMobs) {
            //Retrieve the equipment object of the Entity
            EntityEquipment equipment = event.getEntity().getEquipment();

            //Check the type of the Entity to give it the correct armor
            switch (event.getEntityType()) {
            case ZOMBIE:
                //Set each piece of equipment that is not null
                if (zombieHolding != null) {
                    equipment.setItemInHand(zombieHolding.clone());
                }
                if (zombieHelmet != null) {
                    equipment.setHelmet(zombieHelmet.clone());
                }
                if (zombieChestplate != null) {
                    equipment.setChestplate(zombieChestplate.clone());
                }
                if (zombieLeggings != null) {
                    equipment.setLeggings(zombieLeggings.clone());
                }
                if (zombieBoots != null) {
                    equipment.setBoots(zombieBoots.clone());
                }
                break;

            case SKELETON:
                //Set each piece of equipment that is not null
                if (skeletonHolding != null) {
                    equipment.setItemInHand(skeletonHolding.clone());
                }
                if (skeletonHelmet != null) {
                    equipment.setHelmet(skeletonHelmet.clone());
                }
                if (skeletonChestplate != null) {
                    equipment.setChestplate(skeletonChestplate.clone());
                }
                if (skeletonLeggings != null) {
                    equipment.setLeggings(skeletonLeggings.clone());
                }
                if (skeletonBoots != null) {
                    equipment.setBoots(skeletonBoots.clone());
                }
                break;

            default: //Any other EntityType
                //Do nothing
                break;
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
    String alias, String[] args) {
        reloadConfig();
        sender.sendMessage("MobEnhancer config has been reloaded");
        return true; //The command was executed successfully
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
        giveArmorToMobs = getConfig().getBoolean("GiveArmorToMobs");
        zombieHolding = getConfig().getItemStack("Zombie.holding");
        zombieHelmet = getConfig().getItemStack("Zombie.helmet");
        zombieChestplate = getConfig().getItemStack("Zombie.chestplate");
        zombieLeggings = getConfig().getItemStack("Zombie.leggings");
        zombieBoots = getConfig().getItemStack("Zombie.boots");
        skeletonHolding = getConfig().getItemStack("Skeleton.holding");
        skeletonHelmet = getConfig().getItemStack("Skeleton.helmet");
        skeletonChestplate = getConfig().getItemStack("Skeleton.chestplate");
        skeletonLeggings = getConfig().getItemStack("Skeleton.leggings");
        skeletonBoots = getConfig().getItemStack("Skeleton.boots");
    }
}
