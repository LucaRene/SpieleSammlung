package Pong;

import processing.core.PApplet;

public class Game extends PApplet{

    float spieler_x, spieler_y, ball_x, ball_y, ball_g_x, ball_g_y;
    int hoeheFenster, breiteFenster, hoeheSpieler, breiteSpieler, score;

    //Hinweis: Bei Änderung der Auflösung muss size(), hoeheFenster und breiteFenster geändert werden!

    public static void main(String[] args) {
        PApplet.main(new String[] {"Pong.Game"});
    }

    public void settings(){
        size(1080,720);
    }

    public void setup(){
        spieler_x = 20;
        spieler_y = 360;
        ball_x = 540;
        ball_y = 360; 
        ball_g_x = -6;
        ball_g_y = 0;
        score = 0;
        hoeheFenster = 720;
        breiteFenster = 1080;
        hoeheSpieler = 100;
        breiteSpieler = 20;
        rectMode(CENTER);
    }

    public void draw(){
        background(0);
        rect(spieler_x,spieler_y,breiteSpieler,hoeheSpieler);
        rect(ball_x,ball_y,10,10);
        spielerVersetzen();
        ballVersetzen();
        updateText();
    }

    void spielerVersetzen(){
        if(keyPressed){
            if(keyCode == DOWN){
                if(spieler_y < (hoeheFenster-hoeheSpieler/2.0f)) spieler_y += 10 ;
            }
            else if(keyCode == UP){
                if(spieler_y > hoeheSpieler/2f) spieler_y -= 10;
            }
        }
    }

    void ballVersetzen(){
        ball_x += ball_g_x;
        ball_y -= ball_g_y;
        if(ball_x < spieler_x+breiteSpieler/2f){
            if(ball_y > spieler_y - hoeheSpieler/2f && ball_y < spieler_y + hoeheSpieler/2f){
                ball_g_x = (-ball_g_x) + 0.2f;
                ball_g_y = ball_g_y -(spieler_y-ball_y) * 0.1f;
                score += 1;
            } else {
                setup();
            }
        }
        if(ball_y < 20 || ball_y > hoeheFenster-20) ball_g_y = -ball_g_y;
        if(ball_x > breiteFenster-20) ball_g_x = -ball_g_x;
    }

    void updateText(){
        text("Score: " + score, breiteFenster-70, 20);
    }
}
