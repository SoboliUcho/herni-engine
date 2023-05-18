package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class inventory {
    element[] inventory = new element[5];
    static Image inventoryFrame;
    player player;

    public inventory(player player) {
        for (element i : inventory) {
            i = null;
        }
        this.player = player;
        openImageInventory();
    }

    private void openImageInventory() {
        String fullname = "/img/inventoryFrame.png";
        try {
            inventoryFrame = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.print("successful image uploaded of ");
            System.out.println("inventoryFrame");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addEelentToInventory(element element) {
        for (element i : inventory) {
            if (i == null) {
                i = element;
                break;
            }
        }
    }

    public void drawInventory(Graphics2D g2) {
        for (int i = 0; i < inventory.length; i++) {
            int imageSize = player.game.elementSize * 2;
            g2.drawImage(inventoryFrame, imageSize * i, player.game.yElements * (imageSize / 2), imageSize, imageSize,null);
        }
        drawItems(g2);
    }

    void drawItems(Graphics2D g2) {

    }
}
