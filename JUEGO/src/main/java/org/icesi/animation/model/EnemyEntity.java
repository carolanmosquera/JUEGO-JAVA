package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;

public class EnemyEntity extends CollisionManager{
    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private int life;
    private Canvas canvas;

    public EnemyEntity(Canvas canvas, double posionX, double posionY, double ancho, double alto, int life) {
        super(canvas);
        this.posionX = posionX;
        this.posionY = posionY;
        this.ancho = ancho;
        this.alto = alto;
        this.life = life;
    }

    public double getPosionX() {
        return posionX;
    }
    public void setPosionX(double posionX) {
        this.posionX = posionX;
    }
    public double getPosionY() {
        return posionY;
    }
    public void setPosionY(double posionY) {
        this.posionY = posionY;
    }
    public double getAncho() {
        return ancho;
    }
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    public double getAlto() {
        return alto;
    }
    public void setAlto(double alto) {
        this.alto = alto;
    }
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }

}
