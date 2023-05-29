package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.game;

/**
 * The Wall class represents a wall in the game. It defines the position,
 * length, and direction of the wall.
 */
public class wall {
    public int xStart;
    public int yStart;
    public int lenght;
    public int direction;
    public int[][] wallPoints;

    game game;
    static Image wall = null;

    /**
     * Creates a new Wall object with the specified parameters.
     *
     * @param xStart    the starting x-coordinate of the wall
     * @param yStart    the starting y-coordinate of the wall
     * @param length    the length of the wall
     * @param direction the direction of the wall (1 for horizontal, 2 for vertical,
     *                  3 and 4 diagonaly, - change direction)
     * @param game      the game instance
     */
    public wall(int xStart, int yStart, int lenght, int direction, game game) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.lenght = lenght;
        this.direction = direction;
        this.game = game;
        this.wallPoints = new int[lenght][2];
        openWallImage();
        palceWall();
    }

    /**
     * Opens the image file of wall.
     */
    public void openWallImage() {
        if (wall != null) {
            return;
        }
        String fullname = "/img/wall.png";
        try {
            wall = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.println("successful image uploaded of Wall");
            // System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * calculate all realtive points wher is wall
     */
    public void palceWall() {
        if (Math.abs(direction) == 1) {
            for (int i = 0; i < lenght; i++) {
                wallPoints[i][0] = xStart + (i * direction);
                wallPoints[i][1] = yStart;
            }
        }
        if (Math.abs(direction) == 2) {
            for (int i = 0; i < lenght; i++) {
                wallPoints[i][0] = xStart;
                wallPoints[i][1] = yStart + (i * direction / 2);
            }
        }
        if (Math.abs(direction) == 3) {
            for (int i = 0; i < lenght; i++) {
                wallPoints[i][0] = xStart - (i * direction / 3);
                wallPoints[i][1] = yStart + (i * direction / 3);
            }
        }
        if (Math.abs(direction) == 4) {
            for (int i = 0; i < lenght; i++) {
                wallPoints[i][0] = xStart - (i * direction / 4);
                wallPoints[i][1] = yStart + (i * direction / 4);
            }
        }
    }

    /**
     * Paints the wall on the graphics context.
     *
     * @param g2 the graphics context
     */
    public void paintWall(Graphics2D g2) {
        for (int[] i : wallPoints) {
            int x = i[0] * game.elementSize;
            int y = i[1] * game.elementSize;
            g2.drawImage(wall, x, y, game.elementSize, game.elementSize, null);
        }
        // System.out.println("wall: [" + x + " " + y + "]");

    }

}
