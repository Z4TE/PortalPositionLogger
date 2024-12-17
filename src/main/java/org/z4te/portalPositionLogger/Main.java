package org.z4te.portalPositionLogger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public final class Main extends JavaPlugin implements Listener {

    public static HashMap<UUID, Location> portalLocation = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("portal")).setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll();
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        Location location = player.getLocation();

        portalLocation.put(playerId, location);

        // デバッグ用
        //player.performCommand("portal");
    }
}
