package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.game;

public class element {
    public int xPozition;
    public int yPozition;
    public String type;
    public Image image;
    int size;
    int number;
    public boolean isInInventory;
    boolean isColectable;
    public game game;
    int lives;
    int strange;

    public element(int xPozition, int yPozition, String type, boolean isColectable, game game) {
        this.xPozition = xPozition;
        this.yPozition = yPozition;
        this.type = type;
        this.isColectable = isColectable;
        this.isInInventory = false;
        this.game = game;
        openElemtImage(type);
        // this.game.gamInventory.addEelentToInventory(this);
        lives = 0;
        strange = 0;
    }

    public element(int xPozition, int yPozition, String type, int lives, int strange, boolean isColectable, game game) {
        this.xPozition = xPozition;
        this.yPozition = yPozition;
        this.type = type;
        this.isColectable = isColectable;
        this.isInInventory = false;
        this.game = game;
        openElemtImage(type);
        // this.game.gamInventory.addEelentToInventory(this);
        this.lives = lives;
        this.strange = strange;
    }

    public element(String type, game game) {
        this.xPozition = 0;
        this.yPozition = 0;
        this.type = type;
        this.isColectable = true;
        this.isInInventory = true;
        this.game = game;
        openElemtImage(type);
        this.game.player.inventory.addEelentToInventory(this);
        lives = 0;
        strange = 0;
    }

    public element(String type, int lives, int strange, game game) {
        this.xPozition = 0;
        this.yPozition = 0;
        this.type = type;
        this.isColectable = true;
        this.isInInventory = true;
        this.game = game;
        openElemtImage(type);
        this.game.player.inventory.addEelentToInventory(this);
        this.lives = lives;
        this.strange = strange;
    }

    public void drawElemnt(Graphics2D g2) {
        int x = xPozition * game.elementSize;
        int y = yPozition * game.elementSize;
        drawElemnt(x, y, g2);
    }

    public void drawElemnt(int xPozition2, int yPozition2, Graphics2D g2) {
        g2.drawImage(image, xPozition2, yPozition2, game.elementSize, game.elementSize, null);
    }

    public void openElemtImage(String name) {
        String fullname = "/img/" + name + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.print("successful image uploaded of ");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean elementIsInRange(player playere) {
        int useX = xPozition * game.elementSize;
        int useY = yPozition * game.elementSize;
        int range = ((game.elementSize * playere.range) / 2) - (game.elementSize / 2);
        if (playere.xPozition - range <= useX + game.elementSize && playere.xPozition + range >= useX - game.elementSize
                && playere.yPozition - range <= useY + game.elementSize
                && playere.yPozition + range >= useY - game.elementSize) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isColectable(player playere) {
        if (elementIsInRange(playere) && isInInventory == false) {
            // printColect();
            return true;
        }
        return false;
    }

    public void printColect(player playere, Graphics2D g2) {
        if (isColectable(playere)) {
            g2.drawString("Press I", xPozition * game.elementSize, yPozition * game.elementSize);
        }
    }

    public void toInventory(element element) {
        this.xPozition = 0;
        this.yPozition = 0;
        this.type = element.type;
        this.isColectable = false;
        this.isInInventory = true;
        this.game = element.game;
        this.image = element.image;
    }

}
