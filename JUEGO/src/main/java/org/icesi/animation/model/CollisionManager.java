package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CollisionManager {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private double rangoColicioXderecha;
    private double rangoColicioYarriba;
    private double rangoColicioXizquierda;
    private double rangoColicioYabajo;

    private double realColicionYinicial;
    private double realColicionXinicial;
    private double realColicionYfinal;
    private double realColicionXfinal;

    private double width;
    private double height;

    private double xOrigen;
    private double yOrigen;

    private boolean dimensionesYaDefinidas;

    private boolean colicionesInferiores;

    private boolean rangoDeColicionRectangulo;

    private double rangoDeteccion;

    private Position position;

    //aqui creas el objeto solo con la posicion
    public CollisionManager(Canvas canvas) {
        this.canvas = canvas;
        this.colicionesInferiores = false;
        this.rangoDeColicionRectangulo = false;

    }

    //aqui ya le asignas las dimensiones
    public void defineDimension(double x, double y,double width, double height, double rangoDeteccion) {
        this.position = new Position(x, y);
        graphicsContext = canvas.getGraphicsContext2D();
        this.dimensionesYaDefinidas = false;
        this.width = width;
        this.height = height;
        this.xOrigen = position.getX() + width / 2;
        this.yOrigen = position.getY() + height / 2;

        this.realColicionYinicial = position.getY();
        this.realColicionXinicial = position.getX();
        this.realColicionYfinal = position.getY()+height;
        this.realColicionXfinal = position.getX()+width;

//        System.out.println("realColicionYinicial "+realColicionYinicial);
//        System.out.println("realColicionXinicial "+realColicionXinicial);
//        System.out.println("realColicionYFinal "+realColicionYfinal);
//        System.out.println("realColicionXfinal "+realColicionXfinal);



        this.dimensionesYaDefinidas = true;

        setRange(rangoDeteccion);


    }

    public void paint() {

    }

    public void setRange(double x) {
        if (dimensionesYaDefinidas) {
            this.rangoDeteccion = x;

            if(!rangoDeColicionRectangulo) {
                this.rangoColicioXderecha = xOrigen + rangoDeteccion;
                this.rangoColicioYarriba = yOrigen - rangoDeteccion;
                this.rangoColicioXizquierda = xOrigen - rangoDeteccion;
                this.rangoColicioYabajo = yOrigen + rangoDeteccion;
            }else{
                this.rangoColicioXderecha = realColicionXfinal + rangoDeteccion;
                this.rangoColicioYarriba = realColicionYinicial - rangoDeteccion;
                this.rangoColicioXizquierda = realColicionXinicial - rangoDeteccion;
                this.rangoColicioYabajo = realColicionYfinal + rangoDeteccion;

            }

            if ((rangoColicioXderecha <= realColicionXfinal) &&
                    (rangoColicioYarriba <= realColicionYinicial) &&
                    (rangoColicioXizquierda >= realColicionXinicial) &&
                    (rangoColicioYabajo <= realColicionYfinal)) {

                this.colicionesInferiores = true;
            } else {
                this.colicionesInferiores = false;
            }

            //System.out.println("rangoColicioXderecha "+ rangoColicioXderecha);
            //System.out.println("rangoColicioYarriba "+ rangoColicioYarriba);
            //System.out.println("rangoColicioXizquierda "+ rangoColicioXizquierda);
            //System.out.println("rangoColicioYabajo "+ rangoColicioYabajo);
        } else {
            System.out.println("primero debes de definir las dimensiones con el metodo (defineDimension) ");
        }
    }

    public void setRangoDeColicionRectangulo(boolean rectangulo) {
        this.rangoDeColicionRectangulo = rectangulo;
    }

    public boolean isColicionesInferiores(){
        return colicionesInferiores;
    }

    public void setColicionesInferiores(boolean colicionesInferiores) {
        this.colicionesInferiores = colicionesInferiores;
    }

    public double getRangoColicioXderecha() {
        return rangoColicioXderecha;
    }

    public double getRangoColicioXizquierda() {
        return rangoColicioXizquierda;
    }

    public double getRangoColicioYarriba() {
        return rangoColicioYarriba;
    }

    public double getRangoColicioYabajo() {
        return rangoColicioYabajo;
    }

    public double getColicioXinicial() {
        return realColicionXinicial;
    }
    public double getColicioYinicial() {
        return realColicionYinicial;
    }
    public double getColicioXfinal() {
        return realColicionXfinal;
    }
    public double getColicioYfinal() {
        return realColicionYfinal;
    }

    public double getwidth(){
        return width;
    }

    public double getheight(){
        return height;
    }

    public double getxOrigen(){
        return xOrigen;
    }

    public double getyOrigen(){
        return yOrigen;
    }

    public double getPositionX(){
        return position.getX();
    }

    public double getPositionY(){
        return position.getY();
    }

}
