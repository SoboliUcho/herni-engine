package hra;
import org.junit.Before;
import org.junit.Test;

import entity.element;
import entity.enemy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.logging.Level;

import game.*;

public class levelTest {
    private game game;
    private level level;

    @Before
    public void setUp() {
        game = new game(Level.INFO);
        level = new level(1, game);
    }

    @Test
    public void testLoadLevel() {
        level.run();
        assertTrue(level.loaded);
    }

    @Test
    public void testAnaliseText() {
        ArrayList<String> text = new ArrayList<>();
        text.add("element #1 #1 #heart #game");
        text.add("enemy #10 #10 #true");
        level.analiseText(text);
        assertEquals(1, level.enemies.size());
        assertEquals(1, level.items.size());
    }
    @Test
    public void testPlayerInventory(){
        element element = new element(0, 0,"sword",0,0, false, game);

        game.player.inventory.addEelentToInventory(element);

        assertEquals(element, game.player.inventory.inventory[0]);
    }
}
