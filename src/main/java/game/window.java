package game;

import java.awt.Graphics2D;

import javax.swing.JFrame;

import entity.entita;

public class window {
    int xSize;
    int ySize;
    int elementSize;

    String name;
    JFrame frame;

    public window(String name, int yElements, int xElements,int elementSize) {
        this.name = name;
        this.elementSize = elementSize;
        this.xSize = elementSize * xElements;
        this.ySize = elementSize * yElements;

        makeWindow();
    }

    void makeWindow() {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize, ySize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    void PrintEntita( Graphics2D g2, entita entita) {
        int[] pozicion = entita.entitaPozicion();

        pozicion[0] = pozicion[0] * elementSize;
        pozicion[1] = pozicion[1] * elementSize;

    }

    // @Override
    // protected void paintComponent ( Graphics g ) {
    //     super.paintComponent ( g );
    // }

}
