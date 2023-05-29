package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The keyboard class handles keyboard input by implementing the KeyListener
 * interface.
 */
public class keyboard implements KeyListener {
    public boolean upIsPress,
            downIsPress,
            leftIsPress,
            rightIsPress,
            shiftIsPress,
            spaceIsPress,
            fIsPress,
            escIsPress,
            enterIsPress,
            iIsPress = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not used in this implementation
    }

    /**
     * Invoked when a key is pressed.
     *
     * @param e the KeyEvent object containing information about the key press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("loop");
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upIsPress = true;
            System.out.print("Up: ");
            System.out.println(upIsPress);
        }
        if (code == KeyEvent.VK_A) {
            leftIsPress = true;
            System.out.print("Left: ");
            System.out.println(leftIsPress);
        }
        if (code == KeyEvent.VK_S) {
            downIsPress = true;
            System.out.print("Down: ");
            System.out.println(downIsPress);
        }
        if (code == KeyEvent.VK_D) {
            rightIsPress = true;
            System.out.print("Right: ");
            System.out.println(rightIsPress);
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftIsPress = true;
            System.out.print("Shift: ");
            System.out.println(shiftIsPress);
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceIsPress = true;
            System.out.print("Space: ");
            System.out.println(spaceIsPress);
        }
        if (code == KeyEvent.VK_F) {
            fIsPress = true;
            System.out.print("F: ");
            System.out.println(fIsPress);
        }
        if (code == KeyEvent.VK_ENTER) {
            enterIsPress = true;
            System.out.print("Enter: ");
            System.out.println(enterIsPress);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escIsPress = true;
            System.out.print("Esc: ");
            System.out.println(escIsPress);
        }
        if (code == KeyEvent.VK_I) {
            iIsPress = true;
            System.out.print("I: ");
            System.out.println(iIsPress);
        }
        // System.out.println("run");
        logger.logFiner("Key States - Up: " + upIsPress +
                ", Down: " + downIsPress +
                ", Left: " + leftIsPress +
                ", Right: " + rightIsPress +
                ", Shift: " + shiftIsPress +
                ", Space: " + spaceIsPress +
                ", F: " + fIsPress +
                ", Esc: " + escIsPress +
                ", Enter: " + enterIsPress +
                ", I: " + iIsPress);
    }

    /**
     * Invoked when a key is released.
     *
     * @param e the KeyEvent object containing information about the key release
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upIsPress = false;
        }
        if (code == KeyEvent.VK_A) {
            leftIsPress = false;
        }
        if (code == KeyEvent.VK_S) {
            downIsPress = false;
        }
        if (code == KeyEvent.VK_D) {
            rightIsPress = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftIsPress = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceIsPress = false;
        }
        if (code == KeyEvent.VK_F) {
            fIsPress = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterIsPress = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escIsPress = false;
        }
        if (code == KeyEvent.VK_I) {
            iIsPress = false;
        }
    }

    public void print() {
        System.out.println();
        System.out.print("Key States - Up: ");
        System.out.print(upIsPress);
        System.out.print(", Down: ");
        System.out.print(downIsPress);
        System.out.print(", Left: ");
        System.out.print(leftIsPress);
        System.out.print(", Right: ");
        System.out.print(rightIsPress);
        System.out.print(", Shift: ");
        System.out.print(shiftIsPress);
        System.out.print(", Space: ");
        System.out.print(spaceIsPress);
        System.out.print(", F: ");
        System.out.print(fIsPress);
        System.out.print(", Esc: ");
        System.out.print(escIsPress);
        System.out.print(", Enter: ");
        System.out.print(enterIsPress);
        System.out.print(", I: ");
        System.out.println(iIsPress);
    }

    // @Override
    // public void run() {

    // }
}
