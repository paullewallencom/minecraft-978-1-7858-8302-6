package com.codisimus.alwaysday;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AlwaysDay extends JavaPlugin {
    BukkitTask dayLightTask;

    @Override
    public void onDisable() {
        dayLightTask.cancel();
    }

    @Override
    public void onEnable() {
        BukkitRunnable bRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    //Set the time to noon
                    world.setTime(6000);
                }
            }
        };

        //Repeat task every 1200 ticks (1 minute)
        dayLightTask = bbRunnable.runTaskTimer(this, 0, 1200);
    }
}
