package objects;


import java.awt.Rectangle;
import java.awt.Graphics;
import gamestate.GameState;
import java.awt.Color;
import resources.Images;
/**
 * Write a description of class Block here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Block extends Rectangle
{
    private static final long serialVersionUID = 1L;
    public static final int blockSize = 80;
    private int id;
    public Block(int x, int y, int width, int height){
        setBounds(x, y, width, height);
    }
    public Block(int x, int y, int id){
        
        setBounds(x, y, blockSize, blockSize);
        if (id == 5 || id == 6){
            setBounds(x, y + blockSize/10, width, height - blockSize/10);
        }
        this.id = id;
    }
    public void tick(){
        
    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        if(id >=5 && id <= 6){
            //g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height)
            g.drawImage(Images.getBlocks()[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
            
        }  
        else if(id >0){
            //g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
            g.drawImage(Images.getBlocks()[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
        }   
        
    }
    public static int blockSize(){
        return blockSize;
    }
    //Getters and Setters
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return id;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getwidth(){
        return (int)width;
    }
    public int getheight(){
        return (int)height;
    }
}
