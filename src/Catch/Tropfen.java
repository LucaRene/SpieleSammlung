package Catch;

import processing.core.PApplet;
import processing.core.PImage;

public class Tropfen {

    PApplet p;
    String pfad = "drop.png";
    float fensterGröße;
    float startY = -1000;
    float geschwindigkeit = 3;
    float x, y;
    PImage bild;

    Tropfen(PApplet applet, float FensterGröße){
        p = applet;
        fensterGröße = FensterGröße;
        bild = p.loadImage(pfad);
        createDrop();
    }

    void update(){
        if (!außerhalb()) {
            y += geschwindigkeit;
            p.image(bild, x, y);
        } else createDrop();
    }

    float gibX(){
        return x;
    }

    float gibY(){
        return y;
    }

    boolean außerhalb(){
        if(y > fensterGröße){
            return true;
        } else return false;
    }

    void createDrop(){
        x = p.random(10, fensterGröße);
        y = p.random(startY);
    }
}
