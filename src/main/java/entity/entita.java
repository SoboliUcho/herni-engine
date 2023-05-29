package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.game;

/**
 * The entita class represents an entity in the game.
 */
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
    boolean atack = false;

    static Image attackImage;
    static Image catchImage;

    int secondsCounter = 0;
    int timeOfattack = 2;
    public boolean makeAction = true;
    int xX;
    int yY;

    /**
     * Constructs an entity with the specified coordinates and game.
     *
     * @param x    the x-coordinate
     * @param y    the y-coordinate
     * @param game the game object
     */
    public entita(int x, int y, game game) {
        this.game = game;
        this.xPozition = x * this.game.elementSize;
        this.yPozition = y * this.game.elementSize;
        this.strange = 0;
        this.xX = xPozition;
        this.yY = yPozition;
        this.range = 2;

    }

    /**
     * Checks if the entity is alive.
     *
     * @return true if the entity is alive, false otherwise
     */
    public boolean isLive() {
        if (lives > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Draws the entity's attack action on the graphics context.
     *
     * @param g2 the graphics context
     */
    public void atackAction(Graphics2D g2) {
        if (atack && animationCoolDown()) {
            // int pozicion = game.elementSize;
            int x = xPozition - ((game.elementSize * range) / 2) + game.elementSize / 2;
            int y = yPozition - ((game.elementSize * range) / 2) + game.elementSize / 2;
            g2.drawImage(attackImage, x, y, game.elementSize * range, game.elementSize * range, null);

        }
    }

    /**
     * Inflicts damage to another entity.
     *
     * @param entita the entity to be damaged
     */
    void makeDamage(entita entita) {
        entita.lives = entita.lives - strange;
    }

    /**
     * Gets the coordinates of the entity.
     *
     * @return an array containing the x and y coordinates
     */
    public int[] entitaPozicion() {
        int[] pozicion = { xPozition, yPozition };
        return pozicion;
    }

    /**
     * Opens the attack image file.
     */
    public void openAttackImage() {
        String fullname = "/img/attack.png";
        try {
            attackImage = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.println("successful image uploaded of Attack");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the catch image file.
     */
    public void openCatchImage() {
        String fullname = "/img/catch.png";
        try {
            catchImage = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.println("successful image uploaded of Catch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the image file with the specified name.
     *
     * @param name the name of the image file
     */
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

    /**
     * Draws the entity on the graphics context.
     *
     * @param g2 the graphics context
     */
    public void draw(Graphics2D g2) {
        // System.out.println(type + " [" + xPozition + ", " + yPozition + "]");

        g2.drawImage(image, xPozition, yPozition, game.elementSize, game.elementSize, null);
    }

    /**
     * Generates random coordinates for the entity.
     */
    void Randompozicion() {

        Random rand = new Random();
        xX = rand.nextInt(600);
        yY = rand.nextInt(600);
        System.out.println("new random pozition: [" + xX + ", " + yY + "]");
        // System.out.println(xX);
        // System.out.println(yY);
    }

    /**
     * Moves the entity randomly.
     */
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

    /**
     * Checks if the entity hits a wall and adjusts its position accordingly.
     *
     * @param previousX the previous x-coordinate
     * @param previousY the previous y-coordinate
     */
    void hitWall(int previousX, int previousY) {

        for (wall wall : game.walls) {
            for (int[] pozicion : wall.wallPoints) {
                int useX = pozicion[0] * game.elementSize;
                int usey = pozicion[1] * game.elementSize;
                if (xPozition >= useX - game.elementSize && xPozition <= useX + game.elementSize
                        && yPozition >= usey - game.elementSize && yPozition <= usey + game.elementSize) {
                    xPozition = previousX;
                    yPozition = previousY;
                    // System.out.println(" colizion at: [" + useX + ", " + usey + "]");

                }

            }
        }
    }

    /**
     * Waits for the specified number of seconds.
     *
     * @param seconds the number of seconds to wait
     * @return true if the specified time has passed, false otherwise
     */
    boolean waitTime(float seconds) {
        int frames = (secondsCounter * game.FPS) + game.frame;
        if (frames == (int) (seconds * game.FPS)) {
            secondsCounter = 0;
            return true;
        } else {
            if (game.frame == 0) {
                secondsCounter += 1;
            }
            return false;
        }
    }

    /**
     * Checks if the entity's action is ready to be performed.
     *
     * @return true if the action is ready, false otherwise
     */
    boolean coolDown() {
        if (makeAction) {
            return true;
        }
        if (secondsCounter == timeOfattack) {
            secondsCounter = 0;
            return true;
        } else {
            if (game.frame == 1) {
                secondsCounter += 1;
                System.out.println(secondsCounter);
            }
            return false;
        }

    }

    /**
     * Checks if the entity's animation is ready to be performed.
     *
     * @return true if the animation is ready, false otherwise
     */
    boolean animationCoolDown() {
        if (secondsCounter == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks if another entity is within the range of this entity.
     *
     * @param entita the other entity
     * @return true if the other entity is within range, false otherwise
     */
    boolean entitaIsInRange(entita entita) {
        int rang = ((game.elementSize * range) / 2) - (game.elementSize / 2);
        int useX = xPozition;
        int useY = yPozition;

        // System.out.println(rang);
        if (useX - game.elementSize - rang <= entita.xPozition && useX + game.elementSize + rang >= entita.xPozition
                && useY - game.elementSize - rang <= entita.yPozition
                && useY + game.elementSize + rang >= entita.yPozition) {
            return true;
        } else {
            return false;
        }
    }

}
