package entity;
/**
 * The Heart class represents a heart element in the game.
 * Hearts can be collected by the player to increase their lives.
 */
public class heart extends element{
    /**
     * Creates a new Heart object.
     *
     * @param xPozition     the x-coordinate of the heart
     * @param yPozition     the y-coordinate of the heart
     * @param lives         the number of lives the heart provides
     * @param isCollectable true if the heart can be collected, false otherwise
     * @param game          the game instance
     */
    public heart(int xPozition, int yPozition, int lives, boolean isColectable, game.game game) {
        super(xPozition, yPozition, "heart", isColectable, game);
        this.lives = lives;
        // this.game.gamInventory.addEelentToInventory(this);
    }
    /**
     * Adds the heart element to the player's liveCount.
     *
     * @param element the heart element to add to the inventory
     */
    public void toInventory(heart element){
        game.player.addLives(element.lives);
    }
    
}
