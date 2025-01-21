package org.icesi.animation.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.icesi.animation.model.Avatar;
import org.icesi.animation.model.InventoryManagerTool;
import org.icesi.animation.model.InventoryManagerResources;
import org.icesi.animation.screens.BaseScreen;
import org.icesi.animation.screens.ScreenA;
import org.icesi.animation.screens.ScreenB;
import org.icesi.animation.screens.ScreenC;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

public class HelloController implements Initializable {

    @FXML
    private Canvas canvas;

    private List<Label> heartLabels;


    // Contenedor principal donde colocarás la tabla
    @FXML
    private AnchorPane rootPane;

    private GraphicsContext graphicsContext;
    private ArrayList<BaseScreen> screens;
    private boolean isRunning;


    //atributo que maneja el table view de inventario
    private InventoryManagerTool inventoryManagerTool;
    private InventoryManagerResources inventoryManagerResources;


    //objeto avatar que se mantiene constante a lo largo de las clases
    private Avatar avatar;


    //this is the variable that is going to control de number of the screen
    public static int SCREEN_ID = 0;

    public HelloController() {
        heartLabels = new ArrayList<>();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //de aqui pa bajo ya es logica de screens

        this.graphicsContext = canvas.getGraphicsContext2D();
        screens = new ArrayList<>(2);

        // Inicializar y agregar el inventario
        inventoryManagerTool = new InventoryManagerTool();
        inventoryManagerResources = new InventoryManagerResources();

        avatar = new Avatar(canvas, inventoryManagerTool,inventoryManagerResources);

        //tool inventory
        rootPane.getChildren().add(inventoryManagerTool.getInventoryTable());
        AnchorPane.setTopAnchor(inventoryManagerTool.getInventoryTable(), 10.0);
        AnchorPane.setRightAnchor(inventoryManagerTool.getInventoryTable(), 10.0);

        //resource inventory
        rootPane.getChildren().add(inventoryManagerResources.getInventoryTable());
        AnchorPane.setBottomAnchor(inventoryManagerResources.getInventoryTable(), 10.0);
        AnchorPane.setRightAnchor(inventoryManagerResources.getInventoryTable(), 10.0);


        screens.add(new ScreenA(canvas, inventoryManagerTool,avatar,inventoryManagerResources));//0
        screens.add(new ScreenB(canvas, inventoryManagerTool,avatar,inventoryManagerResources));//1
        screens.add(new ScreenC(canvas, inventoryManagerTool,avatar,inventoryManagerResources));//2


        //this is the old way, in which we paint a specific screen
        // = new ScreenA(canvas);

        initActions();
        initHealth();

        isRunning = true;

        // Thread that start the game
        new Thread(() -> { // Runable -> lambda
            while (isRunning){
                Platform.runLater( () -> {
                    //screen.paint();
                    //now instead of painting a specific screen, we paint a screen that dependes of the position in the arralyst
                    screens.get(SCREEN_ID).paint();
                });
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }





    public void setRunning(){

        isRunning = false;
    }

    public void initActions(){
        canvas.setOnKeyPressed(event -> {

                screens.get(SCREEN_ID).onKeyPressed(event);

        });

        canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN_ID).onKeyReleased(event);

        });
    }

    public void initHealth() {
        rootPane.getChildren().removeAll(heartLabels);
        heartLabels.clear();

        int space = 0;
        for (int i = 0; i < avatar.getHealth(); i++) {
            space += 20;
            Label l = new Label();
            loadImage("src/main/resources/health/heart.png", l, 50, 50);
            l.setLayoutX(10 + space);
            l.setLayoutY(10);
            heartLabels.add(l);
            rootPane.getChildren().add(l);
        }

    }

    public void loadImage(String url, Label l, int width, int height) {
        File i = new File(url);
        String ur;

        try {
            ur = i.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        ImageView img = new ImageView(ur);
        img.setFitHeight(height);
        img.setFitWidth(width);
        l.setGraphic(img);

    }

    public void dismissHealthInScreen(){
        if (avatar.getHealth() >= 0) {
            rootPane.getChildren().remove(heartLabels.get(heartLabels.size() - 1));
            heartLabels.remove(heartLabels.get(heartLabels.size() - 1));
        }

    }
}