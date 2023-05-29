package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The menu class represents the game menu and handles the loading of levels and
 * the save functionality.
 */
public class menu implements Runnable {
    game game;
    ArrayList<JMenuItem> leves = new ArrayList<>();
    public int levelCount;
    File[] levels;
    JMenuBar menuBar = new JMenuBar();
    JMenu levelsMenu = new JMenu("levels");
    JMenu saveMenu = new JMenu("Save");
    JMenu exitMenu = new JMenu("exit");

    /**
     * Constructs a menu object for the specified game.
     *
     * @param game the game object
     */
    public menu(game game) {
        this.game = game;

    }

    /**
     * Creates the "Save" menu item and attaches an action listener to handle the
     * Open save operation.
     */
    void saveMenu() {
        JMenuItem button = new JMenuItem("save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.levelNumber = 0;
                game.escWasPress = false;
            }
        });
        saveMenu.add(button);
    }

    /**
     * Loads the available levels from the specified directory.
     */
    void loadLevels() {
        File directory;
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        // for (File file : levels) {
        // // System.out.println(file);
        // }
        if (levels == null) {
            throw new IllegalStateException("Unable to access files in the specified directory.");
        }
        levelCount = levels.length;
    }

    /**
     * Returns the array of level files.
     *
     * @return the array of level files
     */
    File[] levelsFiles() {
        return levels;
    }

    /**
     * Returns an array of menu items representing the available levels.
     *
     * @return the array of menu items
     */
    JMenuItem[] returnButons() {
        JMenuItem[] button;
        button = leves.toArray(new JMenuItem[leves.size()]);
        System.out.println("buttons were add");
        return button;
    }

    /**
     * Loads the level buttons for the available levels.
     */
    void loadButons() {
        for (int i = 0; i < levels.length; i++) {
            String leveltext = levels[i].getName();
            if (leveltext.contains("level")) {
                leveltext = leveltext.replace("level", "");
                leveltext = leveltext.replace(".txt", "");
                final int levelNumber = Integer.parseInt(leveltext);

                JMenuItem button = new JMenuItem("level " + leveltext);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.levelNumber = levelNumber;
                        game.escWasPress = false;
                    }
                });
                leves.add(button);
            }
        }
    }

    /**
     * Adds the level buttons to the menu bar and displays it in the game window.
     */
    void addButons() {
        for (JMenuItem file : leves) {
            levelsMenu.add(file);
        }
        menuBar.add(levelsMenu);
        menuBar.add(saveMenu);
        // menuBar.add(exitMenu);
        game.window.frame.setJMenuBar(menuBar);
        game.window.frame.pack();

    }

    /**
     * Implementation of the Runnable interface. This method is executed when the
     * menu is run as a thread.
     */
    @Override
    public void run() {
        loadLevels();
        loadButons();
        saveMenu();
        addButons();
        System.out.println("buttons were add");
    }
}
