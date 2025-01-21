package org.icesi.animation.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.icesi.animation.model.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ScreenA extends BaseScreen{


    private Avatar avatar;
    private TentAvatar tentAvatar;
    private Portal portal;
    private Image gifImage;
    private Image island;
    private Image uperPartTent;
    private CheckGeneralColisions checkColicionPortal;
    private CheckGeneralColisions checkColicionCrop1;
    private CheckGeneralColisions checkColicionCrop2;
    private CheckGeneralColisions checkColicionCrop3;
    private CheckGeneralColisions checkColicionCrop4;
    private CheckGeneralColisions checkColicionCrop5;
    private CheckGeneralColisions checkColicionCrop6;
    private CheckGeneralColisions checkColicionCrop7;
    private CheckGeneralColisions checkColicionCrop8;
    private CheckGeneralColisions checkColicionCrop9;
    private CheckGeneralColisions checkColicionCrop10;

    private ArrayList<CheckGeneralColisions> checkColisionsCrops;

    private final int MAX_CROPS = 10;
    private ArrayList<Crop> crops;
    private ArrayList<CheckGeneralColisions> cropsCollisions;

    public ScreenA(Canvas canvas, InventoryManagerTool inventoryManagerTool, Avatar avatar,InventoryManagerResources inventoryManagerResources) {
        super(canvas, inventoryManagerTool,inventoryManagerResources);
        //because of that ScreenA inherit from BaseScreen, it no longer need it own graphicsContext
        //this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.avatar = avatar;
        this.tentAvatar = new TentAvatar(super.canvas,avatar);
        this.portal = new Portal(super.canvas,avatar);
        gifImage = new Image(getClass().getResource("/animations.background/gifUnPocoMas.gif").toExternalForm());
        island = new Image(getClass().getResource("/animations.background/fondoIslaPrincipal.png").toExternalForm());
        uperPartTent = new Image(getClass().getResource("/animacionesObjetos/parteSuperiorTienda.png").toExternalForm());

        avatar.setPositionY(500);
        avatar.setPositionX(500);

        portal.setNumScreen(2);

        this.checkColicionPortal = new CheckGeneralColisions(avatar,portal,super.canvas);

        this.crops = new ArrayList<>();
        this.cropsCollisions = new ArrayList<>();

    }

    public void paint() {

        //fondo
        //gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, super.canvas.getWidth(), super.canvas.getHeight());
        gc.clearRect(0, 0, super.canvas.getWidth(), super.canvas.getHeight());
        gc.drawImage(gifImage, 0, 0, gifImage.getWidth(), gifImage.getHeight(),
                0, 0, super.canvas.getWidth(), super.canvas.getHeight());

        //plataforma

        // tamaño imagen
        double scaledWidth = island.getWidth() / 2 + 200; // Por ejemplo, reducir a la mitad.
        double scaledHeight = island.getHeight() / 2;

        // coordenadas centrar horizontalmente
        double x = (super.canvas.getWidth() - scaledWidth) / 2 - 250;
        double y = (super.canvas.getHeight() - scaledHeight) / 2 - 275; // Subir 100 píxeles arriba del centro.

        // alto canvas 1688.0
        // ancho canvas 2932.0

        //alto plataforma 844.0
        //ancho plataforma 1666.0

        gc.drawImage(island, x, y, scaledWidth, scaledHeight);

        //rectangulo A
        //gc.strokeRect(x+100, y+420, 489, scaledHeight/2-160);
        //rectangulo B
        //gc.strokeRect(x+100+489, y+360, 489, scaledHeight/2-100);
        //rectangulo C
        //gc.strokeRect(x+100+978, y+450, 489, scaledHeight/2-200);
        //rectangulo D
        //gc.strokeRect(x+100+489-100,y+360,99,60);
        //rectangulo E
        //gc.strokeRect(x+100+978, y+389, 99, 60);

        //colision A
        double XinicialA = x + 100;
        double YinicialA = y + 420;
        double XfinalA = XinicialA + 489;
        double YfinalA = YinicialA + (scaledHeight / 2 - 160);

        //colision B
        double XinicialB = x + 100 + 489;
        double YinicialB = y + 360;
        double XfinalB = XinicialB + 489;
        double YfinalB = YinicialB + (scaledHeight / 2 - 100);

        //colicion C
        double XinicialC = x + 100 + 978;
        double YinicialC = y + 450;
        double XfinalC = XinicialC + 489;
        double YfinalC = YinicialC + (scaledHeight / 2 - 200);

        //colicion D
        double XinicialD = x + 100 + 489 - 100;
        double YinicialD = y + 360;
        double XfinalD = XinicialD + 99;
        double YfinalD = YinicialD + 60;

        //colicion E
        double XinicialE = x + 100 + 978;
        double YinicialE = y + 389;
        double XfinalE = XinicialE + 99;
        double YfinalE = YinicialE + 60;

        tentAvatar.paint();

        //dimensiones tentAvatar
        //gc.setFill(Color.BLACK);
        double xTent = tentAvatar.getPositionX();
        double yTent = tentAvatar.getPositionY();
        double widthTent = tentAvatar.getWidthTent();
        double heightTent = tentAvatar.getHeightTent();
        //gc.strokeRect(xTent + 30, yTent + 97, widthTent - 60, heightTent - 100);

        //colicion tiena
        double XinicialTent = xTent-5 ;//670
        double YinicialTent = yTent + 57;//597.0
        double XfinalTent = xTent + (widthTent-50);//780.0
        double YfinalTent = yTent + (heightTent-30);//700.0

        crops = avatar.getCrops();

        for (int i = 0; i < crops.size(); i++) {
            crops.get(i).paint();
        }

        portal.paint();

        if(checkColicionPortal.checkColicion()) {
            if (checkColicionPortal.checkColicion()) {
                portal.setActive(true);
            } else {
                portal.setActive(false);
            }
        }else if (avatar.getXavatar() > (XinicialTent-20 ) && avatar.getXavatar() < (XfinalTent ) &&
                avatar.getYavatar() > (YinicialTent-20 ) && avatar.getYavatar() < (YfinalTent )) {

            colicionTent( XinicialTent,  YinicialTent,  XfinalTent,  YfinalTent);

        }else if (avatar.getXavatar() < XfinalA - 10) {

            colicionA(XinicialA, YinicialA, XfinalA, YfinalA);

        }else if (avatar.getXavatar() > XfinalA - 10 && avatar.getXavatar() < XfinalB - 10) {

            colicionB(XinicialB, YinicialB, XfinalB, YfinalB, YinicialE);

        } else if (avatar.getXavatar() > XinicialC - 10 && avatar.getXavatar() < XfinalC - 10) {

            colicionC(XinicialC, YinicialC, XfinalC, YfinalC);

        } else  if (avatar.getXavatar() < XfinalD && avatar.getYavatar() < YfinalD && avatar.getXavatar() > XinicialD) {

            colicionD(XinicialD, YinicialD, XfinalD, YfinalD);
        }else{
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);
            avatar.setMoveDown(true);
            avatar.setMoveUp(true);
        }

        avatar.paint();
        gc.drawImage(uperPartTent,tentAvatar.getPositionX()-71,tentAvatar.getPositionY()-55);
    }

    public void colicionA(double XinicialA, double YinicialA, double XfinalA, double YfinalA){
        if(avatar.getXavatar()<XinicialA-10||avatar.getYavatar()<YinicialA-25||avatar.getXavatar()>XfinalA-50||avatar.getYavatar()>YfinalA-60){

            if(avatar.getXavatar()<XinicialA-10){
                avatar.setMoveLeft(false);
            }

            if(avatar.getYavatar()<YinicialA-35){
                avatar.setMoveUp(false);
            }
            if(avatar.getYavatar()>YfinalA-60){
                avatar.setMoveDown(false);
            }
            if(avatar.getXavatar()<XinicialA-10 && avatar.getYavatar()<YinicialA-35){
                avatar.setMoveLeft(false);
                avatar.setMoveUp(false);
            }
            if(avatar.getXavatar()<XinicialA-10 && avatar.getYavatar()>YfinalA-60){
                avatar.setMoveLeft(false);
                avatar.setMoveDown(false);
            }

            //System.out.println("A se salio de rectangulo A en posicion x "+avatar.getXavatar()+" y en posicion y "+avatar.getYavatar());
        }else{
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);
            avatar.setMoveDown(true);
            avatar.setMoveUp(true);
        }
    }
    public void colicionB(double XinicialB, double YinicialB, double XfinalB, double YfinalB,double YinicialC){
        if( avatar.getXavatar()<XinicialB-10||avatar.getYavatar()<YinicialB-25||avatar.getXavatar()>XfinalB-50||avatar.getYavatar()>YfinalB-60){
            if(avatar.getYavatar()<YinicialB-35){
                avatar.setMoveUp(false);
            }
            if(avatar.getYavatar()>YfinalB-60){
                avatar.setMoveDown(false);
            }
            if(avatar.getXavatar()<XinicialB-10){
                avatar.setMoveLeft(false);
            }
            if(avatar.getYavatar()<YinicialC-35 && avatar.getXavatar()>950.0){
                avatar.setMoveRight(false);

            }

            //System.out.println("B se salio de rectangulo B en posicion x "+avatar.getXavatar()+" y en posicion y "+avatar.getYavatar());
        }else{
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);
            avatar.setMoveDown(true);
            avatar.setMoveUp(true);
        }
    }
    public void colicionC(double XinicialC, double YinicialC, double XfinalC, double YfinalC){
        if( avatar.getXavatar()<XinicialC-10||avatar.getYavatar()<YinicialC-25||avatar.getXavatar()>XfinalC-50||avatar.getYavatar()>YfinalC-60){
            if(avatar.getYavatar()<YinicialC-35){
                avatar.setMoveUp(false);
            }
            if(avatar.getYavatar()>YfinalC-60){
                avatar.setMoveDown(false);
            }
            if(avatar.getXavatar()<XinicialC-10){
                avatar.setMoveLeft(false);
            }
            if(avatar.getXavatar()>XfinalC-50){
                avatar.setMoveRight(false);
            }
            if(avatar.getYavatar()>400.0&&avatar.getXavatar()>1027.0){
                avatar.setMoveRight(false);
            }

            //System.out.println("C se salio de rectangulo C en posicion x "+avatar.getXavatar()+" y en posicion y "+avatar.getYavatar());
        }else{
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);
            avatar.setMoveDown(true);
            avatar.setMoveUp(true);
        }

    }

    public void colicionD(double XinicialD, double YinicialD, double XfinalD, double YfinalD){
        if( avatar.getXavatar()<XinicialD-10||avatar.getYavatar()<YinicialD-25||avatar.getXavatar()>XfinalD-50||avatar.getYavatar()>YfinalD-60){
            if(avatar.getYavatar()<YinicialD-35){
                avatar.setMoveUp(false);
            }
            if(avatar.getXavatar()>444.0){
                avatar.setMoveLeft(false);
            }
        }else {
            avatar.setMoveUp(true);
            avatar.setMoveDown(true);
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);

        }
    }

    public void colicionTent(double XinicialTent, double YinicialTent, double XfinalTent, double YfinalTent) {
        if (avatar.getXavatar() > (XinicialTent ) || avatar.getXavatar() < (XfinalTent ) ||
                avatar.getYavatar() > (YinicialTent ) || avatar.getYavatar() < (YfinalTent )) {

            if (avatar.getXavatar() <= XinicialTent -10) {
                avatar.setMoveRight(false);
                tentAvatar.setActive(true);
            }
            if (avatar.getXavatar() >= XfinalTent-10) {
                avatar.setMoveLeft(false);
                tentAvatar.setActive(true);
            }
            if (avatar.getYavatar() <= YinicialTent+30) {
                avatar.setMoveDown(false);
                tentAvatar.setActive(true);
            }
            if (avatar.getYavatar() >= YfinalTent-19) {
                avatar.setMoveUp(false);
                tentAvatar.setActive(true);
            }
        }else {
            // Restaurar los movimientos permitidos si el avatar no está cerca del área de colisión
            avatar.setMoveLeft(true);
            avatar.setMoveRight(true);
            avatar.setMoveUp(true);
            avatar.setMoveDown(true);
            tentAvatar.setActive(false);

        }
    }


    @Override
    public void onKeyPressed(KeyEvent event){

        avatar.setOnKeyPressed(event);
        tentAvatar.setOnKeyPressed(event);
        portal.setOnKeyPressed(event);

    }

    @Override
    public void onKeyReleased(KeyEvent event){
        avatar.onKeyReleased(event);
    }


}
