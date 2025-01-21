package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Crop extends CollisionManager {

    private GraphicsContext gc;
    private double xInicial;
    private double yInicial;
    private double xFinal;
    private double yFinal;
    private double ancho;
    private double alto;
    private ArrayList<Image> sprites;
    private int currentSpriteIndex ;
    private ResourcesSmall resourceCrop;
    private Avatar avatar;
    private InventoryManagerResources inventoryManagerResources;


    private Image sprite1;
    private Image sprite2;
    private Image sprite3;

    public Crop(Canvas canvas, double xInicial, double yInicial, String typeCrop, Avatar avatar, InventoryManagerResources inventoryManagerResources) {
        super(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.avatar = avatar;
        this.inventoryManagerResources = inventoryManagerResources;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.ancho = xFinal - xInicial;
        this.alto = yFinal - yInicial;
        sprites = new ArrayList<>();

        //sprite1 = new Image(getClass().getResourceAsStream("/crops/sprite1.png"));
        sprite2 = new Image(getClass().getResourceAsStream("/crops/sprite2.png"));

        //sprites.add(sprite1);
        sprites.add(sprite2);

        currentSpriteIndex = 0;

        switch (typeCrop){
            case "berries":
                this.resourceCrop = new ResourcesSmall(canvas,1,xInicial,yInicial,avatar,inventoryManagerResources);
                break;
            case "strawberries":
                this.resourceCrop = new ResourcesSmall(canvas,2,xInicial,yInicial,avatar,inventoryManagerResources);
                break;
            case "fungus":
                this.resourceCrop = new ResourcesSmall(canvas,3,xInicial,yInicial,avatar,inventoryManagerResources);
                break;
            case "peaches":
                this.resourceCrop = new ResourcesSmall(canvas,4,xInicial,yInicial,avatar,inventoryManagerResources);
                break;
            case "breads":
                this.resourceCrop = new ResourcesSmall(canvas,5,xInicial,yInicial,avatar,inventoryManagerResources);
                break;
        }
    }

    @Override
    public void paint() {

        double ancho2 = sprites.get(currentSpriteIndex).getWidth() *0.5;
        double alto2 = sprites.get(currentSpriteIndex).getHeight() * 0.5;

        // Dibujar el sprite actual en el canvas
//        gc.drawImage(sprites.get(currentSpriteIndex), xInicial, yInicial, ancho2-10, alto2 -35);

        resourceCrop.paint();



//        if(currentSpriteIndex >= sprites.size()) {
//            currentSpriteIndex = 0;
//        }

    }




}
