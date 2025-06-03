package entity;

import static entity.Constants.Enemy01Constants.*;
import static entity.HelpMethods.*;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity{
    GamePanel gp;
    private int width, height;
    private boolean side;
    private String name;
    private boolean mirror;
    private boolean dead;
    private BufferedImage img1, img2;
    private float speed;
    private int enemyAction = WALK;
    private float xDrawOffset = 5;
    private float yDrawOffset;
    private float velocity = 0;

    public Enemy(GamePanel gp, String name,float x, float y, int width, int height, boolean side){
        this.gp = gp;
        this.name = name;
        this.width = width;
        this.height = height;
        this.side = mirror = side;
        if(name.equals("Enemy01")){
            speed = .7f;
            aniSpeed = 20;
            yDrawOffset = 15;
            initHitBox(x, y - yDrawOffset, 27, 27);
        }
        else if(name.equals("Enemy02")){
            speed = .5f;
            aniSpeed = 10;
            yDrawOffset = 9;
            initHitBox(x, y - 25, 35, 56);
        }

        // fix it : it's every time set images
        try{
            img1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy01.png"));
            img2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy02.png"));     
        }catch(IOException e){
            e.printStackTrace();
        }

        loadAnimations();
    }

    private void loadAnimations() {
        animation = new BufferedImage[10][8];
        int row = 0;
        for(int j=0; j<5; j++, row++){
            for(int i=0, col=0; i<6; i++, col++){
                animation[row][col] = img1.getSubimage(i*53, j*48, 53, 48);
            }
        }

        for(int j=0; j<5; j++, row++){
            for(int i=0, col=0; i<8; i++, col++){
                animation[row][col] = img2.getSubimage(i*80, j*71, 80, 71);
            }
        }
    }

    public void update(){
        updateAnimationTick();
        updatePos();
        EnemyDeath();
    }

    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            
            if(name.equals("Enemy01"))
                if(aniIndex >= E01_GetSpriteAmount(enemyAction))
                    aniIndex = 0;
            
            if(name.equals("Enemy02"))
                if(aniIndex >= E02_GetSpriteAmount(enemyAction))
                    aniIndex = 0;
        }
    }

    public void updatePos(){
        velocity = side ? speed : -speed;

        if(EnemyMoveHere(hitbox.x + velocity, hitbox.y, hitbox.width, hitbox.height, (gp.TILE_SIZE-hitbox.height)+yDrawOffset ,gp.file.map, side, name)){
            hitbox.x += velocity;
        }else{
            side = !side;
            mirror = !mirror;
        }
    }

    public void EnemyDeath(){
        Rectangle2D.Float playerHitbox = gp.player.getHitbox();
        if(((playerHitbox.x >= hitbox.x && playerHitbox.x <= hitbox.x+hitbox.width) || (playerHitbox.x+(playerHitbox.width/2) >= hitbox.x && playerHitbox.x+(playerHitbox.width/2) <= hitbox.x+hitbox.width) 
            || (playerHitbox.x+playerHitbox.width >= hitbox.x && playerHitbox.x+playerHitbox.width <= hitbox.x+hitbox.width)) && (playerHitbox.y+playerHitbox.height >= hitbox.y && playerHitbox.y+playerHitbox.height <= hitbox.y+20)){
            dead = true;
        }
    }

    public void collision(){
        if(dead) return;
        Rectangle2D.Float playerHitbox = gp.player.getHitbox();
        if(playerHitbox.intersects(hitbox)){
            gp.gameThread = null;
        }
    }

    public void draw(Graphics2D g2){
        if(!mirror){
            switch(name){
                case "Enemy01": xDrawOffset = 21;
                                g2.drawImage(getMirroredImage(animation[enemyAction][aniIndex]), (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);break;
                case "Enemy02": xDrawOffset = 33;
                                g2.drawImage(getMirroredImage(animation[5 + enemyAction][aniIndex]), (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);break;
                default: break;
            }
        }else{
            switch(name){
                case "Enemy01": xDrawOffset = 5;
                                g2.drawImage(animation[enemyAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);break;
                case "Enemy02": xDrawOffset = 12;
                                g2.drawImage(animation[5 + enemyAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);break;
                default: break;
            }
            
        }
        // drawHitbox(g2);
    }

    public boolean isDead(){
        return dead;
    }
}