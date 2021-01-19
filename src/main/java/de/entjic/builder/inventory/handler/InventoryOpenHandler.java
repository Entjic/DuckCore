package de.entjic.builder.inventory.handler;

import org.bukkit.event.inventory.InventoryOpenEvent;

public interface InventoryOpenHandler {
    void onInventoryOpen(InventoryOpenEvent event);
}
