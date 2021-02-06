package de.duckcore.entjic.builder.inventory.dialog;

import de.duckcore.entjic.builder.inventory.SimpleInventory;

public class Dialog extends Node {
    private SimpleInventory inventory;
    private final String title;

    public Dialog(int inventorySize, String title) {
        this(new SimpleInventory(inventorySize, title), title);
    }

    public Dialog(SimpleInventory inventory, String title){
        this.inventory = inventory;
        this.title = title;
    }

    public Dialog(String title){
        this.title = title;
    }

    public SimpleInventory getInventory() {
        return inventory;
    }

    public void setInventory(SimpleInventory inventory) {
        this.inventory = inventory;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public void addChild(Node child) {
        super.addChild(child);
    }

    public Dialog findDialog(Dialog dialog, String find) {
        if (dialog.getTitle().equals(find)) {
            return dialog;
        } else {
            for (Node child : dialog.getChildren()) {
                Dialog result = findDialog((Dialog) child, find);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

}
