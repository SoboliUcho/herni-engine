package game;

import entity.element;
import entity.enemy;
import entity.heart;
import entity.inventory;
import entity.player;
import entity.wall;

public class level {
    int levelNumber;
    player player;
    enemy [] enemies; 
    game game;

    public level(int levelNumber, game game) {
        this.levelNumber = levelNumber;
        this.game = game;
        game.addPlayer(20,20);
        // loadLevel();
    }
    void loadLevel(){
        enemy enemy = new enemy(15, 15, 1,1,2,game);
        // enemy enemy2 = new enemy(5, 25, game);
        // enemy enemy3 = new enemy(15, 5, game);
        // enemy enemy5 = new enemy(1, 5, game);
        // enemy enemy6 = new enemy(4, 20, game);
        // enemy enemy7 = new enemy(18, 6, game);

        wall wall = new wall(15, 14, 4, -2, game);
        wall wall2 = new wall(15, 16, 4, 2, game);
        wall wall3 = new wall(14, 15, 4, -1, game);
        wall wall4 = new wall(16, 15, 4, 1, game);


        // wall bot = new wall(1, 1, game.xElements, 1, game);

        enemies = new enemy [] {enemy}; //, enemy2, enemy3, enemy5, enemy6, enemy7};
        sideWall();
        game.addWalls(new wall [] {wall});//, wall2, wall3, wall4});
        game.addEnemys(enemies);
        game.gamInventory = new inventory(game, 2);
        element element = new element("heart", game);
        heart element2 = new heart(15,14,1,true, game);
        endPoint endPoint = new endPoint(10, 10, null, game);
        System.out.println("level " + levelNumber + " is loaded");
    }   
    public void sideWall(){
        wall top = new wall(0, 0, game.xElements, 1, game);
        wall bot = new wall(0, game.yElements-1, game.xElements, 1, game);
        wall left = new wall(0, 0, game.xElements, 2, game);
        wall right = new wall(game.xElements-1, 0, game.xElements, 2, game);

        game.addWalls(new wall[] {top, bot, left, right});
    }
}
