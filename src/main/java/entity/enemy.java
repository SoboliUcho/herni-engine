package entity;

import java.awt.Graphics2D;
import java.util.Random;

import game.game;

public class enemy extends entita {
    int eyeshot;

    public enemy(int x, int y, int lives, game game) {
        super(x, y, game);
        speed = 1;
        eyeshot = 5;
        this.type = "enemy";
        this.lives = lives;
        openImage(type);
    }

    public void atack() {
        if (playerIsInRange(game.player) && makeAction){
            makeDamage(game.player);
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

    boolean playerIsInRange(player playere) {
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

    public void moveEntityToPlayer() {
        Random rand = new Random();
        int newX = xPozition;
        int newY = yPozition;
        int previousX = xPozition;
        int previousY = yPozition;
        if (!playerIsVisible()) {
            return;
        }
        // if (xPozition == game.player.xPozition && yPozition == game.player.yPozition) {
        //     return;
        // }
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
