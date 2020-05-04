package gamestate;

import main.GamePanel;
import entities.Player;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import objects.Block;
import java.util.ArrayList;
import mapping.Map;

public class Level1State extends GameState
{
    
    private Player player;
    private ArrayList<Block> b;
    private Map map;
    public Level1State(GameStateManager gsm){
        super(gsm);
        
    }
    public void init(){
        player = new Player(30, 150);
        xOffset = -200;
        yOffset = -200;
        map = new Map("/Maps/map1.map");
        
        
    }
    public  void tick(){
        
        player.tick(map.getBlocks());
    }
    public  void draw(Graphics g){
        Color sky = new Color(176, 215, 255);
        g.setColor(sky);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
        player.draw(g);
        map.draw(g);
    }
    public  void keyPressed(int k){
        player.keyPressed(k);
    }
    public  void keyReleased(int k){
        player.keyReleased(k);
    }
}
