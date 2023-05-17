package game;

import entity.enemy;
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
        game.addPlayer(1,1);
        // loadLevel();
    }
    void loadLevel(){
        enemy enemy = new enemy(25, 25, game);
        enemy enemy2 = new enemy(5, 25, game);
        enemy enemy3 = new enemy(15, 5, game);
        enemy enemy5 = new enemy(1, 5, game);
        enemy enemy6 = new enemy(4, 20, game);
        enemy enemy7 = new enemy(18, 6, game);

        wall wall = new wall(10, 10, 1, 1, game);
        wall wall2 = new wall(15, 3, 1, 1, game);
        wall wall3 = new wall(7, 25, 1, 1, game);

        // wall bot = new wall(1, 1, game.xElements, 1, game);

        enemies = new enemy [] {enemy, enemy2, enemy3, enemy5, enemy6, enemy7};
        sideWall();
        game.addWalls(new wall [] {wall, wall2, wall3});
        game.addEnemys(enemies);
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
