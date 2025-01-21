package org.icesi.animation.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CheckGeneralColisions {
    private boolean colicion;
    private Avatar avatar;
    private CollisionManager objto;
    private CheckGeneralColisions checkColisions;
    private boolean up;
    private GraphicsContext gc;
    private boolean DoColicon;

    private String typeCollicion;

    public CheckGeneralColisions(Avatar avatar, CollisionManager objeto, Canvas canvas) {
        this.avatar = avatar;
        this.objto = objeto;
        this.up = false;
        this.gc = canvas.getGraphicsContext2D();
        this.DoColicon = true;
        this.typeCollicion = "";


    }

    public boolean checkColicion(){
        if(DoColicon) {

            // Calcular el centro del avatar
            double xCentroAvatar = avatar.getXavatar() + avatar.getWidthAvatar() / 2;
            double yCentroAvatar = avatar.getYavatar() + avatar.getHeightAvatar() / 2;

            if (xCentroAvatar >= objto.getColicioXinicial() - 20 || yCentroAvatar >= objto.getColicioYinicial() - 20 || xCentroAvatar <= objto.getColicioXfinal() + 20) {

                // Verificar colisión con el área definida por los rangos del objeto
                boolean colisionX = xCentroAvatar >= objto.getRangoColicioXizquierda() && xCentroAvatar <= objto.getRangoColicioXderecha();
                boolean colisionY = yCentroAvatar >= objto.getRangoColicioYarriba() && yCentroAvatar <= objto.getRangoColicioYabajo();

                // Manejar la colisión si se detecta
                if (colisionX && colisionY) {

                    //System.out.println("entro");

                    if (objto.isColicionesInferiores()) {
                        //System.out.println("colicion inferior");

                        // Restringir movimientos del avatar según la posición relativa al objeto
                        if (xCentroAvatar <= objto.getRangoColicioXizquierda() + 15) { // Cerca del límite izquierdo
                            //System.out.println("izquierda");
                            this.typeCollicion="left";
                            avatar.setMoveRight(false);
                        } else {
                            avatar.setMoveRight(true);
                        }
                        if (xCentroAvatar >= objto.getRangoColicioXderecha() - 10) { // Cerca del límite derecho
                            //System.out.println("derecha");
                            this.typeCollicion="right";
                            avatar.setMoveLeft(false);
                        } else {
                            avatar.setMoveLeft(true);
                        }
                        if (yCentroAvatar >= objto.getRangoColicioYarriba()) { // Cerca del límite superior
                            //System.out.println("arriba");
                            this.typeCollicion="up";
                            if (yCentroAvatar <= objto.getRangoColicioYarriba() + 15) {
                                avatar.setMoveDown(false);
                            }
                        } else {
                            avatar.setMoveDown(true);
                        }

                        if (yCentroAvatar <= objto.getRangoColicioYabajo()) { // Cerca del límite inferior
                            //System.out.println("abajo");
                            this.typeCollicion="down";
                            if (yCentroAvatar >= objto.getRangoColicioYabajo() - 10) {
                                avatar.setMoveUp(false);
                            }

                        } else {
                            avatar.setMoveUp(true);
                        }

                        this.colicion = true;
                    } else {
                        //System.out.println("colicion superior");

                        if (xCentroAvatar <= objto.getColicioXinicial() + 15 && xCentroAvatar < objto.getColicioXfinal()) { // Cerca del límite izquierdo
                            //System.out.println("izquierda");
                            this.typeCollicion="left";
                            avatar.setMoveRight(false);
                        } else {
                            avatar.setMoveRight(true);
                        }
                        if (xCentroAvatar >= objto.getColicioXfinal() - 10) { // Cerca del límite derecho
                            //System.out.println("derecha");
                            this.typeCollicion="right";
                            avatar.setMoveLeft(false);
                        } else {
                            avatar.setMoveLeft(true);
                        }

                        if (yCentroAvatar >= objto.getColicioYinicial()) { // Cerca del límite superior
                            //System.out.println("arriba");
                            this.typeCollicion="up";
                            if (yCentroAvatar <= objto.getColicioYinicial() + 15) {
                                avatar.setMoveDown(false);
                            }
                        } else {
                            avatar.setMoveDown(true);
                        }
                        if (yCentroAvatar <= objto.getColicioYfinal()) { // Cerca del límite inferior
                            //System.out.println("abajo");
                            this.typeCollicion="down";
                            if (yCentroAvatar >= objto.getColicioYfinal() - 20) {
                                avatar.setMoveUp(false);
                            }
                        } else {
                            avatar.setMoveUp(true);
                        }

                        this.colicion = true;
                    }
                } else {
                    this.colicion = false;
                    avatar.setMoveRight(true);
                    avatar.setMoveLeft(true);
                    avatar.setMoveUp(true);
                    avatar.setMoveDown(true);
                }
            }
        }else{
            this.colicion = false;
        }
        return this.colicion;

    }

    public void setDoColicon(boolean doColicon) {
        this.DoColicon = doColicon;
    }

    public boolean DoColicon() {
        return this.DoColicon;
    }

    public void visualizarRangoColicion(){
        gc.setStroke(javafx.scene.paint.Color.RED); // Establecer el color del contorno (rojo para destacar)
        gc.setLineWidth(2); // Grosor del contorno
        gc.strokeRect(objto.getRangoColicioXizquierda(), objto.getRangoColicioYarriba(),
                objto.getRangoColicioXderecha() - objto.getRangoColicioXizquierda(),
                objto.getRangoColicioYabajo() - objto.getRangoColicioYarriba());

    }

}
