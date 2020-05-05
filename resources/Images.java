package resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
/**
 * Write a description of class Images here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Images
{
    private static BufferedImage[] blocks;
    
    public Images() {
        blocks = new BufferedImage[5];
        try{
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/images/grass.jpg"));
            blocks[1] = ImageIO.read(getClass().getResourceAsStream("/images/dirt.jpg"));
            blocks[2] = ImageIO.read(getClass().getResourceAsStream("/images/stone.png"));
            blocks[3] = ImageIO.read(getClass().getResourceAsStream("/images/water.png"));
            blocks[4] = ImageIO.read(getClass().getResourceAsStream("/images/water_top.png"));
            blocks[5] = ImageIO.read(getClass().getResourceAsStream("/images/lava_top.png"));
            blocks[6] = ImageIO.read(getClass().getResourceAsStream("/images/lava.png"));
            blocks[7] = ImageIO.read(getClass().getResourceAsStream("/images/wood.png"));
            blocks[8] = ImageIO.read(getClass().getResourceAsStream("/images/water_top.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static BufferedImage[] getBlocks(){
        return blocks;
    }
}
