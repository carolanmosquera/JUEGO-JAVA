package org.icesi.animation.model;
//this is a class that is use it like a connection between the object and the table
//tha class organice the information of the object like its attributes and manage them in a way
//that can be use it in the table easier.
public class InventoryItem {
    private String itemName;
    private int quantity;

    public InventoryItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
