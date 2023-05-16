package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import entity.enemy;
import entity.player;

public class game extends JPanel implements Runnable {
    public int xElements;
    public int yElements;
    public int elementSize;

    public int FPS = 60;
    long timePerFrame;
    long startTime;
    long curentTime;
    int frame = 0;

    int levelNumber;
    public level level;
    public keyboard keyboard;
    public window window;
    public Thread thread;

    public player player;
    enemy[] enemies;

    public game() {
        this.xElements = 30;
        this.yElements = 30;
        this.elementSize = 20;
        this.timePerFrame = 1_000_000_000 / FPS;

        keyboard = new keyboard();
        level = new level(levelNumber, this);
        level.loadLevel();
        // addPlayer();
        // thread = new Thread(this);
    }

    void addPlayer(entity.player player) {
        this.player = player;
        player.game = this;
    }

    public void addEnemys(enemy[] enemy) {
        this.enemies = enemy;
    }

    public void addPlayer(int x, int y) {
        player = new player(x, y, this, keyboard);
        player.game = this;
    }

    void addWindow(window window) {
        this.window = window;
        makeWindow();
    }

    void makeWindow() {
        // System.out.println(window);
        window.makeWindow("game", xElements, xElements, elementSize, keyboard);
    }

    void frameCount(long timethread) {
        curentTime = System.nanoTime();
        if (frame == 30) {
            frame = 1;
            timethread = System.currentTimeMillis() - timethread;
            // System.out.println(timethread);
        } else {
            frame = frame + 1;
        }
        // System.out.println(frame);
    }

    void threadSleepTime() {
        long sleepTime = timePerFrame - (curentTime - startTime);
        if (sleepTime > 0) {
            // System.out.println("sleep " + sleepTime);
            try {
                Thread.sleep(sleepTime / 1000000, (int) (sleepTime % 1000000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // drawLines(g);
        for (enemy enemy : enemies) {
            enemy.draw(g2);
            // System.out.println(enemy.playerIsVisible());
        }
        player.draw(g2);
        

    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(120, 50, 360, 50);

        g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));

        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

    }

    void updatePozicion() {
        for (enemy enemy : enemies) {
            // System.out.println(enemy.entitaPozicion()[0] +", " + enemy.entitaPozicion()[1]);
            enemy.moveEntityToPlayer();
        }
        player.moveRandom();
    }

    @Override
    public void run() {
        // level.loadLevel();
        while (true) {
            // while (frame < 20) {
            long timethread = System.currentTimeMillis();
            // System.out.println(timethread);

            startTime = System.nanoTime();
            // keyboard.print();
            updatePozicion();
            repaint();

            frameCount(timethread);
            curentTime = System.nanoTime();
            threadSleepTime();

        }
    }
}
