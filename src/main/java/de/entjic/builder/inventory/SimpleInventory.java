package de.entjic.builder.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

public class SimpleInventory extends InventoryHandler implements InventoryHolder {
    private final Inventory inventory;

    public SimpleInventory(int inventorySize) {
        this(inventorySize, InventoryType.CHEST.getDefaultTitle());
    }

    public SimpleInventory(int inventorySize, String title) {
        this(inventorySize, InventoryType.CHEST, title);
    }

    public SimpleInventory(InventoryType inventoryType) {
        this(inventoryType, inventoryType.getDefaultTitle());
    }

    public SimpleInventory(InventoryType inventoryType, String title) {
        this(0, inventoryType, title);
    }

    public SimpleInventory(int inventorySize, InventoryType inventoryType, String title) {
        if (inventoryType == InventoryType.CHEST && inventorySize > 0) {
            inventory = Bukkit.createInventory(this, inventorySize, title);
        } else {
            inventory = Bukkit.createInventory(this, Objects.requireNonNull(inventoryType, "type"), title);
        }
    }

    public void setItem(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> handler) {
        inventory.setItem(slot, itemStack);

        if (handler != null) {
            getItemHandlers().put(slot, handler);
        } else {
            getItemHandlers().remove(slot);
        }
    }

    public void setItem(int slot, ItemStack itemStack) {
        setItem(slot, itemStack, null);
    }

    public void setItems(int slotFrom, int slotTo, ItemStack itemStack, Consumer<InventoryClickEvent> handler) {
        for (int slot = slotFrom; slot <= slotTo; slot++) {
            setItem(slot, itemStack, handler);
        }
    }

    public void setItems(int slotFrom, int slotTo, ItemStack itemStack) {
        setItems(slotFrom, slotTo, itemStack, null);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {

    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {

    }

    @Override
    public void onInventoryOpen(InventoryOpenEvent event) {

    }
}