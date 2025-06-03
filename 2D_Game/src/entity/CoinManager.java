package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class CoinManager {
    GamePanel gp;
    public int score = 0;
    public ArrayList<Coins> coinList;

    public CoinManager(GamePanel gp){
        this.gp = gp;
        coinList = new ArrayList<>();

        addCoins();
    }
    
    private void addCoins() {
        coinList.add(new Coins(gp, 38*gp.TILE_SIZE, 3*gp.TILE_SIZE, 32, 32));
        coinList.add(new Coins(gp, 42*gp.TILE_SIZE, 2*gp.TILE_SIZE, 32, 32));
        coinList.add(new Coins(gp, 44*gp.TILE_SIZE, 2*gp.TILE_SIZE, 32, 32));
        coinList.add(new Coins(gp, 46*gp.TILE_SIZE, 2*gp.TILE_SIZE, 32, 32));
        coinList.add(new Coins(gp, 48*gp.TILE_SIZE, 2*gp.TILE_SIZE, 32, 32));
        coinList.add(new Coins(gp, 38*gp.TILE_SIZE, 16*gp.TILE_SIZE, 32, 32));
    }

    public void update(){
        for(Coins coins : coinList){
            coins.update();
            coins.collision();
        }
        removeCoins();
    }

    private void removeCoins(){
        coinList.removeIf(e-> {
            if(e.isCollected()){
                score++;
                return true;
            }
            return false;
        });
    }

    public void draw(Graphics2D g2){
        for(Coins coins : coinList){
            coins.draw(g2);
        }
    }
}