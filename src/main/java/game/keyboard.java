package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class keyboard implements KeyListener{
    public boolean upIsPress,
            downIsPress,
            leftIsPress,
            rightIsPress,
            shiftIsPress,
            spaceIsPress,
            fIsPress,
            escIsPress,
            enterIsPress, 
            iIsPress= false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upIsPress = true;
        }
        if (code == KeyEvent.VK_A) {
            leftIsPress = true;
        }
        if (code == KeyEvent.VK_S) {
            downIsPress = true;
        }
        if (code == KeyEvent.VK_D) {
            rightIsPress = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftIsPress = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceIsPress = true;
        }
        if (code == KeyEvent.VK_F) {
            fIsPress = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterIsPress = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escIsPress = true;
        }
        if (code == KeyEvent.VK_I) {
            iIsPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upIsPress =  false;
        }
        if (code == KeyEvent.VK_A) {
            leftIsPress =  false;
        }
        if (code == KeyEvent.VK_S) {
            downIsPress =  false;
        }
        if (code == KeyEvent.VK_D) {
            rightIsPress =  false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftIsPress =  false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spaceIsPress =  false;
        }
        if (code == KeyEvent.VK_F) {
            fIsPress =  false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterIsPress =  false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escIsPress =  false;
        }
        if (code == KeyEvent.VK_I) {
            iIsPress =  false;
        }
    }
}
