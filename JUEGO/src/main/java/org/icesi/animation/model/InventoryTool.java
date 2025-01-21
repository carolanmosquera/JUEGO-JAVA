package org.icesi.animation.model;
//this is a class that is use it like a connection between the object and the table
//tha class organice the information of the object like its attributes and manage them in a way
//that can be use it in the table easier.
public class InventoryTool {
    private String itemName;
    private int quantity;
    private double duration;
    private String description;

    public InventoryTool(String itemName, int quantity, double duration, String description) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.duration = duration;
        this.description = description;
    }

    public  void setDuration(double duration) {
        this.duration=duration;

    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

}