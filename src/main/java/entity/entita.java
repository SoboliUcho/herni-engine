package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class entita {
    // pozition
    public int xPozition;
    public int yPozition;
    public String type;
    int strange;
    int size;
    int speed;
    public int lives;
    Image image;

    public entita(int x, int y) {
        this.xPozition = x;
        this.yPozition = y;
        this.strange = 0;

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

}
