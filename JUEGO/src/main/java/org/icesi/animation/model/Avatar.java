package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Avatar {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Position position;

    private int health;
    private ArrayList<Crop> crops;

    private String state;
    private String stateToolTypeAction;
    private String typeSeed;
    private int frame;

    private AxeTool axeTool;
    private PeakTool peakTool;
    private SickleTool sickleTool;

    private InventoryManagerTool inventoryManagerTool;
    private InventoryManagerResources inventoryManagerResources;

    private boolean selectionInventoryResource;


    private ArrayList<Image> upWalking;
    private ArrayList<Image> downWalking;
    private ArrayList<Image> rigthUpWalking;
    private ArrayList<Image> rigthDownWalking;
    private ArrayList<Image> lefthUpWalking;
    private ArrayList<Image> lefthDownWalking;
    private ArrayList<Image> idleGatitoQuieto;
    private ArrayList<Image> HachaAnimacion;
    private ArrayList<Image> PicoAnimacion;
    private ArrayList<Image> OzAnimacion;
    private ArrayList<Image> attackAnimation;
    private ArrayList<Image> defenseAnimation;

    //move validations
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveLeft;

    // actions
    private boolean upPressed;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean atack;
    private boolean defense;
    private boolean toolAction;

    private int contadorDefense;

    //dimensiones personajes
    private double widthAvatar;
    private double heightAvatar;

    public Avatar(Canvas canvas, InventoryManagerTool inventoryManagerTool, InventoryManagerResources inventoryManagerResources){
        this.canvas = canvas;
        this.state="idle";
        this.inventoryManagerTool = inventoryManagerTool;
        this.inventoryManagerResources = inventoryManagerResources;
        this.stateToolTypeAction = "";
        this.typeSeed = "";
        this.selectionInventoryResource = false;

        this.upWalking = new ArrayList<>();
        this.downWalking = new ArrayList<>();
        this.rigthUpWalking = new ArrayList<>();
        this.rigthDownWalking = new ArrayList<>();
        this.lefthUpWalking = new ArrayList<>();
        this.lefthDownWalking = new ArrayList<>();
        this.idleGatitoQuieto = new ArrayList<>();
        this.HachaAnimacion = new ArrayList<>();
        this.PicoAnimacion = new ArrayList<>();
        this.OzAnimacion = new ArrayList<>();
        this.attackAnimation = new ArrayList<>();
        this.defenseAnimation = new ArrayList<>();

        this.moveUp = true;
        this.moveDown = true;
        this.moveRight = true;
        this.moveLeft = true;
        this.atack = false;
        this.defense = false;

        this.graphicsContext = canvas.getGraphicsContext2D();
        position = new Position(250, 550);

        for (int i = 0; i < 2; i++){

            Image upWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeFrente/frente_000"+i+".png"));
            upWalking.add(upWalkingImage);

        }
        for (int i = 0; i < 2; i++){

            Image downWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeAtras/atras_000"+i+".png"));
            downWalking.add(downWalkingImage);

        }
        for (int i = 0; i < 4; i++){

            Image rigthUpWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeDerechaAdelante/derechaFrente_000"+i+".png"));
            rigthUpWalking.add(rigthUpWalkingImage);

        }

        for (int i = 0; i < 3; i++){

            Image rigthDownWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeDerechaAtras/derechaAtras_000"+i+".png"));
            rigthDownWalking.add(rigthDownWalkingImage);

        }

        for (int i = 0; i < 4; i++){

            Image leftUpWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeIzquierdaAdelante/izquierdaFrente_000"+i+".png"));
            lefthUpWalking.add(leftUpWalkingImage);

        }

        for (int i = 0; i < 3; i++){

            Image leftDownWalkingImage = new Image(getClass().getResourceAsStream("/animation/hero/animacionCaminarDeIzquierdaAtras/IzquierdaAtras_000"+i+".png"));
            lefthDownWalking.add(leftDownWalkingImage);

        }

        for (int i = 0; i < 3; i++){
            Image idleGatito = new Image(getClass().getResourceAsStream("/animation/hero/animacionIdleGatito/Idle_gatito_000"+i+".png"));
            idleGatitoQuieto.add(idleGatito);
        }
        for (int i = 0; i < 9; i++){
            Image hachaIzquierda = new Image(getClass().getResourceAsStream("/animation/hero/animacionChambaHachaIzquierda/gatoHachaIzquierda_000"+i+".png"));
            HachaAnimacion.add(hachaIzquierda);
        }
        for (int i = 0; i < 9; i++){
            Image pico = new Image(getClass().getResourceAsStream("/animation/hero/animacionChambaPico/gatoPico_000"+i+".png"));
            PicoAnimacion.add(pico);
        }
        for (int i = 0; i < 9; i++){
            Image oz = new Image(getClass().getResourceAsStream("/animation/hero/animacionChambaOz/gatoOz_000"+i+".png"));
            OzAnimacion.add(oz);
        }
        for (int i = 0; i < 9; i++){
            Image attack = new Image(getClass().getResourceAsStream("/animation/hero/GatoAtaque/ataque_000"+i+".png"));
            attackAnimation.add(attack);
        }
        for (int i = 0; i < 4; i++){
            Image escudo = new Image(getClass().getResourceAsStream("/animation/hero/GatoEscudo/escudo_000"+i+".png"));
            defenseAnimation.add(escudo);
        }

        this.widthAvatar=idleGatitoQuieto.get(frame%2).getWidth();
        this.heightAvatar=idleGatitoQuieto.get(frame%2).getHeight();

        health = 5;

        crops = new ArrayList<>(10);

    }

    public void paint(){
        onMove();
        if(toolAction == true && stateToolTypeAction!="") {

            if (stateToolTypeAction.equals("hacha")) {
                graphicsContext.drawImage(HachaAnimacion.get(frame % 8), position.getX(), position.getY(), widthAvatar, heightAvatar);
                frame++;
            } else if (stateToolTypeAction.equals("pico")) {
                graphicsContext.drawImage(PicoAnimacion.get(frame % 8), position.getX(), position.getY(), widthAvatar, heightAvatar);
                frame++;
            } else if (stateToolTypeAction.equals("oz")) {
                graphicsContext.drawImage(OzAnimacion.get(frame % 8), position.getX(), position.getY(), widthAvatar, heightAvatar);
                frame++;
            }
        } else if(atack == true){

            System.out.println("x "+getXavatar());
            System.out.println("y "+getYavatar());

            //aca debe ir la animación de ataque
            graphicsContext.drawImage(attackAnimation.get(frame%8), position.getX()-10, position.getY()-26,widthAvatar*1.3,heightAvatar*1.3);
            frame++;

        } else if (defense == true) {


            if (frame < defenseAnimation.size() - 1) {
                graphicsContext.drawImage(defenseAnimation.get(frame % 3), position.getX() + 7, position.getY() - 5, widthAvatar * 0.8, heightAvatar * 0.9);
                frame++;
            }else{
                graphicsContext.drawImage(defenseAnimation.get(3), position.getX() + 7, position.getY() - 5, widthAvatar * 0.8, heightAvatar * 0.9);
            }



        } else if(state=="idle"){
            graphicsContext.drawImage(idleGatitoQuieto.get(frame%2),position.getX(), position.getY(),widthAvatar,heightAvatar);

            frame++;
        }
        else if(state == "left_up"){
            graphicsContext.drawImage(lefthUpWalking.get(frame%4), position.getX(), position.getY(),widthAvatar,heightAvatar);

            frame++;
        }
         else if(state == "left_down"){

            graphicsContext.drawImage(lefthDownWalking.get(frame%3), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "right_up"){

            graphicsContext.drawImage(rigthUpWalking.get(frame%4), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "right_down"){

            graphicsContext.drawImage(rigthDownWalking.get(frame%3), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "up"){

            graphicsContext.drawImage(upWalking.get(frame%2), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "down"){

            graphicsContext.drawImage(downWalking.get(frame%2), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "right"){

            graphicsContext.drawImage(rigthUpWalking.get(frame%4), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;
        }
        else if(state == "left"){

            graphicsContext.drawImage(lefthUpWalking.get(frame%4), position.getX(), position.getY(),widthAvatar,heightAvatar);
            frame++;

        }

    }

    //--------------------------------PROPORTIONS AVATAR------------------------------------------

    public double getWidthAvatar(){

        return widthAvatar;
    }
    public double getHeightAvatar(){

        return heightAvatar;
    }

    public double getXavatar(){

        return position.getX();
    }
    public double getYavatar(){

        return position.getY();
    }

    public Position getPosition(){
        return position;
    }

    public void setSizeAvatar(double factor){
        this.widthAvatar = widthAvatar*factor;
        this.heightAvatar = heightAvatar*factor;
    }

    //-----------------------Tools------------------------------------------
    public String getStateToolTypeAction(){
        return stateToolTypeAction;
    }
    public void setAxeTool(AxeTool axeTool){
        this.axeTool = axeTool;
    }

    public AxeTool getAxeTool(){
        return axeTool;
    }

    public void setPeakTool(PeakTool peakTool){
        this.peakTool = peakTool;
    }

    public PeakTool getPeakTool(){
        return peakTool;
    }

    public void setSickleTool(SickleTool sickleTool){
        this.sickleTool = sickleTool;
    }

    public SickleTool getSickleTool(){
        return sickleTool;
    }

    //-------------------Movements----------------------------------------------
    public void setPositionX(double x){
        position.setX(x);
    }
    public void setPositionY(double y){
        position.setY(y);
    }

    public void setMoveUp(boolean moveUp){
        this.moveUp = moveUp;

    }
    public void setMoveDown(boolean moveDown){
        this.moveDown = moveDown;

    }
    public void setMoveLeft(boolean moveLeft){
        this.moveLeft = moveLeft;

    }
    public void setMoveRight(boolean moveRight){
        this.moveRight = moveRight;

    }

    public void onMove(){

        if (left && upPressed && moveUp && moveLeft) {
            position.setX(position.getX()-15+1);
            position.setY(position.getY()-15+1);

            state = "left_down";
        } else if (left && down && moveDown && moveLeft) {
            position.setX(position.getX()-15+1);
            position.setY(position.getY()+15-1);

            state = "left_up";
        } else if (right && upPressed && moveUp && moveRight) {
            position.setX(position.getX()+15-1);
            position.setY(position.getY()-15+1);

            state = "right_down";
        } else if (right && down && moveDown && moveRight) {
            position.setX(position.getX()+15-1);
            position.setY(position.getY()+15-1);

            state = "right_up";
        } else if (upPressed && moveUp) {
            position.setY(position.getY()-15);

            state = "down";
        } else if (down && moveDown) {
            position.setY(position.getY()+15);

            state = "up";
        } else if (left && moveLeft) {
            position.setX(position.getX()-15);

            state = "left";
        } else if (right && moveRight) {
            position.setX(position.getX()+15);

            state = "right";
        }

    }

    public void setOnKeyPressed(KeyEvent event){
        switch (event.getCode()){
           case UP -> { upPressed = true; }
           case DOWN -> { down = true;}
           case RIGHT -> { right = true;}
           case LEFT -> { left = true;}
            case SPACE ->{atack = true;}
            case M ->{defense=true;}
            case T -> {
                toolAction = true;
                if (stateToolTypeAction.equals("hacha") && axeTool != null) {
                    axeTool.reduceDurability();
                } else if (stateToolTypeAction.equals("pico") && peakTool != null) {
                    peakTool.reduceDurability();
                } else if (stateToolTypeAction.equals("oz") && sickleTool != null) {
                    sickleTool.reduceDurability();
                } else {
                    //System.out.println("Error: herramienta no seleccionada o no inicializada.");
                    toolAction = false;
                }
           }
            case DIGIT1 -> {
                if(inventoryManagerTool.selectItemIndex(1).equals("Axe")){
                    this.stateToolTypeAction = "hacha";
                }else if(inventoryManagerTool.selectItemIndex(1).equals("Peak")){
                    this.stateToolTypeAction = "pico";
                }else if(inventoryManagerTool.selectItemIndex(1).equals("Sickle")){
                    this.stateToolTypeAction = "oz";
                }
            }
            case DIGIT2 -> {
                if(inventoryManagerTool.selectItemIndex(2).equals("Axe")){
                    this.stateToolTypeAction = "hacha";
                }else if(inventoryManagerTool.selectItemIndex(2).equals("Peak")){
                    this.stateToolTypeAction = "pico";
                }else if(inventoryManagerTool.selectItemIndex(2).equals("Sickle")){
                    this.stateToolTypeAction = "oz";
                }
            }
            case DIGIT3 -> {
                if(inventoryManagerTool.selectItemIndex(3).equals("Axe")){
                    this.stateToolTypeAction = "hacha";
                }else if(inventoryManagerTool.selectItemIndex(3).equals("Peak")){
                    this.stateToolTypeAction = "pico";
                }else if(inventoryManagerTool.selectItemIndex(3).equals("Sickle")){
                    this.stateToolTypeAction = "oz";
                }
            }

            case P -> {
               int typeResource = 0;
               if(selectionInventoryResource){

                   Crop newCrop = new Crop(this.canvas,getXavatar(), getYavatar(),typeSeed,this,inventoryManagerResources);
                   crops.add(newCrop);
                   inventoryManagerResources.useItem(typeSeed,1);

               }
               Crop newCrop = new Crop(this.canvas,getXavatar(), getYavatar(),typeSeed,this,inventoryManagerResources);
               crops.add(newCrop);
            }
            case A ->{
                int indexNumberInventory = 0;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }
            case S ->{
                int indexNumberInventory = 1;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }
            case D ->{
                int indexNumberInventory = 2;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }
            case F ->{
                int indexNumberInventory = 3;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }
            case G ->{
                int indexNumberInventory = 4;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }
            case H ->{
                int indexNumberInventory = 5;
                this.typeSeed=inventoryManagerResources.selectItemAtIndex(indexNumberInventory);
                this.selectionInventoryResource=true;

            }

        }
    }

    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()){
            case UP -> {state="idle"; upPressed = false;}
            case DOWN -> {state="idle"; down = false;}
            case RIGHT -> {state="idle"; right = false;}
            case LEFT -> {state="idle"; left = false;}
            case SPACE ->{state="idle"; atack = false;}
            case T ->{state="idle"; toolAction = false;}
            case M ->{state="idle"; defense = false; frame=0;}
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }
}
