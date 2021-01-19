package de.entjic.builder.inventory.dialog;

public class DialogBuilder {
    InventoryDialog root;

    public DialogBuilder(InventoryDialog root){
        this.root = root;
    }

    public DialogBuilder append(InventoryDialog child){
        root.addChild(child);
        return this;
    }

    public InventoryDialog buildDialog(){
        return root;
    }

}
