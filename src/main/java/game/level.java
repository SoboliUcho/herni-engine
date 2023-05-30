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

/**
 * The level class represents a game level and handles the loading of game
 * elements within the level.
 */
public class level implements Runnable {
    File level;
    int levelNumber;
    String levelName;
    public boolean loaded = false;

    File save;

    game game;
    ArrayList<wall> walls = new ArrayList<>();
    public ArrayList<enemy> enemies = new ArrayList<>();
    public ArrayList<element> items = new ArrayList<>();
    public ArrayList<element> playerItems = new ArrayList<>();
    player player;

    /**
     * Constructs a level object with the specified level number and game instance.
     *
     * @param levelNumber the level number
     * @param game        the game instance
     */
    public level(int levelNumber, game game) {
        this.levelNumber = levelNumber;
        this.game = game;

        game.addPlayer(1, 1);
        openSave();
        game.levelThread = new Thread(this);
        game.levelThread.start();
        // loadLevel();
    }

    /** * uploads level elements to the game instance */
    public void loadLevel() {
        game.gamInventory = new inventory(game, items.size());
        for (int i = 0; i < items.size(); i++) {
            game.gamInventory.addEelentToInventory(items.get(i));
        }
        for (int i = 0; i < playerItems.size(); i++) {
            playerItems.get(i).isInInventory = true;
            game.player.inventory.addEelentToInventory(playerItems.get(i));
        }
        enemy[] enemy = enemies.toArray(new enemy[enemies.size()]);
        game.addEnemys(enemy);
        wall[] wallis = walls.toArray(new wall[walls.size()]);
        game.addWalls(wallis);
        sideWall();
        logger.logInfo("level loaded");
    }

    /**
     * Analyzes the text data and creates the corresponding game elements.
     *
     * @param text the text data representing the game elements
     */
    public void analiseText(ArrayList<String> text) {
        for (int i = 0; i < text.size(); i++) {
            String[] pieces = text.get(i).split("#");
            for (int j = 0; j < pieces.length; j++) {
                pieces[j] = pieces[j].trim();
            }
            if (pieces[0].equals("player")) {
                makePlayer(pieces);
            } else if (pieces[0].equals("enemy")) {
                makeEnemy(pieces);
            } else if (pieces[0].equals("element") &&pieces[3].equals("heart")) {
                makeLive(pieces);
            } else if (pieces[0].equals("element")&&!pieces[3].equals("heart")) {
                makeElement(pieces);
            } else if (pieces[0].equals("wall")) {
                makeWall(pieces);
            } else if (pieces[0].equals("endPoint")) {
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

    /**
     * creates an entity-player according to the parameters in the text
     * 
     * @param pieces field of individual parameters in String form
     */
    void makePlayer(String[] pieces) {
        game.addPlayer(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]));
        if (Boolean.parseBoolean(pieces[3]) == false) {
            game.player.xPozition = Integer.parseInt(pieces[1]);
            game.player.yPozition = Integer.parseInt(pieces[2]);
            game.player.setDefault();
            game.player.lives = Integer.parseInt(pieces[4]);
            game.player.strange = Integer.parseInt(pieces[5]);
            logger.logFine("player was made");
        }
    }

    /**
     * creates an entity-Enemy according to the parameters in the text
     * 
     * @param pieces field of individual parameters in String form
     */
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
        logger.logFine("enemy was made");
    }

    /**
     * creates an element-Lifes according to the parameters in the text
     * 
     * @param pieces field of individual parameters in String form
     */
    void makeLive(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        heart element2 = new heart(x, y, 1, true, game);
        items.add(element2);
        logger.logFine("heart was made");
    }

    /**
     * creates an element according to the parameters in the text
     * 
     * @param pieces field of individual parameters in String form
     */
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
        logger.logFine("element was made");
    }

    /**
     * creates an wall according to the parameters in the text
     * 
     * @param pieces field of individual parameters in String form
     */
    void makeWall(String[] pieces) {
        int x = Integer.parseInt(pieces[1]);
        int y = Integer.parseInt(pieces[2]);
        wall element2 = new wall(x, y, Integer.parseInt(pieces[3]), Integer.parseInt(pieces[4]), game);
        walls.add(element2);
        logger.logFine("wall was made");
    }

    /**
     * reads the file and stores each line in an array
     */
    ArrayList<String> readFile() {
        ArrayList<String> text = new ArrayList<>();

        // System.out.println(level.getName());
        // System.out.println(level);
        // System.out.println(this.levelName);
        try {
            Scanner scanner = new Scanner(level);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                logger.logFiner(line);
                text.add(line);
            }
            scanner.close();
            logger.logFine(level.getName() + "was read");
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.logError("failed to read file", e);
        }
        return null;

    }

    /**
     * Loads a test level with predefined game elements for testing purposes.
     */
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

    /**
     * Creates and adds the side walls to the wall repository.
     */
    public void sideWall() {
        wall top = new wall(0, 0, game.xElements, 1, game);
        wall bot = new wall(0, game.yElements - 1, game.xElements, 1, game);
        wall left = new wall(0, 0, game.xElements, 2, game);
        wall right = new wall(game.xElements - 1, 0, game.xElements, 2, game);

        game.addWalls(new wall[] { top, bot, left, right });
        logger.logInfo("side wall was made");
    }

    /**
     * Opens the specified level file by its name.
     *
     * @param levelName The name of the level file to open.
     * @return true if the level file exists and is successfully opened, false
     *         otherwise.
     */
    boolean openLevel(String levelName) {
        File directory;
        File[] levels;
        this.levelName = levelName;
        this.levelName += ".txt";
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            logger.logError("pecified path is not a directory.", null);
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        for (int i = 0; i < levels.length; i++) {
            System.out.println(this.levelName);
            if (levels[i].getName().equals(this.levelName)) {
                level = levels[i];
                // System.out.println("level file exist");
                logger.logInfo("level file exist");
                return true;
            }
        }
        level = null;
        return false;
    }

    /**
     * Opens the level based on the level number.
     *
     * @return true if the level file exists and is successfully opened, false
     *         otherwise.
     */
    boolean openLevel() {
        if (levelNumber == 0) {
            return openLevel("save");
        } else {
            String name = "level" + levelNumber;
            return openLevel(name);
        }
    }

    /**
     * Saves the current game progress to a file.
     */
    void saveProgress() {
        String player = "player #" + game.player.xPozition + "#" + game.player.yPozition + "#false #"
                + game.player.lives
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
                    + enemy.strange + "#" + enemy.range;
            enemyiList.add(enem);
        }

        ArrayList<String> gameItemList = new ArrayList<>();

        for (element item : game.gamInventory.inventory) {
            if (item != null) {
                String enem = "element #" + item.xPozition + "#" + item.yPozition + "#" + item.type + "#game";
                gameItemList.add(enem);
            }
        }

        ArrayList<String> playerItemList = new ArrayList<>();

        for (element item : game.player.inventory.inventory) {
            if (item != null) {
                String enem = "element #" + item.xPozition + "#" + item.yPozition + " #" + item.type + "#player";
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
            logger.logFine("Text written to file successfully.");
        } catch (IOException e) {
            logger.logError("Save faield", e);
            e.printStackTrace();
        }
    }

    /**
     * Opens the save file.
     */
    void openSave() {
        File directory;
        File[] levels;
        String Name = "save.txt";
        directory = new File("src/main/java/levels/");
        if (!directory.isDirectory()) {
            logger.logError("specified path is not a directory.", null);
            throw new IllegalArgumentException("Specified path is not a directory.");
        }
        levels = directory.listFiles();
        for (int i = 0; i < levels.length; i++) {
            // System.out.println(Name);
            if (levels[i].getName().equals(Name)) {
                save = levels[i];
                // System.out.println("save file exist");
                logger.logInfo("level file exist");
            }
        }
    }

    /**
     * Implementation of the Runnable interface. This method is executed when the
     * level is run as a thread.
     */
    @Override
    public void run() {
        openLevel();
        analiseText(readFile());
        loadLevel();
        loaded = true;
        logger.logInfo("level " + levelNumber + " is loaded");
    }

}
