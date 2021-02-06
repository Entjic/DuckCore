package de.duckcore.entjic.builder.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

public final class InventoryListener implements Listener {
    private final Plugin plugin;

    public InventoryListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof SimpleInventory && event.getClickedInventory() != null) {
            SimpleInventory inventory = (SimpleInventory) event.getInventory().getHolder();
            boolean wasCancelled = event.isCancelled();
            event.setCancelled(true);
            inventory.handleClick(event);

            if (! wasCancelled && ! event.isCancelled()) {
                event.setCancelled(false);
            }

        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof SimpleInventory) {
            SimpleInventory inventory = (SimpleInventory) event.getInventory().getHolder();
            inventory.handleOpen(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof SimpleInventory) {
            SimpleInventory inventory = (SimpleInventory) event.getInventory().getHolder();
            inventory.handleClose(event);
        }
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() == plugin) {
            closeAll();
        }
    }

    private void closeAll() {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.getOpenInventory().getTopInventory().getHolder() instanceof SimpleInventory)
                .forEach(Player::closeInventory);
    }

}
