package de.duckcore.entjic.builder.inventory;

import de.duckcore.entjic.builder.inventory.handler.InventoryClickHandler;
import de.duckcore.entjic.builder.inventory.handler.InventoryCloseHandler;
import de.duckcore.entjic.builder.inventory.handler.InventoryOpenHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class InventoryHandler implements InventoryClickHandler, InventoryCloseHandler, InventoryOpenHandler {
    private final Map<Integer, Consumer<InventoryClickEvent>> itemHandlers = new HashMap<>();

    public Map<Integer, Consumer<InventoryClickEvent>> getItemHandlers() {
        return itemHandlers;
    }


    void handleClick(InventoryClickEvent event) {
        onInventoryClick(event);
        Consumer<InventoryClickEvent> clickConsumer = itemHandlers.get(event.getRawSlot());

        if (clickConsumer != null) {
            clickConsumer.accept(event);
        }
    }

    void handleOpen(InventoryOpenEvent event) {
        onInventoryOpen(event);
    }

    void handleClose(InventoryCloseEvent event) {
        onInventoryClose(event);
    }
}
