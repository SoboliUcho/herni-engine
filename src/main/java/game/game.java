package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
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
    int esc = 1;

    int levelNumber = 1;
    public level level;
    public keyboard keyboard;
    public window window;
    // mainScreen mainScreen;
    menu menu;

    public Thread gameThread;
    public Thread menuThread;
    public Thread levelThread;
    public Thread keyboardThread;

    public player player;
    public enemy[] enemies;
    public wall[] walls;
    public inventory gamInventory;
    public endPoint endPoint;
    JButton[] buttons;
    // wall[] sideWalls;

    boolean escWasPress = true;
    boolean buttonWasAdd = false;

    public game() {
        this.xElements = 30;
        this.yElements = 30;
        this.elementSize = 20;
        this.timePerFrame = 1_000_000_000 / FPS;
        keyboard = new keyboard();
        menu = new menu(this);
        gameThread = new Thread(this);
        menuThread = new Thread(menu);
        level = new level(levelNumber, this);

    }

    public void addEndPoint(endPoint endPoint) {
        this.endPoint = endPoint;
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
        window.makeWindow("game", xElements, xElements, elementSize, keyboard);
        menuThread.start();
        gameThread.start();

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

    void escStatus() {
        // System.out.println(escWasPress);
        if (keyboard.escIsPress) {
            if (esc == 10) {
                if (escWasPress) {
                    escWasPress = false;
                    buttonWasAdd = false;
                    esc = 1;

                } else {
                    escWasPress = true;
                    buttonWasAdd = false;
                    esc = 1;
                }

                return;
            }
        }
        if (esc == 10) {
            return;
        }
        esc += 1;
        return;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // System.out.println(escWasPress);
        // buttonsVisible();
            gamInventory.drawItems(g2);
            endPoint.drawElemnt(g2);

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
            // // gamInventory.print();
            // mainScreen.openMainScreen(g2);

            // addButons();
            // repaint();
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
        // System.out.println("run");
        // player.moveRandom();
    }

    void loadingLevel() {
        if (levelNumber != level.levelNumber) {
            enemies = null;
            walls = null;
            // player = null;
            level = new level(levelNumber, this);
            try {
                levelThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            loadingLevel();
            while (!endPoint.inEndPoint() && player.isLive()) {
                // while (frame < 1) {
                long timethread = System.currentTimeMillis();
                startTime = System.nanoTime();

                    updatePozicion();
                    repaint();

                frameCount(timethread);
                if (endPoint.inEndPoint()) {
                    levelNumber += 1;
                }
                level.saveProgress();
                if (levelNumber != level.levelNumber) {
                    loadingLevel();
                }
                escStatus();
                curentTime = System.nanoTime();
                threadSleepTime();
            }

        }

    }
}
