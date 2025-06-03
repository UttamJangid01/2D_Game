package entity;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HelpMethods{
    public static GamePanel gp;
    private static final int speed = 3;
    public HelpMethods(GamePanel gp){
        this.gp = gp;
    }

    // For player
    public static boolean CanMoveHere(float x, float y, float width, float height, ArrayList<ArrayList<Integer>> lvlData){
        if(!IsSolid(x, y, lvlData))
            if(!IsSolid(x+width, y+height, lvlData))
                if(!IsSolid(x+width, y, lvlData))
                    if(!IsSolid(x, y+height, lvlData))
                        return true;    // player is moved.
        return false;   // player cannot moved. 
    }

    private static boolean IsSolid(float x, float y, ArrayList<ArrayList<Integer>> lvlData){
        if(x < 0 || x >= gp.WIDTH)
            return true;
        if(y < 0 || y >= gp.HEIGHT)
            return true;

        float xIndex = x/gp.TILE_SIZE;
        float yIndex = y/gp.TILE_SIZE;

        int value = lvlData.get((int)yIndex).get((int)xIndex);
        if(value > 0)
            return true;    // value is grater then zero.
        return false;   // value is equalto or less then zero.
    }

    public static boolean playerHead(float x, float y, float width, float height, ArrayList<ArrayList<Integer>> lvlData){
        if(!IsSolid(x, y, lvlData)) 
            if(!IsSolid(x+width, y, lvlData))
                return true;
        return false;
    }
 
    public static boolean CanClimbHere(float x, float y, float width, float height, ArrayList<ArrayList<Integer>> lvlData){
    	if(IsClimb(x-speed, y, lvlData) || IsClimb(x-speed, y+height, lvlData)|| IsClimb(x-speed, y+(height)/2, lvlData) || IsClimb(x+width+speed, y, lvlData) || IsClimb(x+width+speed, y+height, lvlData) || IsClimb(x+width+speed, y+(height)/2, lvlData)){	// left side or right side.
			if(!IsSolid(x-speed, y+height, lvlData) || !IsSolid(x+width+speed, y+height, lvlData))	// check player's futs if there is solid tile or not.
				return true;
		}
		return false;
    }
    
    private static boolean IsClimb(float x, float y, ArrayList<ArrayList<Integer>> lvlData){
        if(x < 0 || x >= gp.WIDTH)
            return false;
        if(y < 0 || y >= gp.HEIGHT)
            return false;

    	float xIndex = x/gp.TILE_SIZE;
    	float yIndex = y/gp.TILE_SIZE;
    	
    	int value = lvlData.get((int)yIndex).get((int)xIndex);
    	if(value == 1 || value == 3 || value == 4 || value == 6)
    		return true;
    	return false;
    }

    // For enemy
    public static boolean EnemyMoveHere(float x, float y, float width, float height, float TotalGap, ArrayList<ArrayList<Integer>> lvlData, boolean side, String name){
        if(side){
            if(name.equals("Enemy01"))
                if(IsSolid(x+width, y+height+TotalGap, lvlData) && x+width <gp.WIDTH)
                    return true;
            if(name.equals("Enemy02"))
                if(!IsSolid(x+width, y+height, lvlData) && x+width <gp.WIDTH)
                    return true;
        }else{
            if(name.equals("Enemy01"))
                if(IsSolid(x, y+height+TotalGap, lvlData) && x > 0)
                    return true;
            if(name.equals("Enemy02"))
                if(!IsSolid(x, y+height, lvlData) && x > 0)
                    return true;
        }
        return false;
    }

    public static BufferedImage getMirroredImage(BufferedImage image) {
        if (image == null) {
            return null;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        // Create mirrored image
        BufferedImage mirroredImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = mirroredImage.createGraphics();

        // Flip the image horizontally
        g2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();

        return mirroredImage;
    }
}
