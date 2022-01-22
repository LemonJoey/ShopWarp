package com.live.lemonjoey.shopwarp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.Objects;

import static com.live.lemonjoey.shopwarp.Main.plugin;

public class Events implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player clicker = event.getPlayer();
        if (!clicker.getWorld().getName().equals("event")) return;
        if (!event.getAction().equals(Action.PHYSICAL)) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType() != Material.STONE_PRESSURE_PLATE) return;
        if (clicker.getLocation().distance(new Location(plugin.getServer().getWorld("event"), 0, 48, -437)) < 2) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.hasPermission("ShopWarp.debug"))
                    player.sendMessage(String.format("testing", clicker
                            .getDisplayName()));
            }
        }
    }
}


