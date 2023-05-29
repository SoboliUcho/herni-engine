package game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

/*
*this class is not use in this implemantation 
*/
public class mainScreen {
    ArrayList<JButton> buttons;
    public int levelCount;
    File[] levels;
    game game;
    Image LevelFrame;

    public mainScreen(game game) {
        buttons = new ArrayList<>();
        // openImageLevel();
        this.game = game;
        // addButons();
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

    File[] levels() {
        return levels;
    }

    void addButons() {
        for (int i = 0; i < levels.length; i++) {
            String leveltext = levels[i].getName();
            if (leveltext.contains("level")) {
                leveltext = leveltext.replace("level", "");
                leveltext = leveltext.replace(".txt", "");
                final int levelNumber = Integer.parseInt(leveltext);

                JButton button = new JButton("level " + leveltext);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // switchPanel(panel1);

                        game.levelNumber = levelNumber;
                        game.escWasPress = false;
                        game.removeAll();
                        game.window.frame.add(game);
                        game.window.frame.addKeyListener(game.keyboard);
                        game.buttonWasAdd = false;
                        // game.repaint();
                    }

                });
                // button.setVisible(false);
                buttons.add(button);
            }
        }
    }

    JButton[] loadButons() {
        JButton[] button;
        button = buttons.toArray(new JButton[buttons.size()]);
        System.out.println("buttons were add");
        return button;
    }

    public void run() {
        // System.out.println("buttons were add");
        loadLevels();
        addButons();
        // loadButons();
    }
}
