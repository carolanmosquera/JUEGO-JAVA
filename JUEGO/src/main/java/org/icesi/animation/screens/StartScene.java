package org.icesi.animation.screens;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.icesi.animation.HelloApplication;

public class StartScene {

    @FXML
    private StackPane rootVBox;

    @FXML
    private Label pressEnterLabel;

    @FXML
    private ImageView backgroundImage; // Background

    private Stage stage;


    @FXML
    public void initialize() {

        backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/StartScene/background.png")));


        rootVBox.getChildren().add(0, backgroundImage);


        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 50);

        applyFontAndColor(pressEnterLabel, pixelFont, Color.WHITE);
        pressEnterLabel.setTranslateY(-10);




        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pressEnterLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();


        rootVBox.requestFocus();


        rootVBox.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitWidth(rootVBox.getWidth());
        });
        rootVBox.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundImage.setFitHeight(rootVBox.getHeight());
        });


        rootVBox.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:

                    HelloApplication.openWindow("instructions.fxml");
                    stage.close();
                    break;
                default:
                    break;
            }
            event.consume();
        });

        Platform.runLater(() -> rootVBox.requestFocus());

    }

    private void applyFontAndColor(Label label, Font font, Color color) {
        label.setFont(font);
        label.setTextFill(color);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
