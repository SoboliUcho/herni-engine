package game;

import javax.print.event.PrintEvent;
import javax.sound.midi.VoiceStatus;
import javax.swing.JFrame;

import entity.element;
import entity.entita;
import entity.player;

public class window extends JFrame {
    int elementSize;
    int xElements;
    int yElements;
    int xSize;
    int ySize;

    player player;

    String name;
    JFrame frame;

    public window(String name) {
        this.name = name;
        this.elementSize = 10;
        this.xElements = 50;
        this.yElements = 50;
        this.xSize = elementSize * xElements;
        this.ySize = elementSize * yElements;

        makeWindow();
    }

    void makeWindow() {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize, ySize);
        frame.setVisible(true);
    }
    void addPlayer (player player){
        this.player = player;
    }
    void PrintEntita (entita entita){
        int[] pozicion = entita.entitaPozicion();

        pozicion[0] = pozicion[0] * elementSize;
        pozicion[1] = pozicion[1] * elementSize;
        
        

        }
}
