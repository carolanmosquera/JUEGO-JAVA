package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy01 extends EnemyEntity {

    private GraphicsContext gc;

    public Enemy01(Canvas canvas,double posionX, double posionY, double ancho, double alto, int life) {
        super(canvas,posionX, posionY, ancho, alto, life);
        this.gc=canvas.getGraphicsContext2D();

        setRangoDeColicionRectangulo(true);//with real limits, it makes sure that the collision are external
        defineDimension(posionX,posionY,ancho,alto,100);
        setColicionesInferiores(false);//I make sure that the collicion action work in the limit

    }

    public void paint() {
        gc.setFill(Color.RED);
        gc.fillRect(getPosionX(),getPosionY(),getAncho(),getAlto());

    }

}
