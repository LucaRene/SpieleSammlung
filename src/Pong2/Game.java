package Pong2;

import processing.core.PApplet;

public class Game extends PApplet{

    float spieler1_x, spieler1_y, spieler2_x, spieler2_y, ball_x, ball_y, ball_g_x, ball_g_y;
    int hoeheFenster, breiteFenster, hoeheSpieler, breiteSpieler, score1, score2;
    int zustand, spielerzahl, maxScore;
    float zeit, gesamtZeit = 0.5f;
    KeyHandler k;

    public static void main(String[] args) {
        PApplet.main(new String[] {"Pong2.Game"});
    }

    public void settings(){
        size(1080, 720);
    }

    public void setup(){
        k = new KeyHandler(this);
        rectMode(CENTER);
        textAlign(CENTER, CENTER);
        breiteFenster = 1080;
        hoeheFenster = 720;
        zustand = 0;
        hoeheSpieler = 100;
        breiteSpieler = 20;
        reset();
    }

    public void draw(){
        background(20);
        fill(255);

        //Startbildschirm
        if (zustand == 0){
           zustand0();
        }
        //Spiel für einen Spieler
        else if (zustand == 1){
            zustand1();
        }
        //Einstellung bis wie viele Punkte gespielt wird
        else if (zustand == 2){
            zustand2();
        }
        //Spiel für zwei Spieler
        else if (zustand == 3){
            background(20);
            fill(255);
            spieler2_x = breiteFenster - 20;
            spieler2_y = 50;
            rect(spieler1_x, spieler1_y, breiteSpieler, hoeheSpieler);
            rect(spieler2_x, spieler2_y, breiteSpieler, hoeheSpieler);
            rect(ball_x,ball_y,10,10);
            s1Bewegen();
            s2Bewegen();
            ballBewegen2();
            //updateText2();
    }
        //Schlussbildschirm 1 Spieler
        else if (zustand == 4){

        }
        //Schlussbildschirm 2 Spieler
        else if (zustand == 5){

        }
    }

    private void checkClick() {
        if(mousePressed){
            if(mouseY < hoeheFenster/2 + 40 && mouseY > hoeheFenster/2 - 40) {
                if (mouseX < (breiteFenster/3)+100 && mouseX > (breiteFenster/3-100)){
                    if(zustand == 0) {
                        spielerzahl = 1;
                        zustand = 1;
                    }
                    else if(zustand == 2 && zeit > gesamtZeit){
                        maxScore = 3;
                        zustand = 3;
                        zeit = 0;
                    }
                }
                else if (mouseX < ((breiteFenster/3)*2 + 100) && mouseX > (breiteFenster/3)*2 - 100){
                    if(zustand == 0){
                        spielerzahl = 2;
                        zustand = 2;
                    }
                    else if(zustand == 2 && zeit > gesamtZeit){
                        maxScore = 5;
                        zustand = 3;
                        zeit = 0;
                    }
                }
            }
        }
    }

    private void zeit(){
        zeit += 1/frameRate;
    }

    public void keyPressed(){
        k.updateA();
    }

    public void keyReleased(){
        k.updateB();
    }

    private void s1Bewegen(){
        if(k.w){
            if(spieler1_y > hoeheSpieler/2f) spieler1_y -= 10;
        }
        if(k.s){
            if(spieler1_y < (hoeheFenster-hoeheSpieler/2.0f)) spieler1_y += 10 ;
        }
    }

    private void s2Bewegen(){
        if(k.up){
            if(spieler2_y > hoeheSpieler/2f) spieler2_y -= 10;
        }
        if(k.down){
            if(spieler2_y < (hoeheFenster-hoeheSpieler/2.0f)) spieler2_y += 10 ;
        }
    }

    private void ballBewegen1() {
        ball_x += ball_g_x;
        ball_y -= ball_g_y;
        if (ball_x < spieler1_x + breiteSpieler / 2f) {
            if (ball_y > spieler1_y - hoeheSpieler / 2f && ball_y < spieler1_y + hoeheSpieler / 2f) {
                ball_g_x = (-ball_g_x) + 0.2f;
                ball_g_y = ball_g_y - (spieler1_y - ball_y) * 0.1f;
                score1 += 1;
            } else {
                zustand = 0;
                reset();
            }
        }
        if(ball_y < 20 || ball_y > hoeheFenster-20) ball_g_y = -ball_g_y;
        if(ball_x > breiteFenster-20) ball_g_x = -ball_g_x;
    }

    private void ballBewegen2(){
        ball_x += ball_g_x;
        ball_y -= ball_g_y;
        if(ball_x < spieler1_x + breiteSpieler/2f){
            if(ball_y > spieler1_y - hoeheSpieler/2f && ball_y < spieler1_y + hoeheSpieler/2f) {
                ball_g_x = -ball_g_x + 0.15f;
                ball_g_y -= (spieler1_y - ball_y) * 0.08f;
            }
            else {
                score2 += 1;
                if(score2 == maxScore){
                    zustand = 5;
                }
            }
        }
        else if(ball_x > spieler2_x - breiteSpieler/2f){
        }

    }

    private void reset(){
        score1 = 0;
        score2 = 0;
        ball_x = breiteFenster/2;
        ball_y = hoeheFenster/2;
        ball_g_x = -6f;
        ball_g_y = 0;
        spieler1_x = 20;
        spieler1_y = 50;
    }

    private void updateText(){
        fill(255);
        text("Score: " + score1, breiteFenster-70, 20);
    }

    private void zustand0(){
        rect(breiteFenster/3, hoeheFenster/2, 200, 80);
        rect(breiteFenster/3 * 2, hoeheFenster/2, 200, 80);
        fill(0);
        textSize(18);
        text("1 Spieler", breiteFenster/3, hoeheFenster/2);
        text("2 Spieler", (breiteFenster/3)*2, hoeheFenster/2);
        checkClick();
    }

    private void zustand1(){
        background(20);
        fill(255);
        rect(spieler1_x, spieler1_y, breiteSpieler, hoeheSpieler);
        rect(ball_x,ball_y,10,10);
        s1Bewegen();
        ballBewegen1();
        updateText();
    }

    private void zustand2(){
        zeit();
        rect(breiteFenster/3f, hoeheFenster/2, 200, 80);
        rect(breiteFenster/3f * 2, hoeheFenster/2, 200, 80);
        fill(0);
        textSize(18);
        text("3 Punkte", breiteFenster/3, hoeheFenster/2);
        text("5 Punkte", (breiteFenster/3)*2, hoeheFenster/2);
        checkClick();
    }
}
