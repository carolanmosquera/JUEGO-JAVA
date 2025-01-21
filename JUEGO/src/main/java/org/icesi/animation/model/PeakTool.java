package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class PeakTool extends CollisionManager{
    private Image peak;

    private boolean activeColicion;

    private double integrity;

    private Position position;

    private ArrayList<Image> spritePico;
    private int cont;

    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private InventoryManagerTool inventoryManagerTool;

    private InventoryTool inventoryTool;

    private GraphicsContext gc;

    public PeakTool(Canvas canvas, double posionX, double posionY, InventoryManagerTool inventoryManagerTool) {
        super(canvas);
        this.inventoryManagerTool = inventoryManagerTool;

        this.integrity=100;

        this.cont = 0;
        this.spritePico = new ArrayList<>();

        this.activeColicion = false;

        this.gc = canvas.getGraphicsContext2D();
        position = new Position(posionX, posionY);

        for (int i = 0; i < 2; i++){
            peak = new Image(getClass().getResourceAsStream("/animacionesObjetos/animacionHerramienta/gifDePico/ToolPico_000"+i+".png"));
            spritePico.add(peak);
        }

        this.inventoryTool = new InventoryTool("Peak", 1, integrity, "The tool makes you think about diamonds");

        this.posionX = posionX;
        this.posionY = posionY;
        this.ancho = peak.getWidth();
        this.alto = peak.getHeight();

        setRangoDeColicionRectangulo(true);
        defineDimension(posionX,posionY, ancho, alto,10);
        setColicionesInferiores(false);

    }
    @Override
    public void paint() {

        if(!activeColicion) {

            gc.drawImage(spritePico.get(cont), getPositionX(), getPositionY(), getwidth(), getheight());

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
