package entity;

import game.keyboard;
import game.lifeBar;

import game.game;

//glpat-64qafhwcij1dKBRjjpnN
public class player extends entita {
    public inventory inventory;
    int xDefault;
    int yDefault;
    keyboard keyboard;
    public lifeBar lifeBar;

    public player(int x, int y, game game, keyboard keyboard) {
        super(x, y, game);
        xDefault = x * game.elementSize;
        yDefault = y * game.elementSize;
        type = "player";
        this.keyboard = keyboard;
        inventory = new inventory(this);
        lifeBar = new lifeBar(this);
        setDefault();
        openImage(type);
        openAttackImage();
    }

    public void setDefault() {
        speed = 2;
        strange = 1;
        lives = 10;
        size = 1;

        xPozition = xDefault;
        yPozition = yDefault;
    }

    public void movePlayer() {
        int previousX = xPozition;
        int previousY = yPozition;

        if (keyboard.upIsPress) {
            yPozition = yPozition - speed;
        }
        if (keyboard.downIsPress) {
            yPozition = yPozition + speed;
        }
        if (keyboard.rightIsPress) {
            xPozition = xPozition + speed;
        }
        if (keyboard.leftIsPress) {
            xPozition = xPozition - speed;
        }
        hitWall(previousX, previousY);
    }

    void catchElements() {

    }

    public void addLives(int quantity) {
        this.lives += quantity;
    }

    public void addStrange(int quantity) {
        this.strange = quantity;
    }

}
