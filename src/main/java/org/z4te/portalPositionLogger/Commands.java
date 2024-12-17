package org.z4te.portalPositionLogger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static org.z4te.portalPositionLogger.Main.portalLocation;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("portal")) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be executed by players");
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Too many arguments");
                return true;
            }

            UUID playerId = player.getUniqueId();

            if (portalLocation.containsKey(playerId)) {
                Location location = portalLocation.get(playerId);
                World.Environment environment = player.getWorld().getEnvironment();

                int x = location.getBlockX();
                int y = location.getBlockY();
                int z = location.getBlockZ();

                String message = String.format("You went through the portal at " + ChatColor.GREEN + "[%d, %d, %d]" + ChatColor.RESET + " in %s", x, y, z, environment);
                player.sendMessage(message);
            } else {
                player.sendMessage("You have never traveled between dimensions!");
            }
            return true;
        }

        return false;
    }
}
