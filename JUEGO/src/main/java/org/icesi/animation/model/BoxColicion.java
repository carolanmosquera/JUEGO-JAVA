package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoxColicion extends CollisionManager{

    private GraphicsContext gc;
    private double xInicial;
    private double yInicial;
    private double xFinal;
    private double yFinal;
    private double ancho;
    private double alto;

    public BoxColicion(Canvas canvas, double xInicial, double yInicial, double xFinal, double yFinal) {
        super(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;

        this.ancho = xFinal - xInicial;
        this.alto = yFinal- yInicial;

        setRangoDeColicionRectangulo(true);
        defineDimension(xInicial,yInicial,ancho,alto,10);
        setColicionesInferiores(false);

    }

    @Override
    public void paint() {

        gc.setFill(Color.BLUE);
        gc.fillRect(xInicial, yInicial, ancho, alto);

    }

}
