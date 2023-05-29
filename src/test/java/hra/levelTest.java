package hra;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import game.*;

public class levelTest {
    private game game;
    private level level;

    @Before
    public void setUp() {
        game = new game();
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
}
