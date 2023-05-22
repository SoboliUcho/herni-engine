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
    public int range;
    public int size;
    public int speed;
    public int lives;
    public Image image;
    public game game;

    static Image attackImage;

    int secondsCounter = 0;
    int xX;
    int yY;

    public entita(int x, int y, game game) {
        this.game = game;
        this.xPozition = x * this.game.elementSize;
        this.yPozition = y * this.game.elementSize;
        this.strange = 0;
        this.xX = xPozition;
        this.yY = yPozition;
        this.range = 3;

    }

    boolean isLive() {
        if (lives < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void atack(Graphics2D g2) {
        if (game.keyboard.spaceIsPress) {
            int pozicion = game.elementSize / (range/2);
            g2.drawImage(attackImage, xPozition - pozicion, yPozition - pozicion, game.elementSize * range,
                    game.elementSize * range, null);
        }
    }

    void makeDamage(entita entita) {
        entita.lives = entita.lives - strange;
    }

    public int[] entitaPozicion() {
        int[] pozicion = { xPozition, yPozition };
        return pozicion;
    }

    public void openAttackImage() {
        String fullname = "/img/attack.png";
        try {
            attackImage = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.println("successful image uploaded of Attack");
            // System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        System.out.println(type + " [" + xPozition + ", " + yPozition + "]");

        g2.drawImage(image, xPozition, yPozition, game.elementSize, game.elementSize, null);

        // g2.dispose();
    }

    void Randompozicion() {

        Random rand = new Random();
        xX = rand.nextInt(600);
        yY = rand.nextInt(600);
        System.out.println("new random pozition: [" + xX + ", " + yY + "]");
        // System.out.println(xX);
        // System.out.println(yY);
    }

    public void moveRandom() {

        int newX = xPozition;
        int newY = yPozition;
        // System.out.println(xX);
        // System.out.println(yY);

        if (xPozition > xX - speed && xPozition < xX + speed) {
            Randompozicion();
        }
        if (yPozition > yY - speed && yPozition < yY + speed) {
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

    void hitWall(int previousX, int previousY) {

        for (wall wall : game.walls) {
            for (int[] pozicion : wall.wallPoints) {
                int useX = pozicion[0] * game.elementSize;
                int usey = pozicion[1] * game.elementSize;
                if (xPozition >= useX-game.elementSize && xPozition <= useX +game.elementSize && yPozition >= usey-game.elementSize&& yPozition <= usey+game.elementSize) {
                    xPozition = previousX;
                    yPozition = previousY;
                    System.out.println(" colizion at: [" + useX + ", " + usey + "]");
                    // System.exit(0);
                }

            }
        }
    }

    boolean waitTime (float seconds){
        int frames = (secondsCounter * game.FPS) + game.frame;
        if ( frames == (int)(seconds*game.FPS)){
            secondsCounter = 0;
            return true;
        }
        else{
            if (game.frame == 0){
                secondsCounter +=1; 
            }
            return false;
        }
    }
}
