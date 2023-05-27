package entity;

import java.awt.Graphics2D;
import java.util.Random;

import game.game;

public class enemy extends entita {
    int eyeshot;

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

    public enemy(int x, int y, int lives, int strange, int range, game game , int difernt) {
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

    public void atack() {
        // System.out.println(entitaIsInRange(game.player));
        if (entitaIsInRange(game.player) && makeAction && isLive()){
            makeDamage(game.player);
            makeAction = false;
            atack = true;
        }
        
    }

    public void atackActionEnemy(Graphics2D g2){
        if (atack){
            atackAction(g2);
            if (!animationCoolDown()){
                atack = false;
            }
        }
    }

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
        if (xPozition >= game.player.xPozition-game.elementSize && xPozition <= game.player.xPozition +game.elementSize && yPozition >= game.player.yPozition-game.elementSize&& yPozition <= game.player.yPozition+game.elementSize) {
            return;
        }
        if (game.player.xPozition + game.elementSize< xPozition) {
            newX = xPozition - speed + rand.nextInt(2)-1;
        }
        if (game.player.xPozition + game.elementSize> xPozition) {
            newX = xPozition + speed + rand.nextInt(2)-1;
        }
        if (game.player.yPozition + game.elementSize< yPozition) {
            newY = yPozition - speed + rand.nextInt(2)-1;
        }
        if (game.player.yPozition +game.elementSize > yPozition) {
            newY = yPozition + speed + rand.nextInt(2)-1;
        }
        xPozition = newX;
        yPozition = newY;
        hitWall(previousX, previousY);
    }

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
