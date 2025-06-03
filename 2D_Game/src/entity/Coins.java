package entity;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Coins extends Entity{

    GamePanel gp;
    int width, height;
    int xDrawOffset = 6;
    int yDrawOffset = 6;
    BufferedImage img1;
    boolean coinIsCollected = false;

    public Coins(GamePanel gp,float x, float y, int width, int height){
        this.gp = gp;
        this.width = width;
        this.height = height;
        initHitBox(x+xDrawOffset, y+yDrawOffset, 20, 20);

        try{
            img1 = ImageIO.read(getClass().getResourceAsStream("/images/coins_sprites.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        aniSpeed = 10;
        loadAnimations();
    }

    private void loadAnimations() {
        animation = new BufferedImage[1][12];
        for(int i=0; i<12; i++){
            animation[0][i] = img1.getSubimage(i * 61, 0, 61, 61);
        }
    }

    public void update(){
        updateAnimationTick();
    }

    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 12){
                aniIndex = 0;
            }
        }
    }

    public void collision(){
        Rectangle2D.Float playerHitbox = gp.player.getHitbox();
        if(playerHitbox.intersects(hitbox)){
            coinIsCollected = true;
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(animation[0][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        // drawHitbox(g2);
    }

    public boolean isCollected(){
        return coinIsCollected;
    }
}
