package com.codisimus.norain;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoRain extends JavaPlugin implements Listener {
    //This is a variable that our two methods will use to "communicate" with each other
    private boolean denyRain = true;

    @Override
    public void onEnable() {
        //Register all of the EventHandlers within this class
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.LOW)
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) { //Rain is trying to turn on
            //Cancel the event if denyRain is set to true
            event.setCancelled(denyRain);
        }
        //Reset the denyRain value until next time a Player issues the /toggledownfall command
        denyRain = true;
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        //Check if the Player is attempting to change the weather
        if (event.getMessage().startsWith("/toggledownfall")) {
            //Verify that the Player has permission to change the weather
            if (event.getPlayer().hasPermission("minecraft.command.toggledownfall")) {
                //Allow the Rain to start for this occasion
                denyRain = false;
            }
        }
    }
}
