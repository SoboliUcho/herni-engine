package game;

import java.awt.Graphics2D;

import entity.element;
import entity.player;

public class endPoint extends element {
    String [] keys;

    public endPoint(int xPozition, int yPozition, String [] keys, game game) {
        super(xPozition, yPozition, "end", false, game);
        this.keys = keys;
        this.game.gamInventory.addEelentToInventory(this);
    }

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

    public boolean haveKeys(){
        int goodKeys = 0;
        if (keys == null){
            return true;
        }
        for (element key : game.player.inventory.inventory) {
           for (String name : keys) {
                if (key.type == name){
                    goodKeys += 1;
                }
           }
        }
        if (goodKeys == keys.length){
            return true;
        }

        return false;
    }

    public void needKeys(Graphics2D g2){

        g2.drawString("Press I", xPozition * game.elementSize, yPozition * game.elementSize);
    }
    @Override
    public void printColect(player playere, Graphics2D g2) {
        if (inEndPoint()) {
            g2.drawString("Press F", xPozition * game.elementSize, yPozition * game.elementSize);
        }
    }
}
