package de.entjic.builder.inventory.dialog;

import de.entjic.builder.inventory.SimpleInventory;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.ArrayList;
import java.util.List;

public class InventoryDialog extends SimpleInventory{
    private InventoryDialog parent;
    private final List<InventoryDialog> children = new ArrayList<>();
    private int id;

    public InventoryDialog(int id, int inventorySize) {
        super(inventorySize);
        this.id = id;
        parent = null;
    }

    public void addChild(InventoryDialog child) {
        child.setParent(this);
        children.add(child);
    }

    public void addChildren(List<InventoryDialog> children) {
        children.forEach(child -> child.setParent(this));
        this.children.addAll(children);
    }

    private void setParent(InventoryDialog parent) {
        this.parent = parent;
    }

    public List<InventoryDialog> getChildren() {
        return children;
    }

    public InventoryDialog getParent() {
        return parent;
    }

    public InventoryDialog getRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.getRoot();
        }
    }

    public int getId() {
        return id;
    }

}
