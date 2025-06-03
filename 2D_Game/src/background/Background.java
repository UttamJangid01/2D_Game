package background;

//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Background{
    GamePanel gp;
    ArrayList<BufferedImage> images;
    BufferedImage subImage;
    public Background(GamePanel gp){
        this.gp = gp;
        images = new ArrayList<>();
        loadImages();
    }

    public void loadImages(){
    	try{
            //images.add(ImageIO.read(new File("/home/hacker/Desktop/Projects/Tile.png")));
            subImage = ImageIO.read(getClass().getResourceAsStream("/images/tiles.png"));
            images.add(null);
            int tileSize = 50;
            int row=0, col=0;
            for(row=0; row<3; row++){
                for(col=0; col<3; col++){
                    images.add(subImage.getSubimage(col*tileSize, row*tileSize, tileSize, tileSize));
                }
            }
            subImage = ImageIO.read(getClass().getResourceAsStream("/images/stone.png"));
            images.add(subImage.getSubimage(0, 0, tileSize, tileSize));

            subImage = ImageIO.read(getClass().getResourceAsStream("/images/single_Grass.png"));
            images.add(subImage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for(int row = 0; row < gp.row; row++){
            for(int col = 0; col < gp.col; col++){
                int index = gp.file.map.get(row).get(col);
                g2.drawImage(images.get(index), col * gp.TILE_SIZE, row * gp.TILE_SIZE, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
        }
    }
}
