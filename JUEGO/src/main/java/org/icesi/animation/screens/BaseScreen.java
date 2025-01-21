package org.icesi.animation.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import org.icesi.animation.model.InventoryManagerResources;
import org.icesi.animation.model.InventoryManagerTool;

public abstract class BaseScreen {
    protected Canvas canvas;
    protected GraphicsContext gc;
    private InventoryManagerTool inventoryManagerTool;
    private InventoryManagerResources inventoryManagerResources;

    public BaseScreen(Canvas canvas, InventoryManagerTool inventoryManagerTool, InventoryManagerResources inventoryManagerResources) {
        this.canvas = canvas;
        this.inventoryManagerResources=inventoryManagerResources;
        this.inventoryManagerTool = inventoryManagerTool;
        this.gc = canvas.getGraphicsContext2D();
    }

    public abstract void paint();

    public abstract void onKeyPressed(KeyEvent event);

    public abstract void onKeyReleased(KeyEvent event);


}
