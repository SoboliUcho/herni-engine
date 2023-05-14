package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

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

    public entita(int x, int y) {
        this.xPozition = x;
        this.yPozition = y;
        this.strange = 0;

    }
    boolean isLive (){
        if ( lives < 1){
            return false;
        }
        else{
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
        int[] picturePozition = new int[2];
        picturePozition[0] = xPozition * game.elementSize;
        picturePozition[1] = yPozition * game.elementSize;

        System.out.print("[" + xPozition + " " + yPozition+"]");
        System.out.print("[" + picturePozition[0] + " " + picturePozition[1]+"]");

        // System.out.print(picturePozition[1]);
        g2.drawImage(image, picturePozition[0], picturePozition[1], game.elementSize, game.elementSize, null);
        g2.dispose();
    }
}
