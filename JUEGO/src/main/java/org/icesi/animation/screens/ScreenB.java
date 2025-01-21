package org.icesi.animation.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.icesi.animation.control.HelloController;
import org.icesi.animation.model.*;


public class ScreenB extends BaseScreen{

    private Avatar avatar;
    private Image fondo;
    private CheckGeneralColisions checkColisionOnAxe;
    private CheckGeneralColisions checkColisionOnPeak;
    private CheckGeneralColisions checkColisionOnSickle;
    private double zoomFactor = 1.2;
    private boolean active;

    private InventoryManagerTool inventoryManagerTool;

    private AxeTool axe;
    private PeakTool peak;
    private SickleTool sickle;

    // Define los límites de los rectángulos
    private double x_inicialA, y_inicialA, x_finalA, y_finalA;
    private double x_inicialB, y_inicialB, x_finalB, y_finalB;
    private double x_inicialC, y_inicialC, x_finalC, y_finalC;



    public ScreenB(Canvas canvas, InventoryManagerTool inventoryManagerTool, Avatar avatar,InventoryManagerResources inventoryManagerResources){
        super(canvas, inventoryManagerTool,inventoryManagerResources);
        this.inventoryManagerTool = inventoryManagerTool;
        this.avatar = avatar;

       axe = new AxeTool(super.canvas,600,270, inventoryManagerTool);
       peak = new PeakTool(super.canvas,550,270, inventoryManagerTool);
       sickle = new SickleTool(super.canvas,650,270, inventoryManagerTool);

        fondo = new Image(getClass().getResourceAsStream("/fondoCarpa/dentroDeTienda.png"));

        initializeRectangles();

        this.checkColisionOnAxe = new CheckGeneralColisions(avatar, axe,super.canvas);

        this.checkColisionOnPeak = new CheckGeneralColisions(avatar, peak,super.canvas);

        this.checkColisionOnSickle = new CheckGeneralColisions(avatar, sickle,super.canvas);

    }

    private void initializeRectangles() {
        // Rectángulo A
        y_inicialA = 280 + 58;
        x_inicialA = 300 + 35;
        x_finalA = 898 - 300;
        y_finalA = 455 - 288;

        // Rectángulo B
        y_inicialB = 191.0 + 50;
        x_inicialB = 471.0 + 45;
        x_finalB = 726.0 - 470;
        y_finalB = 350;

        // Rectángulo C
        y_inicialC = 590;
        x_inicialC = 513 + 70;
        x_finalC = 100;
        y_finalC = 75;
    }

    @Override
    public void paint() {
        super.gc.setFill(Color.BLACK);
        super.gc.fillRect(0, 0, super.canvas.getWidth(), super.canvas.getHeight());

        // Aplica el zoom escalando el GraphicsContext
        gc.save(); // Guarda el estado actual del GraphicsContext antes del zoom
        gc.scale(zoomFactor, zoomFactor); // Aplica el zoom

        gc.drawImage(fondo, 300, 100,666,577);

        //gc.setStroke(Color.RED);


        //gc.setStroke(Color.RED); // Establece el color del trazo
        //gc.setLineWidth(2); // Establece el grosor de la línea

        //rectangulo A
        //gc.strokeRect(x_inicialA, y_inicialA, x_finalA, y_finalA);

        //rectangulo B
        //gc.strokeRect(x_inicialB, y_inicialB, x_finalB, y_finalB);

        //rectangulo C
        //gc.strokeRect(x_inicialC, y_inicialC, x_finalC, y_finalC);

        checkCollision();

        avatar.paint();

        axe.paint();

        peak.paint();

        sickle.paint();

        if(!axe.isAlreadyActiveColicion()) {
            if (checkColisionOnAxe.checkColicion()) {
                avatar.setAxeTool(axe);
                inventoryManagerTool.addAxeInventory(axe.getInventoryItem());
                axe.setActiveColicion(true);
            }
        } else if (!peak.isAlreadyActiveColicion()) {
            if (checkColisionOnPeak.checkColicion()) {
                avatar.setPeakTool(peak);
                inventoryManagerTool.addPeakInventory(peak.getInventoryItem());
                peak.setActiveColicion(true);
            }
        } else if (!sickle.isAlreadyActiveColicion()) {
            if (checkColisionOnSickle.checkColicion()) {
                avatar.setSickleTool(sickle);
                inventoryManagerTool.addSickleInventory(sickle.getInventoryItem());
                sickle.setActiveColicion(true);
            }

        }

        gc.restore(); // Restaura el estado original del GraphicsContext sin zoom

    }

    private void checkCollision() {
        boolean inRectangleA = isInsideRectangle(avatar, x_inicialA, y_inicialA, x_finalA, y_finalA);
        boolean inRectangleB = isInsideRectangle(avatar, x_inicialB, y_inicialB, x_finalB, y_finalB);
        boolean inRectangleC = isInsideRectangle(avatar, x_inicialC, y_inicialC, x_finalC, y_finalC);

        if(inRectangleA){
            if(avatar.getXavatar()<347){
                avatar.setMoveLeft(false);
            } else{
                avatar.setMoveLeft(true);
            }
            if(avatar.getXavatar()>850){
                avatar.setMoveRight(false);
            }else{
                avatar.setMoveRight(true);
            }
            if((avatar.getXavatar()<480 && avatar.getYavatar()<345)||(avatar.getXavatar()>765 && avatar.getYavatar()<345)){
                avatar.setMoveUp(false);
            }else{
                avatar.setMoveUp(true);
            }
            if((avatar.getXavatar()<480 && avatar.getYavatar()>444)||(avatar.getXavatar()>765 && avatar.getYavatar()>444)){
                avatar.setMoveDown(false);
            }else{
                avatar.setMoveDown(true);
            }

        }
        if(inRectangleB){

            if(avatar.getXavatar()>764&&avatar.getYavatar()<339){
                avatar.setMoveUp(false);
            }else if(avatar.getYavatar()<260){
                avatar.setMoveUp(false);
            }else {
                avatar.setMoveUp(true);
            }

            if((avatar.getXavatar()<527 && avatar.getYavatar()>443)||(avatar.getXavatar()<527 && avatar.getYavatar()<260)){
                avatar.setMoveLeft(false);
            }else {
                avatar.setMoveLeft(true);
            }
            if((avatar.getXavatar()>705 && avatar.getYavatar()>443)||(avatar.getXavatar()>705 && avatar.getYavatar()<260)){
                avatar.setMoveRight(false);
            }else{
                avatar.setMoveRight(true);
            }
            if((avatar.getXavatar()>644 && avatar.getYavatar()>519)||(avatar.getXavatar()<541 && avatar.getYavatar()>519)){
                avatar.setMoveDown(false);
            }else{
                avatar.setMoveDown(true);
            }


        }
        if(inRectangleC){
            this.active=true;

            if(avatar.getYavatar()>594){
                avatar.setMoveDown(false);
            }else {
                avatar.setMoveDown(true);
            }

            if(avatar.getXavatar()<590){
                avatar.setMoveLeft(false);
            }else{
                avatar.setMoveLeft(true);
            }

            if(avatar.getXavatar()>627){
                avatar.setMoveRight(false);
            }else{
                avatar.setMoveRight(true);
            }
        }else {
            this.active=false;
        }
    }

    private boolean isInsideRectangle(Avatar avatar, double xIn, double yIn, double xFin, double yFin) {
        return avatar.getXavatar() >= xIn && avatar.getXavatar() <= xIn + xFin &&
                avatar.getYavatar() >= yIn && avatar.getYavatar() <= yIn + yFin;
    }


    @Override
    public void onKeyPressed(KeyEvent event) {
        avatar.setOnKeyPressed(event);
        setOnKeyPressed(event);
        //axe.setOnKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyEvent event) {

        avatar.onKeyReleased(event);
    }


    // Método para actualizar el factor de zoom
    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        paint(); // Vuelve a pintar la pantalla con el nuevo zoom
    }

    public void setOnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case E -> {
                if(active){
                    HelloController.SCREEN_ID=0;
                }
            }
        }
    }

}
