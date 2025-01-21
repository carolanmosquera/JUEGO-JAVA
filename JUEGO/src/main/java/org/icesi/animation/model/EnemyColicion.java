package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class EnemyColicion extends Avatar{

    private double posionX;
    private double posionY;
    private double ancho;
    private double alto;
    private int life;
    private Avatar avatar;
    private InventoryManagerResources inventoryManagerResources;

    private CollisionManager rangoDeteccion;
    private CollisionManager rangoAtaque;

    private CheckGeneralColisions colicionDeteccion;

    private Position position;

    private String direction;
    private int numberRamdom;
    private int speed;

    private EnemyEntity enemyObject;

    private Canvas canvas;

    private  IDistance iDistance;


    private GraphicsContext gc;

    public EnemyColicion(Canvas canvas, Avatar avatar, InventoryManagerResources inventoryManagerResources, EnemyEntity enemy) {
        super(canvas,null,inventoryManagerResources);
        this.canvas = canvas;
        this.gc=canvas.getGraphicsContext2D();

        this.avatar = avatar;
        this.enemyObject = enemy;
        this.inventoryManagerResources = inventoryManagerResources;
        this.rangoDeteccion = new CollisionManager(canvas);
        this.rangoAtaque = new CollisionManager(canvas);
        this.speed=9;

        this.posionX = enemyObject.getPosionX();
        this.posionY = enemyObject.getPosionY();
        this.position=new Position(posionX,posionY);
        this.ancho = enemyObject.getAncho();
        this.alto = enemyObject.getAlto();

        //collision detection range
//        rangoDeteccion.setRangoDeColicionRectangulo(true);//with real limits, it makes sure that the collision are external
//        rangoDeteccion.defineDimension(posionX,posionY,ancho,alto,100);
//        rangoDeteccion.setColicionesInferiores(false);//I make sure that the collicion action work in the limit

//        //attack range
//        rangoAtaque.setRangoDeColicionRectangulo(false);//it makes sure that the collision are internal in the center
//        rangoAtaque.defineDimension(posionX,posionY,ancho,alto,50);
//        rangoAtaque.setColicionesInferiores(true);//it makes sure that the collision action just woks in the limits of the center

        Random numRamdom = new Random();
        this.numberRamdom = numRamdom.nextInt(4);
        chooseDirection(numberRamdom);

        this.colicionDeteccion = new CheckGeneralColisions(avatar,enemyObject,canvas);
    }

    public void onMove(){
        if (direction.equals("up")) {
            position.setY(position.getY()-speed);

        } else if (direction.equals("down")) {
            position.setY(position.getY()+speed);

        } else if (direction.equals("left")) {
            position.setX(position.getX()+speed);

        } else if (direction.equals("right")) {
            position.setX(position.getX() - speed);
        }
    }

    public void resetDirection(String directionGiven) {
        Random random = new Random();
        int value1 = 0; // Primer valor
        int value2 = 0; // Segundo valor

        int randomValue = 0;

        if (directionGiven.equals("up")) {
            position.setY(position.getY()+speed);
            value1 = 2;
            value2 =3;
            randomValue = random.nextBoolean() ? value1 : value2;
            chooseDirection(randomValue);
        } else if (directionGiven.equals("down")) {
            position.setY(position.getY()-speed);
            value1 = 1;
            value2 =3;
            randomValue = random.nextBoolean() ? value1 : value2;
            chooseDirection(randomValue);
        } else if (directionGiven.equals("left")) {
            position.setX(position.getX()-speed);
            value1 = 1;
            value2 =4;
            randomValue = random.nextBoolean() ? value1 : value2;
            chooseDirection(randomValue);
        } else if (directionGiven.equals("right")) {
            position.setX(position.getX()+speed);
            value1 = 1;
            value2 =3;
            randomValue = random.nextBoolean() ? value1 : value2;
            chooseDirection(randomValue);
        }
        System.out.println("Dirección después de reset: " + direction);
    }

    public void chooseDirection(int number){
        switch (number){

            case 1:
                System.out.println("eligio arriba");
                this.direction = "up";

                break;

            case 2:
                this.direction = "down";
                break;

            case 3:
                this.direction = "left";
                break;

            case 4:
                System.out.println("eligio right");
                this.direction = "right";
                break;

            default:
                this.direction = "right";
                break;

        }
    }

    public void paint() {
        // Obtener las coordenadas del centro del avatar
        double xCentroAvatar = avatar.getXavatar() + avatar.getWidthAvatar() / 2;
        double yCentroAvatar = avatar.getYavatar() + avatar.getHeightAvatar() / 2;

// Obtener las coordenadas del centro del enemigo
        double xCentroEnemigo = position.getX() + ancho / 2;
        double yCentroEnemigo = position.getY() + alto / 2;

// Rango mínimo para detenerse cerca del avatar
        double rangoMinimo = 10;

// Verificar si el enemigo está muy cerca del avatar
        boolean muyCercaX = Math.abs(xCentroAvatar - xCentroEnemigo) <= rangoMinimo;
        boolean muyCercaY = Math.abs(yCentroAvatar - yCentroEnemigo) <= rangoMinimo;

        double rangoColicioXderecha = xCentroEnemigo + 100;
        double rangoColicioYarriba = yCentroEnemigo - 100;
        double rangoColicioXizquierda = xCentroEnemigo - 100;
        double rangoColicioYabajo = yCentroEnemigo + 100;

        boolean colisionX = xCentroAvatar >= rangoColicioXizquierda && xCentroAvatar <= rangoColicioXderecha;
        boolean colisionY = yCentroAvatar >= rangoColicioYarriba && yCentroAvatar <= rangoColicioYabajo;

        if (colisionX && colisionY) {

// Si el enemigo está lejos del avatar, sigue moviéndose
            if (!muyCercaX) {
                if (xCentroAvatar > xCentroEnemigo) {
                    direction = "right"; // Avatar a la derecha
                    position.setX(position.getX() + speed); // Mover hacia la derecha
                } else if (xCentroAvatar < xCentroEnemigo) {
                    direction = "left"; // Avatar a la izquierda
                    position.setX(position.getX() - speed); // Mover hacia la izquierda
                }
            }

            if (!muyCercaY) {
                if (yCentroAvatar > yCentroEnemigo) {
                    direction = "down"; // Avatar abajo
                    position.setY(position.getY() + speed); // Mover hacia abajo
                } else if (yCentroAvatar < yCentroEnemigo) {
                    direction = "up"; // Avatar arriba
                    position.setY(position.getY() - speed); // Mover hacia arriba
                }
            }else {

            }

        }else {
            onMove();
        }
        gc.setFill(Color.BLUE);
        gc.fillRect(position.getX(), position.getY(), ancho, alto);


    }

    public double getPositionX() {
        return position.getX();
    }
    public double getPositionY() {
        return position.getY();
    }

    public Position getPositionColicion() {
        return position;
    }

    public void setDirection(String directionGiven) {
        this.direction = directionGiven;
    }

    public CheckGeneralColisions getColicionDeteccion() {
        return colicionDeteccion;
    }

    public void updateChekGeneralColisions() {
        this.colicionDeteccion = new CheckGeneralColisions(avatar,enemyObject,canvas);

    }

}
