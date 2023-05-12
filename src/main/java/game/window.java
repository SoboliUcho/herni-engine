package game;

import javax.swing.JFrame;

public class window {
    int elementSize;
    int xElements;
    int yElements;
    int xSize;
    int ySize;

    String name;
    JFrame frame;
  

    public window(String name) {
        this.name = name;
        this.elementSize = 10;
        this.xElements = 50;
        this.yElements = 50;
        this.xSize = elementSize * xElements;
        this.ySize = elementSize * yElements;

        makeWindow();
    }
    void makeWindow(){
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize,ySize);
        frame.setVisible(true);
    }
}
