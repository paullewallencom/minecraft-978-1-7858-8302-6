package com.codisimus.myfirstbukkitplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Broadcasts a hello message to the server
 */
public class MyFirstBukkitPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        if (Bukkit.getOnlinePlayers().size() >= 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                //Only say 'Hello' to each player that has permission
                if (player.hasPermission("myfirstbukkitplugin.greeting")) {
                    player.sendMessage("Hello " + player.getName());
                }
            }
        } else {
            //Say 'Hello' to the Minecraft World
            broadcastToServer("Hello World!");
        }
    }

    /**
     * Sends a message to everyone on the server
     *
     * @param msg the message to send
     */
    private void broadcastToServer(String msg) {
        Bukkit.broadcastMessage(msg);
    }
}
