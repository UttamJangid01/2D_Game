package entity;

import static entity.HelpMethods.*;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import main.GamePanel;
import main.KeyHandling;
import static entity.Constants.PlayerConstants.*;

public class Player extends Entity{

    GamePanel gp;
    private final int width, height;
    private BufferedImage img1, img2;
    private int playerAction = IDLE;
    public final float gap = 2;
    public final float speed = 3;
    private final float gravity = 0.3f;
    private float velocityY = 0f; 
    private boolean jump = false;
    private boolean mirror = false;
    private float xDrawOffset = 12;
    private float yDrawOffset = 20;

    public Player(GamePanel gp, float x, float y, int width, int height){
        // super(x, y, width, height);
        this.gp = gp;
        this.width = width;  
        this.height = height;
        initHitBox(x, y-yDrawOffset, 27, 45);
        //initHitBox(x, y, width, height-gap);
        try{
            img1 = ImageIO.read(getClass().getResourceAsStream("/images/player_sprites.png"));
            img2 = ImageIO.read(getClass().getResourceAsStream("/images/second_player_sprites.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        aniSpeed = 15;
        loadAnimations();
    }

    public void setPlayerPosition(float x, float y){
        hitbox.x = x;
        hitbox.y = y;
    }

    public void update(KeyHandling keyH){
        updatePos(keyH);
        Gravity(0);
    }
    
    private void loadAnimations(){
        animation = new BufferedImage[19][8];
        int row = 0 ; 
        for(int j=0; j<10; j++, row++){
            for(int i=0, col=0; i<8; i++, col++){
                animation[row][col] = img1.getSubimage(i * 101, j * 71, 101, 71);
            }
        }

        for(int j=0; j<9; j++, row++){
            for(int i=0, col=0; i<8; i++, col++){
                animation[row][col] = img2.getSubimage(i * 101, j * 71, 101, 71);
            }
        }
    }

    public void Gravity(float ySpeed){
        velocityY += gravity;
        ySpeed += velocityY;
        
        if(CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, gp.file.map)){
            hitbox.y += ySpeed; 
            jump = false;
        }
        else{
            velocityY = 0;
            jump = true;
        }
        if(!playerHead(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, gp.file.map))
            velocityY = 0.1f; 
    }

    private void changePlayerAction(int newAction){
        if(playerAction != newAction){
            playerAction = newAction;
            aniIndex = 0;
        }
    }

    public void updatePos(KeyHandling keyH){
        if(playerAction == ATTACK_1)
            return;

        if(!keyH.space && !keyH.up && !keyH.down && !keyH.left && !keyH.right && !keyH.attack){
            changePlayerAction(IDLE);
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if(keyH.space && jump){
            velocityY = -7f;
            changePlayerAction(JUMP);
        }
        if(keyH.up){
            ySpeed -= speed/4;
            changePlayerAction(CLIMB);
        }
        if(keyH.down){
            ySpeed += speed/4;
            changePlayerAction(CLIMB);
        }
        if(keyH.left){
            xSpeed -= speed;
            mirror = true;
            changePlayerAction(RUN);
        }
        if(keyH.right){
            xSpeed += speed;
            mirror = false;
            changePlayerAction(RUN);
        }
        if(keyH.attack){
            keyH.attack = false;
            changePlayerAction(ATTACK_1);
        }
        
        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, gp.file.map)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
        }
        
        if(CanClimbHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, gp.file.map)){
        	hitbox.y += ySpeed;
        	velocityY = -0.3f;
        }
    }

    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                if(playerAction == ATTACK_1){
                    playerAction = IDLE;
                }
            }
        }
    }
    
    

    public void draw(Graphics2D g2){
        updateAnimationTick();
        if(mirror){
            xDrawOffset = 61;
            g2.drawImage(getMirroredImage(animation[playerAction][aniIndex]),(int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        }
        else{
            xDrawOffset = 12;
            g2.drawImage(animation[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        }
        // drawHitbox(g2);
    }

    public Rectangle2D.Float getPlayerHitbox(){
        return hitbox;
    }
}
