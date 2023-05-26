package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class mainScreen extends JPanel implements Runnable {
    public int levelCount;
    File[] levels;
    game game;
    Image LevelFrame;
    boolean escWasPress = false;

    public mainScreen(game game) {
        openImageLevel();
        loadLevels();
        this.game = game;
        this.game.window.frame.add(this);
    }

    void loadLevels() {
        File directory;
        directory = new File("src/main/java/levels/");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }

        levels = directory.listFiles();
        for (File file : levels) {
            System.out.println(file);
        }
        if (levels == null) {
            throw new IllegalStateException("Unable to access files in the specified directory.");
        }
        levelCount = levels.length;
    }

    private void openImageLevel() {
        String fullname = "/img/LevelFrame.png";
        try {
            LevelFrame = ImageIO.read(getClass().getResourceAsStream(fullname));
            System.out.print("successful image uploaded of ");
            System.out.println("LevelFrame");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        openMainScreen(g2);
    }

    void openMainScreen(Graphics2D g2) {
        for (int i = 0; i < levelCount; i++) {
            int imageSize = game.elementSize * 4;
            int xPozition = 10 * game.elementSize;
            int yPozition = game.elementSize * i * imageSize + game.elementSize;

            g2.drawImage(LevelFrame, xPozition, yPozition, imageSize, imageSize, null);
        }
    }

    void escStatus() {
        if (game.keyboard.escIsPress && !escWasPress) {
            escWasPress = true;
        }
        if (game.keyboard.escIsPress && escWasPress) {
            escWasPress = false;
        }
    }

    @Override
    public void run() {
        escStatus();
        if (escWasPress) {
            repaint();
        }
    }

}
