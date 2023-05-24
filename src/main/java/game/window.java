package game;

import java.awt.Dimension;
import javax.swing.JFrame;

public class window {
    int xSize;
    int ySize;
    int elementSize;
    keyboard keyboard;

    String name;
    game curentGame;
    JFrame frame;

    public window() {
        curentGame = new game();
        curentGame.window = this;
        curentGame.makeWindow();
    }

    void makeWindow(String name, int xElements, int yElements, int elementSize, keyboard keyboard) {
        this.name = name;
        this.elementSize = elementSize;
        this.xSize = elementSize * xElements +2* curentGame.elementSize;
        this.ySize = elementSize * yElements + 2* curentGame.elementSize;
        this.keyboard = keyboard;
        openWindow();

        System.out.println("window size: x " + xSize + " y " + ySize + " was made");

    }

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
        curentGame.thread.start();

    }

    // void PrintEntita( Graphics2D g2, entita entita) {
    // int[] pozicion = entita.entitaPozicion();

    // pozicion[0] = pozicion[0] * elementSize;
    // pozicion[1] = pozicion[1] * elementSize;

    // }

    // @Override
    // protected void paintComponent ( Graphics g ) {
    // super.paintComponent ( g );
    // }

}
