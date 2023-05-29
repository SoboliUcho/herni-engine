package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.game;
import game.logger;

/**
 * The element class represents an element in the game.
 */
public class element {
    public int xPozition;
    public int yPozition;
    public String type;
    public Image image;
    int size;
    int number;
    public boolean isInInventory;
    public boolean isColectable;
    public game game;
    int lives;
    int strange;

    /**
     * Constructs an element object at the specified position with the given type
     * and collectible status.
     *
     * @param xPozition    the x-coordinate of the element's position
     * @param yPozition    the y-coordinate of the element's position
     * @param type         the type of the element
     * @param isColectable true if the element is collectible, false otherwise
     * @param game         the game object
     */
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

    /**
     * Constructs an element object at the specified position with the given type,
     * lives, strength, and collectible status.
     *
     * @param xPozition    the x-coordinate of the element's position
     * @param yPozition    the y-coordinate of the element's position
     * @param type         the type of the element
     * @param lives        the number of lives of the element
     * @param strange      the strength of the element
     * @param isColectable true if the element is collectible, false otherwise
     * @param game         the game object
     */
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

    /**
     * Constructs an element object of the specified type and adds it to the
     * player's inventory.
     *
     * @param type the type of the element
     * @param game the game object
     */
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

    /**
     * Constructs an element object of the specified type with the given lives,
     * strength, and adds it to the player's inventory.
     *
     * @param type    the type of the element
     * @param lives   the number of lives of the element
     * @param strange the strength of the element
     * @param game    the game object
     */
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

    /**
     * Draws the element on the graphics context.
     *
     * @param g2 the graphics context
     */
    public void drawElemnt(Graphics2D g2) {
        int x = xPozition * game.elementSize;
        int y = yPozition * game.elementSize;
        drawElemnt(x, y, g2);
    }

    /**
     * Draws the element on the graphics context at the specified absolute position.
     *
     * @param xPozition2 the x-coordinate of the position
     * @param yPozition2 the y-coordinate of the position
     * @param g2         the graphics context
     */
    public void drawElemnt(int xPozition2, int yPozition2, Graphics2D g2) {
        logger.logFiner(this.type + "was draw at " + xPozition + yPozition);
        g2.drawImage(image, xPozition2, yPozition2, game.elementSize, game.elementSize, null);
    }

    /**
     * Opens the image file for the element with the specified name.
     *
     * @param name the name of the element
     */
    public void openElemtImage(String name) {
        String fullname = "/img/" + name + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fullname));
            logger.logInfo("successful image uploaded of ");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the element is within the range of the player.
     *
     * @param playere the player object
     * @return true if the element is in range, false otherwise
     */
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

    /**
     * Checks if the element is collectible by the player.
     *
     * @param playere the player object
     * @return true if the element is collectible, false otherwise
     */
    public boolean isColectable(player playere) {
        if (elementIsInRange(playere) && isInInventory == false) {
            return true;
        }
        return false;
    }

    /**
     * Prints the "Press I" message to collect the element on the graphics context.
     *
     * @param playere the player object
     * @param g2      the graphics context
     */
    public void printColect(player playere, Graphics2D g2) {
        if (isColectable(playere)) {
            g2.drawString("Press I", xPozition * game.elementSize, yPozition * game.elementSize);
        }
    }

    /**
     * Moves the element to the player's inventory.
     *
     * @param element the element to be moved
     */
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
