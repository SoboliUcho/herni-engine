package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

// import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.enemy;
import entity.player;

public class game extends JPanel {//implements Runnable {
    public int xElements;
    public int yElements;
    public int elementSize;
    public window window;

    player player;
    enemy[] enemies;

    public game() {
        this.xElements = 10;
        this.yElements = 10;
        this.elementSize = 50;
        addPlayer();
        // Thread thread = new Thread(this);
        // thread.start();
    }

    void addPlayer(entity.player player) {
        this.player = player;
        player.game = this;
    }

    void addPlayer() {
        player = new player(4, 4);
        player.game = this;
    }

    void addWindow(window window) {
        this.window = window;
        makeWindow();
    }

    void makeWindow(){
        // System.out.println(window);
        window.makeWindow("game", xElements, xElements, elementSize);
    }
    // @Override
    // public void run() {
    //     repaint();
    // }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // drawLines(g);
        player.draw(g2);
    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(120, 50, 360, 50);

        g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));

        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

    }
    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     g.setColor(Color.RED);
    //     g.fillRect(50, 50, 100, 100);
    //   }
}
