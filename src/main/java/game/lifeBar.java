package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.player;

public class lifeBar {
    player player;
    game game;
    public Image life;

    public lifeBar(entity.player player) {
        this.player = player;
        game = this.player.game;
        openImageLifeBar();
    }

    private void openImageLifeBar() {
        String fullname = "/img/heart.png";
        try {
            life = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.print("successful image uploaded of ");
            System.out.println("Life");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void drawLifeBar(Graphics2D g2) {
        for (int i = 0; i < player.lives; i++) {
            int imageSize = game.elementSize * 2 - 5;
            int yPozition = ((game.xElements - 2) * game.elementSize) - game.elementSize * i * 2;
            int xPozition = game.xElements * game.elementSize + 2;
            g2.drawImage(life, xPozition, yPozition, imageSize, imageSize, null);
        }
    }
}