package entity;

import game.keyboard;

//glpat-64qafhwcij1dKBRjjpnN
public class player extends entita {
    inventory inventory = new inventory();

    public player(int x, int y) {
        super(x, y);
        type = "player";
        setDefault();
        openImage(type);

    }

    public void setDefault() {
        speed = 1;
        strange = 1;
        lives = 10;
        size = 1;

        xPozition = 25;
        yPozition = 25;
    }

    public void movePlayer(keyboard keyboard) {
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
