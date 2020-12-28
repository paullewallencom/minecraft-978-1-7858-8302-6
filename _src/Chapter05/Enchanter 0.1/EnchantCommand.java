package com.codisimus.enchanter;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Enchants the item that the command sender is holding
 */
public class EnchantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String alias, String[] args) {
        //This command can only be executed by Players
        if (!(sender instanceof Player)) {
            return false;
        }

        //Cast the command sender to a Player
        Player player = (Player) sender;

        //Retrieve the ItemStack that the Player is holding
        ItemStack hand = player.getItemInHand();

        //Return if the Player is not holding an Item
        if (hand == null || hand.getType() == Material.AIR) {
            return false;
        }

        //Add a level 10 Knockback enchantment
        hand.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);

        //Add a level 1 Fire Aspect enchantment
        hand.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);

        player.sendMessage("Your item has been enchanted!");
        return true;
    }

}
