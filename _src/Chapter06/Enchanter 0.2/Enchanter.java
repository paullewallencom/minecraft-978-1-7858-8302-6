package com.codisimus.enchanter;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Enchants the item that the command sender is holding
 */
public class Enchanter extends JavaPlugin {
    @Override
    public void onEnable() {
        //Assign the executor of the enchant command
        getCommand("enchant").setExecutor(new EnchantCommand());
    }
}
