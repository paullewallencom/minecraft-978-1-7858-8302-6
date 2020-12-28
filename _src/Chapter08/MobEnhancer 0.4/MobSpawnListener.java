package com.codisimus.mobenhancer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class MobSpawnListener implements Listener {
    static boolean giveArmorToMobs;
    static ItemStack zombieHolding;
    static ItemStack zombieHelmet;
    static ItemStack zombieChestplate;
    static ItemStack zombieLeggings;
    static ItemStack zombieBoots;
    static ItemStack skeletonHolding;
    static ItemStack skeletonHelmet;
    static ItemStack skeletonChestplate;
    static ItemStack skeletonLeggings;
    static ItemStack skeletonBoots;

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        //Find the type of the Entity that spawned
        String type = event.getEntityType().name();

        //Retrieve the custom health amount for the EntityType
        //This will be 0 if the EntityType is not included in the config
        double health = MobEnhancer.plugin.getConfig().getDouble(type);

        //Set the new health amount for the mob as long as it is positive
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
}
