package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class mainScreen extends JPanel implements Runnable {
    public int levelCount;
    File[] levels;
    game game;
    Image LevelFrame;
    boolean escWasPress = true;

    public Thread gameThread;
    public Thread menuThread;
    // public Thread keayboardThread;

    public mainScreen(game game) {
        openImageLevel();
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
        for (File file : levels) {
            // System.out.println(file);
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
        this.game.window.frame.add(this);
        for (int i = 0; i < levelCount; i++) {
            int imageSize = game.elementSize * 5 - 5;
            int yPozition = game.elementSize * i * 5 + game.elementSize;
            int xPozition = 5* game.elementSize + game.elementSize;
            JButton button = new JButton("Click Me");
            button.setBounds(xPozition, yPozition, imageSize*4, imageSize);
            game.add(button);
            // game.revalidate();
            // game.repaint();
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
        while (!escWasPress) {
            
        }
    }

}
