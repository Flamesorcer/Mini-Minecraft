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
    private static BufferedImage[] hearts;
    public Images() {
        blocks = new BufferedImage[10];
        hearts = new BufferedImage[3];
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
            blocks[9] = ImageIO.read(getClass().getResourceAsStream("/images/crafting_table.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try{
            hearts[0] = ImageIO.read(getClass().getResourceAsStream("/images/heart.png"));
            hearts[1] = ImageIO.read(getClass().getResourceAsStream("/images/heart_half.png"));
            hearts[2] = ImageIO.read(getClass().getResourceAsStream("/images/heart_empty.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static BufferedImage[] getBlocks(){
        return blocks;
    }
    public static BufferedImage[] getHearts(){
        return hearts;
    }
}
