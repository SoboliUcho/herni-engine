package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.element;
import entity.enemy;
import entity.heart;
import entity.inventory;
import entity.player;
import entity.wall;

public class level implements Runnable {
    File level;
    int levelNumber;

    game game;
    ArrayList<wall> walls = new ArrayList<>();
    ArrayList<enemy> enemies = new ArrayList<>();
    ArrayList<element> items = new ArrayList<>();
    player player;

    public level(int levelNumber, game game) {
        this.levelNumber = levelNumber;
        this.game = game;

        game.addPlayer(20, 20);
        game.levelThread = new Thread(this);
        game.levelThread.start();
        // loadLevel();
    }

    void loadLevel() {
        game.gamInventory = new inventory(game, items.size());
        for (int i = 0; i < items.size(); i++) {
            game.gamInventory.addEelentToInventory(items.get(i));
        }
        enemy [] enemy  = enemies.toArray(new enemy[enemies.size()]);
        game.addEnemys(enemy);
        wall [] wallis  = walls.toArray(new wall[walls.size()]);
        game.addWalls(wallis);
        sideWall();
    }

    void analiseText(ArrayList<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String[] pieces = text.get(i).split("#");
            if (pieces[0] == "player") {
                makePlayer(pieces);
            }
            if (pieces[0] == "enemy") {
                makeEnemy(pieces);
            }
            if (pieces[0] == "live") {
                makeLive(pieces);
            }
            if (pieces[0] == "element") {
                makeElement(pieces);
            }
            if (pieces[0] == "wall") {
                makeWall(pieces);
            }
            if (pieces[0] == "endPoint") {
                String[] keys = pieces[3].split(";");
                endPoint endPoint = new endPoint(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), keys, game);

            }
        }
    }

    void makePlayer(String[] pieces) {
        game.addPlayer(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]));
        if (Boolean.parseBoolean(pieces[3]) == true) {
            game.player.xPozition = Integer.parseInt(pieces[4]);
            game.player.yPozition = Integer.parseInt(pieces[5]);
            game.player.lives = Integer.parseInt(pieces[5]);
            game.player.xPozition = Integer.parseInt(pieces[6]);
        }
    }

    void makeEnemy(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        if (Boolean.parseBoolean(pieces[3])) {
            enemy enemy = new enemy(x, y, 1, 1, 2, game);
            enemies.add(enemy);
        } else {
            x *= game.elementSize;
            y *= game.elementSize;
            enemy enemy = new enemy(x, y, Integer.parseInt(pieces[4]), Integer.parseInt(pieces[5]),Integer.parseInt(pieces[6]), game);
            enemies.add(enemy);
        }

    }

    void makeLive(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        heart element2 = new heart(x, y, 1, true, game);
        items.add(element2);
    }

    void makeElement(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        element element2 = new element(x, y, pieces[3], true, game);
        items.add(element2);
    }

    void makeWall(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        wall element2 = new wall(x, y, Integer.parseInt(pieces[3]), Integer.parseInt(pieces[4]), game);
        walls.add(element2);
    }

    ArrayList<String> readFile() {
        ArrayList<String> text = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(level);
            while (scanner.hasNextLine()) {
                // System.out.println(scanner.nextLine());
                text.add(scanner.nextLine());
            }
            scanner.close();
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    void loadTestLevel() {

        enemy enemy = new enemy(15, 15, 1, 1, 2, game);
        // enemy enemy2 = new enemy(5, 25, game);
        // enemy enemy3 = new enemy(15, 5, game);
        // enemy enemy5 = new enemy(1, 5, game);
        // enemy enemy6 = new enemy(4, 20, game);
        // enemy enemy7 = new enemy(18, 6, game);

        wall wall = new wall(15, 14, 4, -2, game);
        wall wall2 = new wall(15, 16, 4, 2, game);
        wall wall3 = new wall(14, 15, 4, -1, game);
        wall wall4 = new wall(16, 15, 4, 1, game);

        // wall bot = new wall(1, 1, game.xElements, 1, game);

        // enemies = new enemy[] { enemy }; // , enemy2, enemy3, enemy5, enemy6, enemy7};
        sideWall();
        game.addWalls(new wall[] { wall });// , wall2, wall3, wall4});
        // game.addEnemys(enemies);
        game.gamInventory = new inventory(game, 2);
        element element = new element("heart", game);
        heart element2 = new heart(14, 14, 1, true, game);
        endPoint endPoint = new endPoint(10, 10, null, game);
        System.out.println("level " + levelNumber + " is loaded");
    }

    public void sideWall() {
        wall top = new wall(0, 0, game.xElements, 1, game);
        wall bot = new wall(0, game.yElements - 1, game.xElements, 1, game);
        wall left = new wall(0, 0, game.xElements, 2, game);
        wall right = new wall(game.xElements - 1, 0, game.xElements, 2, game);

        game.addWalls(new wall[] { top, bot, left, right });
    }

    boolean openLevel(String levelName) {
        File directory;
        File[] levels;
        levelName += ".txt";
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        for (File file : levels) {
            if (file.getName() == levelName) {
                level = file;
                return true;
            }
            // System.out.println(file);
        }
        if (levels == null) {
            throw new IllegalStateException("Unable to access files in the specified directory.");
        }
        level = null;
        return false;
    }

    boolean openLevel() {
        String name = "level" + levelNumber;
        return openLevel(name);
    }

    @Override
    public void run() {
        openLevel();
        analiseText(readFile());
        loadLevel();
        System.out.println("level " + levelNumber + " is loaded");
    }

}
