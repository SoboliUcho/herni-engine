package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.endPoint;
import game.game;

public class inventory {
    public element[] inventory;
    static Image inventoryFrame;
    player player;
    game game;

    public inventory(player player) {
        inventory = new element[5];
        for (element i : inventory) {
            i = null;
        }
        this.player = player;
        this.game = player.game;
        openImageInventory();
    }

    public inventory(game game, int numebrOfElements) {
        inventory = new element[numebrOfElements];
        for (element i : inventory) {
            i = null;
        }
        this.game = game;
        this.player = game.player;
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

    public void addEelentToInventory(element element) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = element;
                // inventory[i].isInInventory = true;
                System.out.println(inventory[i].type + " was ad to inventory");
                print();
                break;
            }
        }
    }

    public void print() {
        for (element element : inventory) {
            System.out.println(element);
        }
    }

    public void drawInventory(Graphics2D g2) {
        for (int i = 0; i < inventory.length; i++) {
            int imageSize = player.game.elementSize * 2;
            int xPozition = imageSize * i;
            int yPozition = player.game.yElements * (imageSize / 2);
            g2.drawImage(inventoryFrame, xPozition, yPozition, imageSize, imageSize, null);

            if (inventory[i] != null && inventory[i].isInInventory == true) {
                inventory[i].drawElemnt(xPozition + player.game.elementSize / 2,
                        yPozition + player.game.elementSize / 2, g2);
            }
        }
    }

    public void drawItems(Graphics2D g2) {
        if (inventory == null){
            return;
        }
        for (element element : inventory) {
            if (element != null) {
                element.drawElemnt(g2);
                // System.out.println(element.isColectable(player));
                element.printColect(player, g2);
            }
        }
    }

    int inventoryLenght() {
        return inventory.length;
    }

    public void update() {
        for (element element : inventory) {
            if (element instanceof endPoint) {
                ((endPoint) element).inEndPoint();
            }
        }
    }

}
