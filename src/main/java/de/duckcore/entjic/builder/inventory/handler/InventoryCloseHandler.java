package de.duckcore.entjic.builder.inventory.handler;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface InventoryCloseHandler {
    void onInventoryClose(InventoryCloseEvent event);
}
