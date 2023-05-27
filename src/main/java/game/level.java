package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    String levelName;

    File save;

    game game;
    ArrayList<wall> walls = new ArrayList<>();
    ArrayList<enemy> enemies = new ArrayList<>();
    ArrayList<element> items = new ArrayList<>();
    ArrayList<element> playerItems = new ArrayList<>();
    player player;

    public level(int levelNumber, game game) {
        this.levelNumber = levelNumber;
        this.game = game;

        game.addPlayer(1, 1);
        openSave();
        game.levelThread = new Thread(this);
        game.levelThread.start();
        // loadLevel();
    }

    void loadLevel() {
        game.gamInventory = new inventory(game, items.size());
        for (int i = 0; i < items.size(); i++) {
            game.gamInventory.addEelentToInventory(items.get(i));
        }
        for (int i = 0; i < playerItems.size(); i++) {
            game.player.inventory.addEelentToInventory(items.get(i));
        }
        enemy[] enemy = enemies.toArray(new enemy[enemies.size()]);
        game.addEnemys(enemy);
        wall[] wallis = walls.toArray(new wall[walls.size()]);
        game.addWalls(wallis);
        sideWall();
    }

    void analiseText(ArrayList<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String[] pieces = text.get(i).split("#");
            for (int j = 0; j < pieces.length; j++) {
                pieces[j] = pieces[j].trim();
                // System.out.print(j + " ");
                // System.out.println(pieces[j]);
            }
            // System.out.print(pieces[0] + "-" +pieces[1]);
            if (pieces[0].equals("player")) {
                makePlayer(pieces);
            }
            if (pieces[0].equals("enemy")) {
                makeEnemy(pieces);
            }
            if (pieces[0].equals("live")) {
                makeLive(pieces);
            }
            if (pieces[0].equals("element")) {
                makeElement(pieces);
            }
            if (pieces[0].equals("wall")) {
                makeWall(pieces);
            }
            if (pieces[0].equals("endPoint")) {
                int x = Integer.parseInt(pieces[1]);
                int y = Integer.parseInt(pieces[2]);
                String[] keys;
                if (pieces[3].equals("null")) {
                    keys = null;
                } else {
                    keys = pieces[3].split(";");
                }
                endPoint endPoint = new endPoint(x, y, keys, game);
                game.addEndPoint(endPoint);
            }
        }
    }

    void makePlayer(String[] pieces) {
        game.addPlayer(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]));
        if (Boolean.parseBoolean(pieces[3]) == true) {
            game.player.xPozition = Integer.parseInt(pieces[1]);
            game.player.yPozition = Integer.parseInt(pieces[2]);
            game.player.setDefault();
            game.player.lives = Integer.parseInt(pieces[4]);
            game.player.xPozition = Integer.parseInt(pieces[5]);
        }
    }

    void makeEnemy(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        if (Boolean.parseBoolean(pieces[3])) {
            enemy enemy = new enemy(x, y, 1, 1, 2, game);
            enemies.add(enemy);
        } else {
            enemy enemy = new enemy(x, y, Integer.parseInt(pieces[4]), Integer.parseInt(pieces[5]),
                    Integer.parseInt(pieces[6]), game, 1);
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
        if (pieces[4].equals("player")) {
            element2.isInInventory = true;
            element2.isColectable = false;
            playerItems.add(element2);
        } else {
            items.add(element2);
        }
    }

    void makeWall(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        wall element2 = new wall(x, y, Integer.parseInt(pieces[3]), Integer.parseInt(pieces[4]), game);
        walls.add(element2);
    }

    ArrayList<String> readFile() {
        ArrayList<String> text = new ArrayList<>();
        // System.out.println(level.getName());
        // System.out.println(level);
        // System.out.println(this.levelName);
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

        // enemies = new enemy[] { enemy }; // , enemy2, enemy3, enemy5, enemy6,
        // enemy7};
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
        this.levelName = levelName;
        this.levelName += ".txt";
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        for (int i = 0; i < levels.length; i++) {
            System.out.println(this.levelName);
            if (levels[i].getName().equals(this.levelName)) {
                level = levels[i];
                System.out.println("level file exist");
                return true;
            }
        }
        level = null;
        return false;
    }

    boolean openLevel() {
        String name = "level" + levelNumber;
        return openLevel(name);
    }

    void saveProgress() {
        String player = "player #" + game.player.xPozition + "#" + game.player.yPozition + "#true #" + game.player.lives
                + "#" + game.player.strange;

        String endPointString = "endPoint #" + game.endPoint.xPozition + "#" + game.endPoint.yPozition + "#";
        if (game.endPoint.keys == null) {
            endPointString = endPointString + "null";
        } else {
            for (String key : game.endPoint.keys) {

                endPointString = endPointString + key + ",";
            }
        }

        ArrayList<String> enemyiList = new ArrayList<>();
        for (enemy enemy : game.enemies) {
            if (!enemy.isLive()) {
                continue;
            }
            String enem = "enemy #" + enemy.xPozition + "#" + enemy.yPozition + "#false #" + enemy.lives + "#"
                    + enemy.range;
            enemyiList.add(enem);
        }

        ArrayList<String> gameItemList = new ArrayList<>();
        for (element item : game.gamInventory.inventory) {
            String enem = item.type + "#" + item.xPozition + "#" + item.yPozition + "#" + item.type + "#game";
            gameItemList.add(enem);
        }

        ArrayList<String> playerItemList = new ArrayList<>();

        for (element item : game.player.inventory.inventory) {
            if (item != null) {
                String enem = item.type + "#" + item.xPozition + "#" + item.yPozition + "#" + item.type + "#player";
                playerItemList.add(enem);
            }
        }

        ArrayList<String> wallist = new ArrayList<>();
        for (wall item : walls) {
            String enem = "wall #" + item.xStart + "#" + item.yStart + "#" + item.lenght + "#" + item.direction;
            wallist.add(enem);
        }

        ArrayList<String> output = new ArrayList<>();
        output.add(player);
        output.add(endPointString);
        output.addAll(enemyiList);
        output.addAll(gameItemList);
        output.addAll(playerItemList);
        output.addAll(wallist);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(save));) {
            for (String line : output) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void openSave() {
        File directory;
        File[] levels;
        String Name = "save.txt";
        directory = new File("src/main/java/levels/");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }
        levels = directory.listFiles();
        for (int i = 0; i < levels.length; i++) {
            // System.out.println(Name);
            if (levels[i].getName().equals(Name)) {
                save = levels[i];
                System.out.println("save file exist");
            }
        }
    }

    @Override
    public void run() {
        openLevel();
        analiseText(readFile());
        loadLevel();
        System.out.println("level " + levelNumber + " is loaded");
    }

}
