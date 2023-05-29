package game;

import java.awt.Graphics2D;

import entity.element;
import entity.player;

/**
 * The endPoint class represents the endpoint in the game where the player needs
 * to reach.
 * It extends the element class.
 */
public class endPoint extends element {
    String[] keys;

    /**
     * Creates a new endPoint object with the specified parameters.
     *
     * @param xPosition the x-coordinate of the endpoint
     * @param yPosition the y-coordinate of the endpoint
     * @param keys      the keys - name of elements in inventory - required to
     *                  unlock the endpoint
     * @param game      the game instance
     */
    public endPoint(int xPozition, int yPozition, String[] keys, game game) {
        super(xPozition, yPozition, "end", false, game);
        this.keys = keys;
        this.game.endPoint = this;
        // System.out.println(this.game);
    }

    /**
     * Checks if the player is in the endpoint.
     *
     * @return true if the player is in the endpoint, false otherwise
     */
    public boolean inEndPoint() {
        int useX = xPozition * game.elementSize;
        int useY = yPozition * game.elementSize;
        if (game.player.xPozition <= useX + game.elementSize && game.player.xPozition >= useX - game.elementSize
                && game.player.yPozition <= useY + game.elementSize
                && game.player.yPozition >= useY - game.elementSize) {
            System.out.println("v cíly");
            return true;
        }
        return false;
    }

    /**
     * Checks if the player has the required keys to unlock the endpoint.
     *
     * @return true if the player has the required keys, false otherwise
     */
    public boolean haveKeys() {
        int goodKeys = 0;
        if (keys == null) {
            return true;
        }
        for (element key : game.player.inventory.inventory) {
            for (String name : keys) {
                if (key.type == name) {
                    goodKeys += 1;
                }
            }
        }
        if (goodKeys == keys.length) {
            return true;
        }

        return false;
    }

    /**
     * Displays the message indicating that the player needs keys to unlock the
     * endpoint.
     *
     * @param g2 the graphics context
     */
    public void needKeys(Graphics2D g2) {

        g2.drawString("Need Keys", xPozition * game.elementSize, yPozition * game.elementSize);
    }

    @Override
    public void printColect(player playere, Graphics2D g2) {
        if (inEndPoint() && haveKeys()) {
            g2.drawString("Press F", xPozition * game.elementSize, yPozition * game.elementSize);
        } else {
            needKeys(g2);
        }
    }
}
