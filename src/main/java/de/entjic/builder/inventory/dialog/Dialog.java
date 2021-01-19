package de.entjic.builder.inventory.dialog;

public class Dialog extends Node{
    private final DialogInventory inventory;

    public Dialog(int id, int inventorySize, String title){
        inventory = new DialogInventory(id, inventorySize, title);
    }

    public DialogInventory getInventory() {
        return inventory;
    }

    @Override
    public void addChild(Node child) {
        super.addChild(child);
    }


}
