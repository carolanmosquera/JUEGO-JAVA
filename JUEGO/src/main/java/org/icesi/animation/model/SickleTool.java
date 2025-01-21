package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class SickleTool extends CollisionManager{
    private Image sickle;

    private boolean activeColicion;

    private double integrity;

    private Position position;

    private ArrayList<Image> spriteSickle;
    private int cont;

    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private InventoryManagerTool inventoryManagerTool;

    private InventoryTool inventoryTool;

    private GraphicsContext gc;

    public SickleTool(Canvas canvas, double posionX, double posionY, InventoryManagerTool inventoryManagerTool) {
        super(canvas);
        this.inventoryManagerTool = inventoryManagerTool;

        this.integrity=100;

        this.cont = 0;
        this.spriteSickle = new ArrayList<>();

        this.activeColicion = false;

        this.gc = canvas.getGraphicsContext2D();
        position = new Position(posionX, posionY);

        for (int i = 0; i < 2; i++){
            sickle = new Image(getClass().getResourceAsStream("/animacionesObjetos/animacionHerramienta/gifDeOz/toolOz_000"+i+".png"));
            spriteSickle.add(sickle);
        }

        this.inventoryTool = new InventoryTool("Sickle", 1, integrity, "Favorite tool of a certain hooded man");

        this.posionX = posionX;
        this.posionY = posionY;
        this.ancho = sickle.getWidth();
        this.alto = sickle.getHeight();

        setRangoDeColicionRectangulo(true);
        defineDimension(posionX,posionY, ancho, alto,10);
        setColicionesInferiores(false);

    }
    @Override
    public void paint() {

        if(!activeColicion) {

            gc.drawImage(spriteSickle.get(cont), getPositionX(), getPositionY(), getwidth(), getheight());

            cont++;

            if(cont>1){
                cont=0;
            }
        }

    }

    public InventoryTool getInventoryItem() {
        return inventoryTool;
    }


    public void setActiveColicion(boolean active) {

        this.activeColicion = active;
    }

    public boolean isAlreadyActiveColicion() {
        return activeColicion;
    }

    public void reduceDurability(){
        integrity = integrity - 000.1;
        inventoryTool.setDuration(integrity);
        inventoryManagerTool.updateInventory(inventoryTool);
    }

    public void increaseDurability(){
        integrity = 100;
        inventoryTool.setDuration(integrity);
        inventoryManagerTool.updateInventory(inventoryTool);
    }

    public void setOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case T -> {
                if(activeColicion && integrity>0){
                    reduceDurability();
                }
            }
        }
    }

}
