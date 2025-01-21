package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import org.icesi.animation.control.HelloController;
import org.icesi.animation.screens.ScreenC;

import java.util.ArrayList;

public class Portal extends CollisionManager {

    private ArrayList<Image> spritePortal;
    private int cont;

    private boolean active;

    private int numScreen;

    private GraphicsContext gc;

    private Avatar avatar;

    public Portal(Canvas canvas, Avatar avatar) {
        super(canvas);
        this.gc = canvas.getGraphicsContext2D();

        this.spritePortal = new ArrayList<>();

        this.avatar = avatar;

        this.active = false;

        this.numScreen = 2;

        this.cont=0;

        for (int i = 0; i < 75; i++){
            Image portal = new Image(getClass().getResourceAsStream("/animacionesObjetos/animacionPortal/portalmagenes/portalPrueba_"+i+".png"));
            spritePortal.add(portal);
        }

        defineDimension(25,550.0, 100, 100,20);
        setColicionesInferiores(true);

    }

    @Override
    public void paint() {

        gc.drawImage(spritePortal.get(cont), getPositionX(), getPositionY(), getwidth(), getheight());

        cont++;

        if(cont>74){
            cont=0;
        }


    }

    public void setNumScreen(int numScreen) {
        this.numScreen = numScreen;
    }

    public void setActive(boolean active) {

        this.active = active;
    }

    public void setOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case R -> {
                if(active){

                    HelloController.SCREEN_ID=numScreen;
                    if(HelloController.SCREEN_ID==2){
                        ScreenC.elementsCreated=false;
                    }
                    avatar.setPositionY(500);
                    avatar.setPositionX(500);
                    avatar.setMoveDown(true);
                    avatar.setMoveUp(true);
                    avatar.setMoveLeft(true);
                    avatar.setMoveRight(true);
                }
            }
        }
    }


}
