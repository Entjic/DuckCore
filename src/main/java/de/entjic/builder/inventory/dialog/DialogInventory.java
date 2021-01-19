package de.entjic.builder.inventory.dialog;

import de.entjic.builder.inventory.SimpleInventory;


public class DialogInventory extends SimpleInventory {
    private final int id;
    private final String title;

    public DialogInventory(int id, int inventorySize, String title) {
        super(inventorySize, title);
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
