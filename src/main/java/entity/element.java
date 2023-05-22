package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.game;

public class element {
    int xPozition;
    int yPozition;
    String type;
    public Image image;
    int size;
    int number;
    public boolean isInInventory;
    boolean isColectable;
    game game;

    public element(int xPozition, int yPozition, String type, boolean isColectable, game game) {
        this.xPozition = xPozition;
        this.yPozition = yPozition;
        this.type = type;
        this.isColectable = isColectable;
        this.isInInventory = false;
        this.game = game;
        openElemtImage(type);
        this.game.gamInventory.addEelentToInventory(this);
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
    }

    public void drawElemnt(Graphics2D g2) {
        int x = xPozition*game.elementSize;
        int y = yPozition*game.elementSize;
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
        int usey = yPozition * game.elementSize;
        if (playere.xPozition >= useX - game.elementSize && playere.xPozition <= useX + game.elementSize  && playere.yPozition >= usey - game.elementSize && playere.yPozition <= usey + game.elementSize) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isColectable(player playere){
        if (elementIsInRange(playere) && !isInInventory){
            // printColect();
            return true;
        }
        return false;
    }

    public void printColect(Graphics2D g2) {
        if (isColectable){

        }
    }
}
