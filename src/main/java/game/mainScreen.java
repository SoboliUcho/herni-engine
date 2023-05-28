package game;

import java.awt.Image;
import java.io.File;

public class mainScreen{
    public int levelCount;
    File[] levels;
    game game;
    Image LevelFrame;

    public mainScreen(game game) {
        // openImageLevel();
        loadLevels();
        this.game = game;
    }

    void loadLevels() {
        File directory;
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        // for (File file : levels) {
        //     // System.out.println(file);
        // }
        if (levels == null) {
            throw new IllegalStateException("Unable to access files in the specified directory.");
        }
        levelCount = levels.length;
    }
    File [] levels (){
        return levels;
    }


}
