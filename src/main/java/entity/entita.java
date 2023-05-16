package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.game;

public class entita {
    // pozition
    public int xPozition;
    public int yPozition;
    public String type;
    public int strange;
    public int size;
    public int speed;
    public int lives;
    public Image image;
    public game game;

    int xX;
    int yY;

    public entita(int x, int y, game game) {
        this.game = game;
        this.xPozition = x * this.game.elementSize;
        this.yPozition = y * this.game.elementSize;
        this.strange = 0;
        this.xX = xPozition;
        this.yY = yPozition;

    }

    boolean isLive() {
        if (lives < 1) {
            return false;
        } else {
            return true;
        }
    }

    void atack() {
        // TODO atack
    }

    void makeDamage(entita entita) {
        entita.lives = entita.lives - strange;
    }

    public int[] entitaPozicion() {
        int[] pozicion = { xPozition, yPozition };
        return pozicion;
    }

    public void openImage(String name) {
        String fullname = "/img/" + name + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.print("successful image uploaded of ");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // int[] picturePozition = new int[2];
        // picturePozition[0] = xPozition * game.elementSize;
        // picturePozition[1] = yPozition * game.elementSize;

        System.out.println(type + " [" + xPozition + " " + yPozition + "]");
        // System.out.println("[" + picturePozition[0] + " " + picturePozition[1]+"]");

        // System.out.print(picturePozition[1]);
        // g2.drawImage(image, picturePozition[0], picturePozition[1], game.elementSize,
        // game.elementSize, null);
        g2.drawImage(image, xPozition, yPozition, game.elementSize, game.elementSize, null);

        // g2.dispose();
    }

    void Randompozicion() {
        Random rand = new Random();
        xX = rand.nextInt(600);
        yY = rand.nextInt(600);
        System.out.println(xX);
        System.out.println(yY);
    }

    public void moveRandom() {

        int newX = xPozition;
        int newY = yPozition;
        System.out.println(xX);
        System.out.println(yY);

        if (xPozition >  xX-speed && xPozition <  xX+speed) {
            Randompozicion();
        }
        if (yPozition >  yY-speed && yPozition <  yY+speed) {
            Randompozicion();
        }

        if (xX < xPozition) {
            newX = xPozition - speed;
        }
        if (xX > xPozition) {
            newX = xPozition + speed;
        }
        if (yY < yPozition) {
            newY = yPozition - speed;
        }
        if (yY > yPozition) {
            newY = yPozition + speed;
        }
        xPozition = newX;
        yPozition = newY;
    }
}
