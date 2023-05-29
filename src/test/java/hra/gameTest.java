package hra;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import entity.*;
import game.*;

public class gameTest {
    private game game;

    @Before
    public void setUp() {
        game = new game();
    }
    @Test
    public void testAddEndPoint() {
        endPoint endPoint = new endPoint(0, 0, null, game);
        game.addEndPoint(endPoint);
        assertEquals(endPoint, game.endPoint);
    }
    @Test
    public void testAddPlayer() {
        player player = new player(1, 1, game, game.keyboard);
        game.addPlayer(player);
        assertEquals(player, game.player);
        assertEquals(game, player.game);
    }
    @Test
    public void testAddWalls() {
        wall[] walls = { new wall(1, 1, 1, 1, game) };
        game.addWalls(walls);
        assertArrayEquals(walls, game.walls);
    }
}
