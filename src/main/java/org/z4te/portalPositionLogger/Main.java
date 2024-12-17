package org.z4te.portalPositionLogger;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World.Environment environment = player.getWorld().getEnvironment();

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        String message = String.format("You went through the portal at " + ChatColor.GREEN + "[%d, %d, %d]" + ChatColor.RESET + " in %s", x, y, z, environment);
        player.sendMessage(message);
    }
}
