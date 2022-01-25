package com.live.lemonjoey.shopwarp;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.live.lemonjoey.shopwarp.Main.plugin;

public class Events implements Listener {

    private static final Component topLine = Component.text("[Warp]").color(TextColor.color(9,2,169)).asComponent();
    private static final String creationTopLine = "[ShopWarp]";

    /*
    Format
    ============
    §1[Warp]
    shops
    &aEveryone

    ============
     */

    @EventHandler
    public void onSignPlace(@NotNull SignChangeEvent event) {
        // TODO: 1/21/2022 Check back later and see if not deprecated method works
        String[] lines = event.getLines();
        if (lines[0].equalsIgnoreCase(creationTopLine)) {
            if (event.getPlayer().hasPermission("shopwarp.createsign")) {
                event.line(0, topLine);
                event.line(1, Component.text("Shops"));
                event.line(2, Component.text("Everyone").color(TextColor.color(43520)));
            } else {
                event.line(0, Component.empty());
                event.line(1, Component.empty());
                event.line(2, Component.empty());
                event.line(3, Component.empty());
            }
        }

    }

    @EventHandler
    public void onSignClick(@NotNull PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getState() instanceof Sign sign) {
            if (sign.line(0).equals(topLine)) {
                Player player = event.getPlayer();

                float minX = 11;
                float maxX = 15;
                float minZ = -435;
                float maxZ = -259;
                float minYAW = -180;
                float maxYAW = 180;

                if (new Random().nextBoolean()) {
                    minX -= 24;
                    maxX -= 24;
                }

                float[] result = new float[4];
                result[0] = (float) (Math.random() * (maxX - minX) + minX);
                result[1] = 50;
                result[2] = (float) (Math.random() * (maxZ - minZ) + minZ);
                result[3] = (float) (Math.random() * (maxYAW - minYAW) + minYAW);

                player.teleport(new Location(plugin.getServer().getWorld("event"), result[0], result[1], result[2], result[3], 0));
            }
        }
    }
}


