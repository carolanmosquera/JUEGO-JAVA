package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.Random;

public class ResourcesSmall extends CollisionManager{
    private Image spriteItem;

    private boolean active;

    private GraphicsContext gc;

    private InventoryManagerResources inventoryManagerResources;

    private Avatar avatar;

    private Position position;

    private int durabilidad;

    private boolean destroyIt;

    private String requiredTool;

    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private CheckGeneralColisions checkGeneralColisions;

    private InventoryItem inventoryItem;


    public ResourcesSmall(Canvas canvas, int numRamdom, double posionX, double posionY, Avatar avatar, InventoryManagerResources inventoryManagerResources){
        super(canvas);
        this.avatar = avatar;

        this.inventoryManagerResources = inventoryManagerResources;

        this.active = false;
        this.destroyIt = false;

        this.gc = canvas.getGraphicsContext2D();
        position = new Position(posionX, posionY);

        Random randomQuantity = new Random();

        switch (numRamdom){
            case 1:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/bayas.png"));
                this.durabilidad=3;
                //recuerda cambiar a oz
                this.requiredTool="oz";
                this.inventoryItem = new InventoryItem("berries", randomQuantity.nextBoolean() ? 1 : 3);
                break;
            case 2:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/fresas.png"));
                this.durabilidad=4;
                this.requiredTool="oz";
                this.inventoryItem = new InventoryItem("strawberries", randomQuantity.nextBoolean() ? 2 : 5);
                break;
            case 3:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/hongos.png"));
                this.durabilidad=3;
                this.requiredTool="oz";
                this.inventoryItem = new InventoryItem("fungus", randomQuantity.nextBoolean() ? 1 : 4);
                break;
            case 4:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/melocotones.png"));
                this.durabilidad=5;
                this.requiredTool="oz";
                this.inventoryItem = new InventoryItem("peaches", randomQuantity.nextBoolean() ? 2 : 3);
                break;
            case 5:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/panes.png"));
                this.durabilidad=4;
                this.requiredTool="oz";
                this.inventoryItem = new InventoryItem("breads", randomQuantity.nextBoolean() ? 3 : 7);
                break;
            case 6:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/piedra.png"));
                this.durabilidad=8;
                this.requiredTool="pico";
                this.inventoryItem = new InventoryItem("rocks", randomQuantity.nextBoolean() ? 1 : 4);
                break;
            default:
                spriteItem = new Image(getClass().getResourceAsStream("/animacionesObjetos/spriteRecursos/piedra.png"));
                this.durabilidad=8;
                this.requiredTool="pico";
                this.inventoryItem = new InventoryItem("rocks", randomQuantity.nextBoolean() ? 1 : 4);
                break;
        }

        this.posionX = posionX;
        this.posionY = posionY;
        this.ancho = spriteItem.getWidth();
        this.alto = spriteItem.getHeight();
        this.ancho = ancho* 1.33;
        this.alto = alto* 1.33;

        setRangoDeColicionRectangulo(true);
        defineDimension(posionX,posionY,ancho,alto,5);
        setColicionesInferiores(false);

        this.checkGeneralColisions=new CheckGeneralColisions(avatar,this,canvas);

    }
    @Override
    public void paint() {
        if(durabilidad>0) {
            gc.drawImage(spriteItem, position.getX(), position.getY(),ancho,alto);
        }

    }

    public void checkColisionResource() {
        if (durabilidad > 0 && checkGeneralColisions.checkColicion()) {
            checkGeneralColisions.visualizarRangoColicion();
            setActive(true);
        } else {
            setActive(false);
        }
    }

    public CheckGeneralColisions getCheckGeneralColisions() {
        return checkGeneralColisions;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public int getDurabilidad(){
        return durabilidad;
    }

    public void setOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case T -> {
                if(active && avatar.getStateToolTypeAction().equals(requiredTool)){
                    this.durabilidad--;
                    if(durabilidad<=0 && !destroyIt){
                        checkGeneralColisions.setDoColicon(false);
                        inventoryManagerResources.addNewItem(inventoryItem.getItemName(),inventoryItem.getQuantity());
                        destroyIt=true;
                    }

                }
            }
        }
    }


}
