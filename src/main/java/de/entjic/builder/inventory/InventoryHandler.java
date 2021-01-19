package de.entjic.builder.inventory;

import de.entjic.builder.inventory.handler.InventoryClickHandler;
import de.entjic.builder.inventory.handler.InventoryCloseHandler;
import de.entjic.builder.inventory.handler.InventoryOpenHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class InventoryHandler implements InventoryClickHandler, InventoryCloseHandler, InventoryOpenHandler {
    private final Map<Integer, Consumer<InventoryClickEvent>> itemHandlers = new HashMap<>();

    private Set<Consumer<InventoryOpenEvent>> inventoryOpenHandlers;
    private Set<Consumer<InventoryCloseEvent>> inventoryCloseHandlers;
    private Set<Consumer<InventoryClickEvent>> inventoryClickHandlers;

    public void addOpenHandler(Consumer<InventoryOpenEvent> inventoryOpenEventConsumer) {
        if (inventoryOpenHandlers == null) {
            inventoryOpenHandlers = new HashSet<>();
        }
        inventoryOpenHandlers.add(inventoryOpenEventConsumer);
    }

    public void addCloseHandler(Consumer<InventoryCloseEvent> inventoryCloseEventConsumer) {
        if (inventoryCloseHandlers == null) {
            inventoryCloseHandlers = new HashSet<>();
        }
        inventoryCloseHandlers.add(inventoryCloseEventConsumer);
    }

    public void addClickHandler(Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        if (inventoryClickHandlers == null) {
            inventoryClickHandlers = new HashSet<>();
        }
        inventoryClickHandlers.add(inventoryClickEventConsumer);
    }

    public Map<Integer, Consumer<InventoryClickEvent>> getItemHandlers() {
        return itemHandlers;
    }

    public Set<Consumer<InventoryClickEvent>> getInventoryClickHandlers() {
        return inventoryClickHandlers;
    }

    public Set<Consumer<InventoryCloseEvent>> getInventoryCloseHandlers() {
        return inventoryCloseHandlers;
    }

    public Set<Consumer<InventoryOpenEvent>> getInventoryOpenHandlers() {
        return inventoryOpenHandlers;
    }


    void handleClick(InventoryClickEvent event) {
        onInventoryClick(event);

        if (inventoryClickHandlers != null) {
            inventoryClickHandlers.forEach(c -> c.accept(event));
        }

        Consumer<InventoryClickEvent> clickConsumer = itemHandlers.get(event.getRawSlot());

        if (clickConsumer != null) {
            clickConsumer.accept(event);
        }
    }

    void handleOpen(InventoryOpenEvent event) {
        onInventoryOpen(event);

        if (inventoryOpenHandlers != null) {
            inventoryOpenHandlers.forEach(c -> c.accept(event));
        }
    }

    void handleClose(InventoryCloseEvent event) {
        onInventoryClose(event);

        if (inventoryCloseHandlers != null) {
            inventoryCloseHandlers.forEach(c -> c.accept(event));
        }

    }
}
