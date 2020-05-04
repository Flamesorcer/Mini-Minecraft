package physics;

import java.awt.Point;
import objects.Block;
import java.awt.Rectangle;
/**
 * Write a description of class Collision here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Collision
{
    public static boolean playerBlock(Point p, Block b){
        return b.contains(p);
    }
    public static boolean playerBlock(int x, int y, int width, int height, Block b){
        return b.contains(x, y, width, height);
    }
    public static boolean playerBlock(Rectangle r, Block b){
        return b.contains(r);
    }
}
