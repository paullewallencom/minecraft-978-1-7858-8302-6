package com.codisimus.warper;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Warper extends JavaPlugin {
    //Static plugin reference to allow access from other classes.
    static JavaPlugin plugin;

    private static HashMap<String, SerializableLocation> homes = new HashMap<>(); //Player Name -> Warp Location
    private static HashMap<String, SerializableLocation> warps = new HashMap<>(); //Warp Name -> Warp Location

    @Override
    public void onEnable() {
        //Instantiate static JavaPlugin variable
        plugin = this;

        //Register all the ConfigurationSerializable classes
        ConfigurationSerialization.registerClass(SerializableLocation.class, "WarperLocation");

        //Register all the Command Executors
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());

        //Load all saved warp/home locations
        load();
    }

    /**
     * Loads warp names/locations from warps.yml
     * 'warp' refers to both homes and public warps
     */
    private static void load() {
        try {
            //Ensure that the file exists before attempting to load it
            File file = new File(plugin.getDataFolder(), "warps.yml");
            if (file.exists()) {
                //Load the file as a YAML Configuration
                YamlConfiguration config = new YamlConfiguration();
                config.load(file);

                //Get the homes section which is our saved hash map of homes
                //Each key is the uuid of the Player
                //Each value is the location of their home
                ConfigurationSection section = config.getConfigurationSection("homes");
                for (String key: section.getKeys(false)) {
                    //Get the location for each key
                    SerializableLocation loc = (SerializableLocation)
                    section.get(key);
                    //Only add the warp location if it is valid
                    if (loc.getLocation() != null) {
                        homes.put(key, loc);
                    }
                }

                //Get the warps section which is our saved hash map of warps
                //Each key is the name of the warp
                //Each value is the warp location
                section = config.getConfigurationSection("warps");
                for (String key: section.getKeys(false)) {
                    //Get the location for each key
                    SerializableLocation loc = (SerializableLocation) 
                    section.get(key);
                    //Only add the warp location if it is valid
                    if (loc.getLocation() != null) {
                        warps.put(key, loc);
                    }
                }
            }
        } catch (Exception loadFailed) {
            plugin.getLogger().log(Level.SEVERE, "Load Failed!",
            loadFailed);
        }
    }

    /**
     * Saves our HashMaps of warp locations so that they may be loaded later
     */
    private static void save() {
        try {
            //Create a new YAML configuration
            YamlConfiguration config = new YamlConfiguration();

            //Add each of our hashmaps to the config by creating sections
            config.createSection("homes", homes);
            config.createSection("warps", warps);

            //Write the configuration to our save file
            config.save(new File(plugin.getDataFolder(), "warps.yml"));
        } catch (Exception saveFailed) {
            plugin.getLogger().log(Level.SEVERE, "Save Failed!", saveFailed);
        }
    }

    /**
     * Returns the Location of the given player's home or null if they do not have a home
     *
     * @param player The given Player
     * @return The Location of the player's home
     */
    public static Location getHome(String player) {
        //Check if the player has a home
        if (homes.containsKey(player)) {
            //Return the location of the player's home
            return homes.get(player).getLocation();
        } else {
            return null;
        }
    }

    /**
     * Returns the Location of the given warp or null if it does not exist
     *
     * @param name The name of the given warp
     * @return The Location of the warp
     */
    public static Location getWarp(String name) {
        //Check if the warp exists
        if (warps.containsKey(name)) {
            //Return the warp's location
            return warps.get(name).getLocation();
        } else {
            return null;
        }
    }

    /**
     * Sets the home location of the given player
     * A serializable location is created to allow for saving
     *
     * @param player The given Player
     * @param loc The new home location
     */
    public static void setHome(String player, Location loc) {
        homes.put(player, new SerializableLocation(loc));
        save(); //The data has been modified
    }

    /**
     * Sets the location of the given warp
     * A serializable location is created to allow for saving
     *
     * @param name The name of the given warp
     * @param loc The new warp location
     */
    public static void setWarp(String name, Location loc) {
        warps.put(name, new SerializableLocation(loc));
        save(); //The data has been modified
    }

    /**
     * Deletes the specified warp
     *
     * @param name The name of the warp to delete
     */
    public static void delWarp(String name) {
        warps.remove(name);
        save(); //The data has been modified
    }
}
