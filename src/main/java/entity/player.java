package entity;

import game.keyboard;
import game.lifeBar;

import java.awt.Graphics2D;

import game.game;

//glpat-64qafhwcij1dKBRjjpnN

/**
 * The player class represents the player entity in the game.
 */
public class player extends entita {
    int xDefault;
    int yDefault;
    keyboard keyboard;
    public lifeBar lifeBar;
    public inventory inventory;

    /**
     * Constructs a player object at the specified position with the given game and
     * keyboard.
     *
     * @param x        the x-coordinate of the player's position
     * @param y        the y-coordinate of the player's position
     * @param game     the game object
     * @param keyboard the keyboard object
     */
    public player(int x, int y, game game, keyboard keyboard) {
        super(x, y, game);
        this.game = game;
        xPozition = x * game.elementSize;
        yPozition = y * game.elementSize;
        type = "player";
        this.keyboard = keyboard;
        inventory = new inventory(this);
        lifeBar = new lifeBar(this);
        setDefault();
        openImage(type);
        openAttackImage();
        openCatchImage();
    }

    /**
     * Displays the catch action image for the player.
     *
     * @param g2 the Graphics2D object
     */
    public void catchAction(Graphics2D g2) {
        if (game.keyboard.iIsPress && animationCoolDown()) {
            int x = xPozition - ((game.elementSize * range) / 2) + game.elementSize / 2;
            int y = yPozition - ((game.elementSize * range) / 2) + game.elementSize / 2;
            g2.drawImage(catchImage, x, y, game.elementSize * range, game.elementSize * range, null);
        }
    }

    /**
     * Sets the default attributes of the player.
     */
    public void setDefault() {
        speed = 2;
        strange = 1;
        lives = 5;
        size = 1;

        xDefault = xPozition;
        yDefault = yPozition;
    }

    /**
     * Moves the player based on the keyboard inputs.
     */
    public void movePlayer() {
        // System.out.println("run");

        int previousX = xPozition;
        int previousY = yPozition;

        if (keyboard.upIsPress) {
            yPozition = yPozition - speed;
        }
        if (keyboard.downIsPress) {
            yPozition = yPozition + speed;
        }
        if (keyboard.rightIsPress) {
            xPozition = xPozition + speed;
        }
        if (keyboard.leftIsPress) {
            xPozition = xPozition - speed;
        }
        hitWall(previousX, previousY);
        makeAction = coolDown();
        // System.out.println(makeAction);
    }

    /**
     * Performs the catch action by adding elements to the player's inventory.
     *
     * @see inventory
     */

    public void catchElements() {
        // inventory.print();
        if (makeAction == false) {
            return;
        }
        if (keyboard.iIsPress) {
            makeAction = false;
        }
        for (int i = 0; i < game.gamInventory.inventory.length; i++) {
            if (game.gamInventory.inventory[i] == null) {
                continue;
            }
            if (game.gamInventory.inventory[i].isColectable(this) && keyboard.iIsPress == true) {
                boolean full = true;
                for (int f = 0; f < inventory.inventoryLenght(); f++) {
                    if (inventory.inventory[f] == null) {
                        if (game.gamInventory.inventory[i] instanceof heart) {
                            lives = lives + game.gamInventory.inventory[i].lives;
                        } else {
                            inventory.inventory[f].toInventory(game.gamInventory.inventory[i]);
                            System.out.println(inventory.inventory[f].type + " was moovew to inventory");
                            addStrange(game.gamInventory.inventory[i].strange);
                        }
                        game.gamInventory.inventory[i] = null;
                        full = false;
                        break;
                    }
                }
                if (full) {
                    System.out.println("inventory is full");
                }
            }
        }

    }

    /**
     * Adds lives to the player.
     *
     * @param quantity the quantity of lives to add
     */
    public void addLives(int quantity) {
        this.lives += quantity;
    }

    /**
     * Adds strength to the player.
     *
     * @param quantity the quantity of strength to add
     */
    public void addStrange(int quantity) {
        this.strange = quantity;
    }

    /**
     * Performs the attack action by damaging enemies within range.
     */
    public void atack() {
        for (enemy Enemy : game.enemies) {
            if (keyboard.spaceIsPress && makeAction) {
                if (entitaIsInRange(Enemy)) {
                    makeDamage(Enemy);
                }
                makeAction = false;
                atack = true;
                System.out.println("makeAtack");
            }
        }
    }

    /**
     * Displays the attack action image for the player.
     *
     * @param g2 the Graphics2D object
     */
    public void atackActionPlayer(Graphics2D g2) {
        if (game.keyboard.spaceIsPress) {
            atackAction(g2);
            if (!animationCoolDown()) {
                atack = false;
            }
        }
    }
}
