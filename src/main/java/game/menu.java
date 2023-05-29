package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class menu implements Runnable{
    game game;
    ArrayList<JMenuItem> leves = new ArrayList<>();
    public int levelCount;
    File[] levels;
    JMenuBar menuBar = new JMenuBar();
    JMenu levelsMenu = new JMenu("levels");
    JMenu saveMenu = new JMenu("Save");
    JMenu exitMenu = new JMenu("exit");


    public menu(game game) {
        this.game = game;

    }
   void saveMenu(){
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

    File[] levelsFiles() {
        return levels;
    }

    JMenuItem[] returnButons() {
        JMenuItem[] button;
        button = leves.toArray(new JMenuItem[leves.size()]);
        System.out.println("buttons were add");
        return button;
    }

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

    void addButons() {
        for (JMenuItem file : leves) {
            levelsMenu.add(file);
        }
        menuBar.add(levelsMenu);
        menuBar.add(saveMenu);
        // menuBar.add(exitMenu);
        game.window.frame.setJMenuBar(menuBar);
    }
    @Override
    public void run() {
        loadLevels();
        loadButons();
        saveMenu();
        addButons();
        System.out.println("buttons were add");
    }
}
