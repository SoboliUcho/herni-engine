package game;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The window class represents the game window where the game is displayed.
 */
public class window {
    int xSize;
    int ySize;
    int elementSize;
    keyboard keyboard;

    String name;
    game curentGame;
    JFrame frame;

    /**
     * Constructs a new window and initializes the game.
     */

    public window() {
        curentGame = new game();
        curentGame.window = this;
        curentGame.makeWindow();
    }

    /**
     * Creates and displays the game window.
     * 
     * @param name        the name of the window
     * @param xElements   the number of elements in the x-axis
     * @param yElements   the number of elements in the y-axis
     * @param elementSize the size of each element
     * @param keyboard    the keyboard object for input handling
     */

    void makeWindow(String name, int xElements, int yElements, int elementSize, keyboard keyboard) {
        this.name = name;
        this.elementSize = elementSize;
        this.xSize = elementSize * xElements + 2 * curentGame.elementSize;
        this.ySize = elementSize * yElements + 2 * curentGame.elementSize;
        this.keyboard = keyboard;
        openWindow();

        System.out.println("window size: x " + xSize + " y " + ySize + " was made");

    }

    /**
     * Opens and displays the game window.
     */
    void openWindow() {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(xSize, ySize));
        frame.add(curentGame);
        frame.addKeyListener(keyboard);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
