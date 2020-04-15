package Pong2;

import processing.core.PApplet;

import java.security.UnresolvedPermission;

public class KeyHandler{

    boolean up, down, w, s;
    PApplet p;

    KeyHandler(PApplet p){
        up = false;
        down = false;
        w = false;
        s = false;
        this.p = p;
    }

    void updateA(){
        if(p.keyCode == p.UP){
            up = true;
        }
        if(p.keyCode == p.DOWN){
            down = true;
        }
        if(p.key == 'w'){
            w = true;
        }
        if(p.key == 's'){
            s = true;
        }
    }

    void updateB(){
        if(p.keyCode == p.UP){
            up = false;
        }
        if(p.keyCode == p.DOWN){
            down = false;
        }
        if(p.key == 'w'){
            w = false;
        }
        if(p.key == 's'){
            s = false;
        }
    }
}
