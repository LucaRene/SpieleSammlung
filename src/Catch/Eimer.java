package Catch;

import processing.core.PImage;
import processing.core.PApplet;

public class Eimer{

    PApplet p;
    String eimerPfad = "bucket.png";
    int hoehe = 400;
    float x, y;
    PImage bucket;

    Eimer(PApplet p){
        this.p = p;
        bucket = p.loadImage(eimerPfad);
    }

    void update(){
        y = hoehe;
        x = p.mouseX;
        p.image(bucket,x,y);
    }

    boolean testKollision(float x, float y){
        float randX = this.x - bucket.width/2;
        float randY = this.y - bucket.height/2;
        if(x > randX && x < randX + bucket.width){
            if(y > randY && y < randY + bucket.height){
                return true;
            } else return false;
        } else return false;
    }
}
