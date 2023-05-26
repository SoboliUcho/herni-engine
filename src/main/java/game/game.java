package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.enemy;
import entity.inventory;
import entity.player;
import entity.wall;

public class game extends JPanel implements Runnable {
    public int xElements;
    public int yElements;
    public int elementSize;

    public int FPS = 60;
    long timePerFrame;
    long startTime;
    long curentTime;
    public int frame = 0;

    int levelNumber;
    public level level;
    public keyboard keyboard;
    public window window;
    public mainScreen mainScreen;

    public Thread gamethread;
    public Thread menuthread;
    public Thread keayboardthread;



    public player player;
    public enemy[] enemies;
    public wall[] walls;
    public inventory gamInventory;
    public endPoint endPoint;
    // wall[] sideWalls;

    public game() {
        this.xElements = 30;
        this.yElements = 30;
        this.elementSize = 20;
        this.timePerFrame = 1_000_000_000 / FPS;
        keyboard = new keyboard();
        gamethread = new Thread(this);
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

    public void addWalls(wall[] walles) {
        if (this.walls == null) {
            this.walls = walles;
        } else {
            wall[] temporeWalls = new wall[this.walls.length + walles.length];
            for (int i = 0; i < this.walls.length; i++) {
                temporeWalls[i] = this.walls[i];
            }
            for (int i = 0; i < walles.length; i++) {
                temporeWalls[i + this.walls.length] = walles[i];
            }
            this.walls = temporeWalls;
        }

    }

    void addWindow(window window) {
        this.window = window;
        makeWindow();
    }

    void makeWindow() {
        // System.out.println(window);
        window.makeWindow("game", xElements, xElements, elementSize, keyboard);
        

        // mainScreen = new mainScreen(this);
        // // addPlayer();
        // menuthread = new Thread(mainScreen);
        
    }

    public void frameCount(long timethread) {
        curentTime = System.nanoTime();
        if (frame == FPS) {
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

        gamInventory.drawItems(g2);

        for (enemy enemy : enemies) {
            if (enemy.isLive()) {
                enemy.draw(g2);
                enemy.atackActionEnemy(g2);
            }
            // System.out.println(enemy.playerIsVisible());
        }

        for (wall wall : walls) {
            wall.paintWall(g2);
        }
        player.atackActionPlayer(g2);
        player.catchAction(g2);
        player.draw(g2);
        player.inventory.drawInventory(g2);
        player.lifeBar.drawLifeBar(g2);
        // gamInventory.print();
    }

    void updatePozicion() {
        for (enemy enemy : enemies) {
            enemy.moveEntityToPlayer();
            enemy.atack();
        }
        // gamInventory.update();
        player.movePlayer();
        player.catchElements();
        player.atack();
        // player.moveRandom();
    }

    @Override
    public void run() {
        // while (true) {
            level = new level(levelNumber, this);
            level.loadLevel();
            // while (!endPoint.inEndPoint() && player.isLive()) {
                while (frame < 1) {
                long timethread = System.currentTimeMillis();
                startTime = System.nanoTime();
                updatePozicion();
                repaint();

                frameCount(timethread);
                curentTime = System.nanoTime();
                if (endPoint.inEndPoint()){
                    levelNumber += 1;
                }
                threadSleepTime();
            }
        // }

    }
}
