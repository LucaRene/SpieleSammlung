package Snake;

import processing.core.PApplet;

public class Game extends PApplet {

    int spielfeldGroeße = 30;
    int spielfeldSkalierung = 20;
    int maxLänge = 100;
    float maxTick = 0.1f;
    int spielerGroeße;
    float tickZeit = 0;
    String richtung;
    String letzteRichtung;
    int[][] spielfeld;
    int[][] spielerPos;
    int[] posFressen;


    public static void main(String[] args) {
        PApplet.main(new String[]{"Snake.Game"});
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        rectMode(CENTER);
        spielfeld = new int[spielfeldGroeße][spielfeldGroeße];
        spielerPos = new int[maxLänge][2];
        posFressen = new int[2];
        reset();
    }

    public void draw() {
        background(0);
        tickZeit += 1 / frameRate;
        if (tickZeit > maxTick) {
            bewegeSpieler();
            testKollision();
            updateSpielfeld();
            tickZeit = 0;
        }
        zeichneSpielfeld();
        spielerEingabe();
    }

    private void testKollision() {
        if(spielerPos[0][0] == posFressen[0] && spielerPos[0][1] == posFressen[1]){
            spielerGroeße += 1;
            spielerPos[spielerGroeße - 1][0] = posFressen[0];
            spielerPos[spielerGroeße - 1][1] = posFressen[1];
            setzeBeute();
        }
    }

    // 0 = nichts; 1=Spieler; 2=Beute;

    private void updateSpielfeld() {
        int i = 0;
        int u = 0;
        while (i < spielfeldGroeße) {
            while (u < spielfeldGroeße) {
                spielfeld[i][u] = 0;
                u++;
            }
            u = 0;
            i++;
        }
    }

    private void zeichneSpielfeld() {
        int i = 1;
        int u = 1;
        spielfeld[posFressen[0]][posFressen[1]] = 2;
        int j = 0;
        while (j<maxLänge){
            if(spielerPos[j][0] != 0 && spielerPos[j][1] != 0) {
                spielfeld[spielerPos[j][0]][spielerPos[j][1]] = 1;
            }
            j++;
        }
        while (i < spielfeldGroeße) {
            while (u < spielfeldGroeße) {
                if (spielfeld[i][u] == 0) {
                    ellipse(spielfeldSkalierung * i, spielfeldSkalierung * u, 5, 5);
                } else if (spielfeld[i][u] == 1) {
                    ellipse(spielfeldSkalierung * i, spielfeldSkalierung * u, 20, 20);
                } else {
                    rect(spielfeldSkalierung * i, spielfeldSkalierung * u, 20, 20);
                }
                u++;
            }
            u = 1;
            i++;
        }
    }

    private void bewegeSpieler() {
        int old_position_x = spielerPos[0][0];
        int old_position_y = spielerPos[0][1];

        if (richtung.equals("UP")) {
            spielerPos[0][1] -= 1;
            letzteRichtung = "UP";
        } else if (richtung.equals("DOWN")) {
            spielerPos[0][1] += 1;
            letzteRichtung = "DOWN";
        } else if (richtung.equals("LEFT")) {
            spielerPos[0][0] -= 1;
            letzteRichtung = "LEFT";
        } else if (richtung.equals("RIGHT")) {
            spielerPos[0][0] += 1;
            letzteRichtung = "RIGHT";
        }

        //alle anderen Punkte dem Kopf hinterher
        int i = 1;
        while (i < spielerGroeße) {
            int tempx = spielerPos[i][0];
            int tempy = spielerPos[i][1];
            spielerPos[i][0] = old_position_x;
            spielerPos[i][1] = old_position_y;
            old_position_x = tempx;
            old_position_y = tempy;
            i++;
        }
        if(spielerPos[0][0]<1 || spielerPos[0][0] > spielfeldGroeße - 1 || spielerPos[0][1] < 1 || spielerPos[0][1] > spielfeldGroeße - 1){
            reset();
        }
        i = 1;
        while (i<spielerGroeße){
            if(spielerPos[0][0] == spielerPos[i][0] && spielerPos[0][1] == spielerPos[i][1]) reset();
            i++;
        }
    }
    void spielerEingabe() {
        if (keyPressed) {
            if (keyCode == UP && letzteRichtung != "DOWN") {
                richtung = "UP";
            } else if (keyCode == DOWN && letzteRichtung != "UP") {
                richtung = "DOWN";
            } else if (keyCode == LEFT && letzteRichtung != "RIGHT") {
                richtung = "LEFT";
            } else if (keyCode == RIGHT && letzteRichtung != "LEFT") {
                richtung = "RIGHT";
            }
        }
    }

    private void setzeBeute() {
        posFressen[0] = floor(random(1, spielfeldGroeße));
        posFressen[1] = floor(random(1, spielfeldGroeße));
    }

    private void reset() {
        setzeBeute();
        int i = 0;
        while (i < maxLänge) {
            spielerPos[i][0] = 0;
            spielerPos[i][1] = 0;
            i++;
        }
        spielerPos[0][0] = spielfeldGroeße / 2;
        spielerPos[0][1] = spielfeldGroeße / 2;
        spielerGroeße = 1;
        richtung = "UP";
        letzteRichtung = "UP";
    }
}