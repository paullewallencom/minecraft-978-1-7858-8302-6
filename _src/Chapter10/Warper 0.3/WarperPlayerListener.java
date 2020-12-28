package com.codisimus.warper;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WarperPlayerListener implements Listener {
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerUuid = player.getUniqueId().toString();

        //We only care about this event if the player is flagged as warping
        if (Warper.isWarping(playerUuid)) {
            //Compare the block locations rather than the player locations
            //This allows a player to move their head without canceling the warp
            Block blockFrom = event.getFrom().getBlock();
            Block blockTo = event.getTo().getBlock();

            //Cancel the warp if the player moves to a different block
            if (!blockFrom.equals(blockTo)) {
                Warper.cancelWarp(playerUuid);
                player.sendMessage("Warping canceled because you moved!");
            }
        }
    }
}
