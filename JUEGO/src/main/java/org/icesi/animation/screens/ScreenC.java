package org.icesi.animation.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import org.icesi.animation.model.*;

import java.util.Random;

public class ScreenC extends BaseScreen {

    private Avatar avatar;
    private Image fondo;
    private Image peces;
    private Portal portal;
    private BoxColicion BigLake;
    private BoxColicion SmallLake;
    private CheckGeneralColisions colicionPortal;

    private ResourcesSmall recurso1;
    private ResourcesSmall recurso2;
    private ResourcesSmall recurso3;
    private ResourcesSmall recurso4;
    private ResourcesSmall recurso5;
    private ResourcesSmall recurso6;
    private ResourcesSmall recurso7;
    private ResourcesSmall recurso9;
    private ResourcesSmall recurso10;
    private ResourcesSmall recurso11;
    private ResourcesSmall recurso12;

    private CheckGeneralColisions colicionBigLakeAvatar;
    private CheckGeneralColisions colicionSmallLakeAvatar;

    private CheckGeneralColisions colicionBigLakeEnemy;
    private CheckGeneralColisions colicionSmallLakeEnemy;

    public static boolean elementsCreated;

    private InventoryManagerResources inventoryManagerResources;
    private double XinicialA;
    private double YinicialA;
    private double XfinalA;
    private double YfinalA;
    private EnemyColicion enemyLogic01;
    private Enemy01 enemy01;
        private CheckGeneralColisions colisionLogicEnemy01;


    public ScreenC(Canvas canvas, InventoryManagerTool inventoryManagerTool, Avatar avatar,InventoryManagerResources inventoryManagerResources) {
        super(canvas, inventoryManagerTool,inventoryManagerResources);

        this.inventoryManagerResources = inventoryManagerResources;
        fondo = new Image(getClass().getResource("/animations.background/esenarioC/fondoEsenarioC.png").toExternalForm());
        peces = new Image(getClass().getResource("/animations.background/esenarioC/gifPecesPequeño.gif").toExternalForm());
        this.avatar = avatar;
        portal = new Portal(super.canvas,avatar);
        BigLake = new BoxColicion(super.canvas,415,240,930, 530);
        SmallLake = new BoxColicion(super.canvas,1240,40,1455, 180);

        createElements();
        this.elementsCreated = true;

        this.colicionPortal =  new CheckGeneralColisions(avatar,portal,super.canvas);
        this.colicionBigLakeAvatar = new CheckGeneralColisions(avatar,BigLake,super.canvas);
        this.colicionSmallLakeAvatar = new CheckGeneralColisions(avatar,SmallLake,super.canvas);


        this.YfinalA = 695;
        this.YinicialA = -8.0;
        this.XfinalA = 1484;
        this.XinicialA = -23.0;

        portal.setNumScreen(0);

        this.enemy01 = new Enemy01(canvas,550,550,50,50,100);

        this.enemyLogic01 = new EnemyColicion(canvas,avatar,inventoryManagerResources,enemy01);

        this.colicionBigLakeEnemy =  new CheckGeneralColisions(enemyLogic01,BigLake,super.canvas);
        this.colicionSmallLakeEnemy = new CheckGeneralColisions(enemyLogic01,SmallLake,super.canvas);
        this.colisionLogicEnemy01 = enemyLogic01.getColicionDeteccion();
    }

    @Override
    public void paint() {

        if(!elementsCreated) {
            createElements();
        }

        // Calcula la escala para mantener la proporción
        double scale = Math.min(canvas.getWidth() / fondo.getWidth(), canvas.getWidth() / canvas.getHeight());

        // Calcula el tamaño final escalado
        double newWidth = (fondo.getWidth()* scale)-400;
        double newHeight = fondo.getHeight() * scale-340;

        // Centra la imagen en el canvas
        double x = (canvas.getWidth() - newWidth) / 2-300;
        double y = (canvas.getHeight() - newHeight) / 2-380;

        gc.fillRect(50,50,0,0);

        gc.drawImage(peces,  x, y+10, newWidth, newHeight);

        gc.drawImage(fondo, x, y+20, newWidth, newHeight);

        portal.paint();

        recurso1.paint();
        recurso2.paint();
        recurso3.paint();
        recurso4.paint();
        recurso5.paint();
        recurso6.paint();
        recurso7.paint();
        recurso9.paint();
        recurso10.paint();
        recurso11.paint();
        recurso12.paint();

        //BigLake.paint();
        //SmallLake.paint();

        if(avatar.getXavatar()<XinicialA+30 || avatar.getYavatar()<YinicialA+30 || avatar.getYavatar()>YfinalA+30 || avatar.getXavatar()>XfinalA){
            collicionLimitsA();
        }else if(colicionBigLakeAvatar.checkColicion()){
            colicionBigLakeAvatar.checkColicion();
        }else if (colicionPortal.checkColicion()) {
            if(colicionPortal.checkColicion()){
                portal.setActive(true);
            }else{
                portal.setActive(false);
            }
        } else if (colicionSmallLakeAvatar.checkColicion()) {
            colicionSmallLakeAvatar.visualizarRangoColicion();
            colicionSmallLakeAvatar.checkColicion();
        }else if(recurso1.getCheckGeneralColisions().checkColicion()){
            recurso1.checkColisionResource();

        }else if(recurso2.getCheckGeneralColisions().checkColicion()){
            recurso2.checkColisionResource();

        }else if(recurso3.getCheckGeneralColisions().checkColicion()){
            recurso3.checkColisionResource();

        }else if(recurso4.getCheckGeneralColisions().checkColicion()){
            recurso4.checkColisionResource();

        }else if(recurso5.getCheckGeneralColisions().checkColicion()){
            recurso5.checkColisionResource();

        }else if(recurso6.getCheckGeneralColisions().checkColicion()){
            recurso6.checkColisionResource();

        }else if(recurso7.getCheckGeneralColisions().checkColicion()){
            recurso7.checkColisionResource();

        }else if(recurso9.getCheckGeneralColisions().checkColicion()){
            recurso9.checkColisionResource();

        }else if(recurso10.getCheckGeneralColisions().checkColicion()){
            recurso10.checkColisionResource();

        }else if(recurso11.getCheckGeneralColisions().checkColicion()){
            recurso11.checkColisionResource();

        }else if(recurso12.getCheckGeneralColisions().checkColicion()){
            recurso12.checkColisionResource();
        }


        avatar.paint();

        enemyLogic01.paint();

        if(enemyLogic01.getPositionX()<XinicialA+30 || enemyLogic01.getPositionY()<YinicialA+30 || enemyLogic01.getPositionY()>YfinalA+30 || enemyLogic01.getPositionX()>XfinalA) {
            collicionLimitsA();
        }else if(colisionLogicEnemy01.checkColicion()){
            colisionLogicEnemy01.visualizarRangoColicion();
        }

    }


    @Override
    public void onKeyPressed(KeyEvent event) {
        avatar.setOnKeyPressed(event);
        portal.setOnKeyPressed(event);

        recurso1.setOnKeyPressed(event);
        recurso2.setOnKeyPressed(event);
        recurso3.setOnKeyPressed(event);
        recurso4.setOnKeyPressed(event);
        recurso5.setOnKeyPressed(event);
        recurso6.setOnKeyPressed(event);
        recurso7.setOnKeyPressed(event);
        recurso9.setOnKeyPressed(event);
        recurso10.setOnKeyPressed(event);
        recurso11.setOnKeyPressed(event);
        recurso12.setOnKeyPressed(event);


    }

    public void collicionLimitsA(){
        if(avatar.getXavatar()<XinicialA+30){
            avatar.setMoveLeft(false);
        }else{
            avatar.setMoveLeft(true);
        }

        if(avatar.getYavatar()<YinicialA+30){
            avatar.setMoveUp(false);
        }else{
            avatar.setMoveUp(true);
        }
        if(avatar.getYavatar()>YfinalA-60){
            avatar.setMoveDown(false);
        }else{
            avatar.setMoveDown(true);
        }
        if(avatar.getXavatar()>XfinalA) {
            avatar.setMoveRight(false);
        }else {
            avatar.setMoveRight(true);
        }

        //----Enemy--------------------
        if(enemyLogic01.getPositionX()<XinicialA+30){
            System.out.println("choco derecha");
            enemyLogic01.resetDirection("right");
        }

        if(enemyLogic01.getPositionY()<YinicialA+30){
            enemyLogic01.resetDirection("up");
        }
        if(enemyLogic01.getPositionY()>YfinalA-60){
            enemyLogic01.resetDirection("down");
        }
        if(enemyLogic01.getPositionX()>XfinalA) {
            System.out.println("choco izquierda");
            enemyLogic01.resetDirection("left");

        }



    }

    public void createElements(){
        Random random = new Random();
        recurso1 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,205,655,avatar,inventoryManagerResources);
        recurso2 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,58,28,avatar,inventoryManagerResources);
        recurso3 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,58,268,avatar,inventoryManagerResources);
        recurso4 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,238,268,avatar,inventoryManagerResources);
        recurso5 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,358,118,avatar,inventoryManagerResources);
        recurso6 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,537,57,avatar,inventoryManagerResources);
        recurso7 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,672,147,avatar,inventoryManagerResources);
        recurso9 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,819,105,avatar,inventoryManagerResources);
        recurso10 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,1042,77,avatar,inventoryManagerResources);
        recurso11 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,1027,257,avatar,inventoryManagerResources);
        recurso12 = new ResourcesSmall(super.canvas, random.nextInt(6) + 1,938,616,avatar,inventoryManagerResources);

        this.elementsCreated=true;

    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        avatar.onKeyReleased(event);
    }

}