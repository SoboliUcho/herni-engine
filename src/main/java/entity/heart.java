package entity;

public class heart extends element{

    public heart(int xPozition, int yPozition, int lives, boolean isColectable, game.game game) {
        super(xPozition, yPozition, "heart", isColectable, game);
        this.lives = lives;
        // this.game.gamInventory.addEelentToInventory(this);
    }

    public void toInventory(heart element){
        game.player.addLives(element.lives);
    }
    
}
