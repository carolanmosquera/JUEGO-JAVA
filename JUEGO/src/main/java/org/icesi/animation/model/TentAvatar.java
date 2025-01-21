package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import org.icesi.animation.control.HelloController;

import java.util.ArrayList;

public class TentAvatar {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Position position;
    private Avatar avatar;

    //colicion with player
    private boolean active;

    private ArrayList<Image> imageTent;

    private double widthTent;
    private double heightTent;
    private int cont;


    public TentAvatar(Canvas canvas, Avatar avatar) {
        this.canvas = canvas;
        this.avatar = avatar;

        this.active = false;

        this.imageTent=new ArrayList<>();

        this.cont=0;

        this.graphicsContext = canvas.getGraphicsContext2D();
        position = new Position(640, 400);

        for (int i = 0; i < 2; i++){
            Image tent = new Image(getClass().getResourceAsStream("/animacionesObjetos/animacionTiendita/tienda_000"+i+".png"));
            imageTent.add(tent);
        }

    }
    public void paint(){
        graphicsContext.drawImage(imageTent.get(cont), position.getX(), position.getY());

        this.widthTent=imageTent.get(cont).getWidth();
        this.heightTent=imageTent.get(cont).getHeight();

        cont++;

        if(cont>1){
            cont=0;
        }

    }

    public double getWidthTent() {
        return widthTent;

    }
    public double getHeightTent() {
        return heightTent;

    }
    public double getPositionX() {
        return position.getX();

    }
    public double getPositionY() {
        return position.getY();

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOnKeyPressed(KeyEvent event) {
            switch (event.getCode()) {
                case E -> {
                    if(active){
                        HelloController.SCREEN_ID=1;
                        active=false;
                        avatar.setPositionX(550);
                        avatar.setPositionY(370);
                    }
                }
            }
    }

}
