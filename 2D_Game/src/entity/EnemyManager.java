package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import main.GamePanel;

public class EnemyManager {
    private GamePanel gp;
    public ArrayList<Enemy> enemys;

    public EnemyManager(GamePanel gp) {
        this.gp = gp;
        enemys = new ArrayList<>();
        // loadEnemies();
    }

    private void loadEnemies() {
        // side is true means enemy moves right
        // side is false means enemy moves left
        // mirror is true means enemy right side face
        // mirror is false means enemy left side face
        
    }

    public void update() {
        for (Enemy e : enemys) {
            e.update();
            e.collision();
        }
        removeDeadEnemies();
    }

    private void removeDeadEnemies(){
        enemys.removeIf(e-> e.isDead());
    }

    public void draw(Graphics2D g2) {
        for (Enemy e : enemys) {
            e.draw(g2);
        }
    }
}
