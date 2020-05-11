package resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images
{
    private static BufferedImage[] blocks;
    private static BufferedImage[] hearts;
    private static BufferedImage[] menu;
    public Images() {
        blocks = new BufferedImage[12];
        hearts = new BufferedImage[3];
        menu = new BufferedImage[9];
        try{
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/images/grass.jpg"));
            blocks[1] = ImageIO.read(getClass().getResourceAsStream("/images/dirt.jpg"));
            blocks[2] = ImageIO.read(getClass().getResourceAsStream("/images/stone.png"));
            blocks[3] = ImageIO.read(getClass().getResourceAsStream("/images/water.png"));
            blocks[4] = ImageIO.read(getClass().getResourceAsStream("/images/water_top.png"));
            blocks[5] = ImageIO.read(getClass().getResourceAsStream("/images/lava_top.png"));
            blocks[6] = ImageIO.read(getClass().getResourceAsStream("/images/lava.png"));
            blocks[7] = ImageIO.read(getClass().getResourceAsStream("/images/wood.png"));
            blocks[8] = ImageIO.read(getClass().getResourceAsStream("/images/cobble.png"));
            blocks[9] = ImageIO.read(getClass().getResourceAsStream("/images/crafting_table.png"));
            blocks[10] = ImageIO.read(getClass().getResourceAsStream("/images/netherrack.png"));
            blocks[11] = ImageIO.read(getClass().getResourceAsStream("/images/obsidian.png"));
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
        try{
            menu[0] = ImageIO.read(getClass().getResourceAsStream("/images/background.png"));
            menu[1] = ImageIO.read(getClass().getResourceAsStream("/images/start_notSelected.png"));
            menu[2] = ImageIO.read(getClass().getResourceAsStream("/images/help_notSelected.png"));
            menu[3] = ImageIO.read(getClass().getResourceAsStream("/images/levels_notSelected.png"));
            menu[4] = ImageIO.read(getClass().getResourceAsStream("/images/quit_notSelected.png"));
            menu[5] = ImageIO.read(getClass().getResourceAsStream("/images/start_Selected.png"));
            menu[6] = ImageIO.read(getClass().getResourceAsStream("/images/help_Selected.png"));
            menu[7] = ImageIO.read(getClass().getResourceAsStream("/images/levels_Selected.png"));
            menu[8] = ImageIO.read(getClass().getResourceAsStream("/images/quit_Selected.png"));



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
    public static BufferedImage[] getMenu(){
        return menu;
    }
}
