package entity;

import java.awt.Graphics2D;
import java.util.Random;

import game.game;

/**
 * The enemy class represents an enemy entity in the game.
 */
public class enemy extends entita {
    int eyeshot;

    /**
     * Constructs an enemy object at the relative position with the given
     * attributes and game.
     *
     * @param x       the x-coordinate of the enemy's position
     * @param y       the y-coordinate of the enemy's position
     * @param lives   the number of lives of the enemy
     * @param strange the strength of the enemy
     * @param range   the attack range of the enemy
     * @param game    the game object
     */
    public enemy(int x, int y, int lives, int strange, int range, game game) {
        super(x, y, game);
        this.strange = strange;
        this.range = range;
        speed = 1;
        eyeshot = 5;
        this.type = "enemy";
        this.lives = lives;
        openImage(type);
    }

    /**
     * Constructs an enemy object at the absolute position with the given
     * attributes, game, and difference value.
     *
     * @param x       the x-coordinate of the enemy's position
     * @param y       the y-coordinate of the enemy's position
     * @param lives   the number of lives of the enemy
     * @param strange the strength of the enemy
     * @param range   the attack range of the enemy
     * @param game    the game object
     * @param difernt the difference value (unused)
     */
    public enemy(int x, int y, int lives, int strange, int range, game game, int difernt) {
        super(x, y, game);
        xPozition = x;
        yPozition = y;
        this.strange = strange;
        this.range = range;
        speed = 1;
        eyeshot = 5;
        this.type = "enemy";
        this.lives = lives;
        openImage(type);
    }

    /**
     * Performs the attack action by damaging the player if they are within range.
     */
    public void atack() {
        // System.out.println(entitaIsInRange(game.player));
        if (entitaIsInRange(game.player) && makeAction && isLive()) {
            makeDamage(game.player);
            makeAction = false;
            atack = true;
        }

    }

    /**
     * Displays the attack action image for the enemy.
     *
     * @param g2 the Graphics2D object
     */
    public void atackActionEnemy(Graphics2D g2) {
        if (atack) {
            atackAction(g2);
            if (!animationCoolDown()) {
                atack = false;
            }
        }
    }

    /**
     * Moves the enemy towards the player.
     */
    public void moveEntityToPlayer() {
        Random rand = new Random();
        int newX = xPozition;
        int newY = yPozition;
        int previousX = xPozition;
        int previousY = yPozition;
        makeAction = coolDown();
        // System.out.println(makeAction);
        if (!playerIsVisible() || !isLive()) {
            return;
        }
        if (xPozition >= game.player.xPozition - game.elementSize
                && xPozition <= game.player.xPozition + game.elementSize
                && yPozition >= game.player.yPozition - game.elementSize
                && yPozition <= game.player.yPozition + game.elementSize) {
            return;
        }
        if (game.player.xPozition + game.elementSize < xPozition) {
            newX = xPozition - speed + rand.nextInt(2) - 1;
        }
        if (game.player.xPozition + game.elementSize > xPozition) {
            newX = xPozition + speed + rand.nextInt(2) - 1;
        }
        if (game.player.yPozition + game.elementSize < yPozition) {
            newY = yPozition - speed + rand.nextInt(2) - 1;
        }
        if (game.player.yPozition + game.elementSize > yPozition) {
            newY = yPozition + speed + rand.nextInt(2) - 1;
        }
        xPozition = newX;
        yPozition = newY;
        hitWall(previousX, previousY);
    }

    /**
     * Checks if the player is within the enemy's line of sight.
     *
     * @return true if the player is visible, false otherwise
     */
    public boolean playerIsVisible() {
        int range = eyeshot * game.elementSize;

        if (game.player.xPozition < xPozition + range) {
            if (game.player.xPozition > xPozition - range) {
                if (game.player.yPozition < yPozition + range) {
                    if (game.player.yPozition > yPozition - range) {
                        return true;
                    }
                }
            }

        }

        return false;
    }
}
