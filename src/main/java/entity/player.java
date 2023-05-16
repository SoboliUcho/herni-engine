package entity;

import game.keyboard;
import game.game;

//glpat-64qafhwcij1dKBRjjpnN
public class player extends entita {
    inventory inventory = new inventory();
    int xDefault;
    int yDefaul;
    keyboard keyboard;

    public player(int x, int y, game game, keyboard keyboard) {
        super(x, y, game);
        xDefault = x;
        yDefaul = y;
        type = "player";
        this.keyboard = keyboard;
        setDefault();
        openImage(type);
    }

    public void setDefault() {
        speed = 1;
        strange = 1;
        lives = 10;
        size = 1;

        xPozition = xDefault;
        yPozition = yDefaul;
    }

    public void movePlayer() {
        if (keyboard.upIsPress) {
            yPozition = yPozition + speed;
        }
        if (keyboard.downIsPress) {
            yPozition = yPozition - speed;
        }
        if (keyboard.rightIsPress) {
            xPozition = xPozition + speed;
        }
        if (keyboard.leftIsPress) {
            xPozition = xPozition - speed;
        }
    }

    void catchElements() {

    }

}
