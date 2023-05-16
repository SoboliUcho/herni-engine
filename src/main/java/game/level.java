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
        loadLevel();
    }
    void loadLevel(){
        game.addPlayer();
        enemy enemy = new enemy(20, 20, game);
        enemies = new enemy [] {enemy};
        game.addEnemys(enemies);
    }
}
