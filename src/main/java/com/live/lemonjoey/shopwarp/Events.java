package com.live.lemonjoey.shopwarp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.Objects;
import java.util.Random;

import static com.live.lemonjoey.shopwarp.Main.plugin;

public class Events implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player clicker = event.getPlayer();
        if (!clicker.getWorld().getName().equals("event")) return;
        if (!event.getAction().equals(Action.PHYSICAL)) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType() != Material.STONE_PRESSURE_PLATE) return;
        if (clicker.getLocation().distance(new Location(plugin.getServer().getWorld("event"), 0, 48, -437)) < 2) {
            float minX = 11;
            float maxX = 15;
            float minZ = -435;
            float maxZ = -259;
            float minYAW = -180;
            float maxYAW = 180;

            if (new Random().nextBoolean()){
                minX -=24;
                maxX -=24;
            }

            float[] result=new float[4];
            result[0]= (float) (Math.random()*(maxX-minX)+minX);
            result[1]=50;
            result[2]= (float) (Math.random()*(maxZ-minZ)+minZ);
            result[3]= (float) (Math.random()*(maxYAW-minYAW)+minYAW);

            clicker.teleport(new Location(event.getPlayer().getWorld(), result[0], result[1], result[2], result[3], 0));
        }
    }
}


