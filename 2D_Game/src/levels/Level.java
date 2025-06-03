package levels;

import entity.Enemy;
import main.GamePanel;

public class Level {
    GamePanel gp;
    private String level;

    public Level(GamePanel gp){
        this.gp = gp;
        level = "map02";
    }

    public void setValues() {
        switch(level){
            case "map01" :  
            gp.player.setPlayerPosition(3*gp.TILE_SIZE, 2*gp.TILE_SIZE);
            
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy02", 10 * gp.TILE_SIZE, 5 * gp.TILE_SIZE, 80, 71, true));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy02", 20 * gp.TILE_SIZE, 5 * gp.TILE_SIZE, 80, 71, false));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy01", 40 * gp.TILE_SIZE, 5 * gp.TILE_SIZE, 53, 48, true));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy02", 20 * gp.TILE_SIZE, 12 * gp.TILE_SIZE, 80, 71, true));

            break;
            case "map02" :
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy01", 15 * gp.TILE_SIZE, 15 * gp.TILE_SIZE, 53, 48, true));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy01", 25 * gp.TILE_SIZE, 8 * gp.TILE_SIZE, 53, 48, false));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy01", 23 * gp.TILE_SIZE, 17 * gp.TILE_SIZE, 53, 48, true));
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy01", 42 * gp.TILE_SIZE, 14 * gp.TILE_SIZE, 53, 48, true));
            
            gp.enemyManager.enemys.add(new Enemy(gp, "Enemy02", 38 * gp.TILE_SIZE, 22 * gp.TILE_SIZE, 80, 71, false));
            break;
        }
    }

    public String getLevel(){
        return level;
    }
}
