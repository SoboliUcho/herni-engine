package game;

import java.awt.Dimension;
import javax.swing.JFrame;

public class window {
    int xSize;
    int ySize;
    int elementSize;

    String name;
    game nGame;
    JFrame frame;

    public window() {
        nGame = new game();
        nGame.window = this;
        nGame.makeWindow();
    }

    void makeWindow(String name, int xElements, int yElements, int elementSize) {
        this.name = name;
        this.elementSize = elementSize;
        this.xSize = elementSize * xElements;
        this.ySize = elementSize * yElements;

        openWindow();
        
        System.out.println("window size: x " + xSize + " y " + ySize);
    }

    void openWindow() {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(xSize, ySize));
        frame.add(nGame);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
