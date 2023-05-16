package game;

import entity.enemy;
import entity.player;

public class level {
    int levelNumber;
    player player;
    enemy [] enemies; 
    game game;

    public level(int levelNumber, game game) {
        this.levelNumber = levelNumber;
        this.game = game;
        game.addPlayer(0,0);
        // loadLevel();
    }
    void loadLevel(){
        enemy enemy = new enemy(25, 25, game);
        enemy enemy2 = new enemy(5, 25, game);
        enemy enemy3 = new enemy(15, 5, game);
        enemy enemy5 = new enemy(1, 5, game);
        enemy enemy6 = new enemy(4, 20, game);
        enemy enemy7 = new enemy(18, 6, game);


        enemies = new enemy [] {enemy, enemy2, enemy3, enemy5, enemy6, enemy7};
        game.addEnemys(enemies);
        System.out.println("level " + levelNumber + " is loaded");
    }
}
