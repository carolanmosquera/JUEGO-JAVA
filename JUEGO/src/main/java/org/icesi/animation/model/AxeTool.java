package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class AxeTool extends CollisionManager{

    private Image axe;

    private boolean activeColicion;

    private double integrity;

    private Position position;

    private ArrayList<Image> spriteHacha;
    private int cont;

    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private InventoryManagerTool inventoryManagerTool;

    private InventoryTool inventoryTool;

    private GraphicsContext gc;

    public AxeTool(Canvas canvas, double posionX, double posionY, InventoryManagerTool inventoryManagerTool) {
        super(canvas);
        this.inventoryManagerTool = inventoryManagerTool;

        this.integrity=100;

        this.cont = 0;
        this.spriteHacha = new ArrayList<>();

        this.activeColicion = false;

        this.gc = canvas.getGraphicsContext2D();
        position = new Position(posionX, posionY);

        for (int i = 0; i < 2; i++){
            axe = new Image(getClass().getResourceAsStream("/animacionesObjetos/animacionHerramienta/gifDeHacha/animacionGatoHacha_000"+i+".png"));
            spriteHacha.add(axe);
        }

        this.inventoryTool = new InventoryTool("Axe", 1, integrity, "The worst enemy of the ents");

        this.posionX = posionX;
        this.posionY = posionY;
        this.ancho = axe.getWidth();
        this.alto = axe.getHeight();

        setRangoDeColicionRectangulo(true);
        defineDimension(posionX,posionY, ancho, alto,10);
        setColicionesInferiores(false);

    }
    @Override
    public void paint() {

        if(!activeColicion) {

            gc.drawImage(spriteHacha.get(cont), getPositionX(), getPositionY(), getwidth(), getheight());

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
        //System.out.println("funciono 2");
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
