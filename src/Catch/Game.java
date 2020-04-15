package Catch;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {

    int fensterBreite = 500, fensterHöhe = 500;
    Eimer eimer;
    Tropfen[] tropfen;
    int score = 0;
    float zeit = 30;

    public static void main(String[] args) {
        PApplet.main(new String[]{"Catch.Game"});
    }

    public void settings(){
        size(500, 500);
    }

    public void setup(){
        noCursor();
        imageMode(CENTER);
        eimer = new Eimer(this);
        tropfen = new Tropfen[10];
        int h = 0;
        while(h<tropfen.length){
            tropfen[h] = new Tropfen(this, fensterBreite);
            h++;
        }
    }

    public void draw(){
        background(100);
        eimer.update();
        if(zeit>0) {
            int h = 0;
            while (h < tropfen.length) {
                tropfen[h].update();
                if (eimer.testKollision(tropfen[h].gibX(), tropfen[h].gibY())) {
                    tropfen[h].createDrop();
                    score++;
                }
                h++;
            }
            text("Punkte: " + score, 25, 25);
            text("Zeit: " + Math.round(zeit) + "s", 450, 25);
            zeit();
        }
        else {
            text("Zeit abgelaufen! Punkte: " + score, 170, 250);
        }
    }

    void zeit(){
        zeit -= 1/frameRate;
    }
}
