package com.codisimus.mobenhancer;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MobEnhancer extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        //Register all of the EventHandlers within this class
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            int health = 40;
            event.getEntity().setMaxHealth(health);
            event.getEntity().setHealth(health);
        }
    }
}
