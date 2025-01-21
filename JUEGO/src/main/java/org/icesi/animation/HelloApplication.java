package org.icesi.animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.icesi.animation.control.HelloController;
import org.icesi.animation.screens.Instructions;
import org.icesi.animation.screens.StartScene;

import java.io.IOException;

public class  HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        openWindow("StartScene.fxml");

    }

    public static void openWindow(String fxml){

        try{

            if(fxml.equals("StartScene.fxml") || fxml.equals("instructions.fxml")){

                Stage stage2 = new Stage();
                stage2.setFullScreenExitHint("");
                stage2.setResizable(false);

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                StackPane root = fxmlLoader.load();
                Scene scene = new Scene(root, 1548, 816);
                stage2.setScene(scene);

                if(fxmlLoader.getController() instanceof StartScene) {
                    StartScene controller = fxmlLoader.getController();
                    controller.setStage(stage2);

                    stage2.setOnCloseRequest(event -> {
                        controller.getStage().close();
                    });
                    stage2.show();
                }
                else{
                    Instructions controller = fxmlLoader.getController();
                    controller.setStage(stage2);

                    stage2.setOnCloseRequest(event -> {
                        controller.getStage().close();
                    });
                    stage2.show();
                }

            }
            else {
                Stage stage2 = new Stage();
                stage2.setFullScreenExitHint("");
                stage2.setResizable(false);

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                Scene scene = new Scene(fxmlLoader.load(), 1548, 816);
                stage2.setTitle("Hello!");
                stage2.setScene(scene);
                stage2.setOnCloseRequest(event -> {
                    HelloController controller = fxmlLoader.getController();
                    controller.setRunning();
                });
                stage2.show();
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}