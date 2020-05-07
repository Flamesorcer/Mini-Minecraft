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
import resources.Images;
public class Level4State extends GameState
{
    
    private Player player;
    private ArrayList<Block> b;
    private Map map;
    public Level4State(GameStateManager gsm){
        super(gsm);
        
    }
    public void init(){
        player = new Player(30, 150, this);
        xOffset = -200;
        yOffset = -200;
        map = new Map("/Maps/map4.map");
        
        
    }
    public  void tick(){
        
        player.tick(map.getBlocks());
    }
    public  void draw(Graphics g){
        Color sky = new Color(34, 47, 70);
        g.setColor(sky);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
        player.draw(g);
        map.draw(g);
        int unHealth = player.getHealth();
        for (int i = 0; i < 10; i++){
            if(unHealth <= 0){
                g.drawImage(Images.getHearts()[2], 30 + 40 * i, 30, 30, 30, null);
            }
            else if(unHealth == 1){
                g.drawImage(Images.getHearts()[1], 30 + 40 * i, 25, 30, 30, null);
                unHealth -= 1;
            }
            else{
                g.drawImage(Images.getHearts()[0], 30 + 40 * i, 20, 30, 30, null);
                unHealth -= 2;
            }
        }
        g.setColor(Color.GRAY);
        g.setFont(new Font("Ariel", Font.PLAIN, 20));
        g.drawString("Level 4", GamePanel.WIDTH - 75, 30);
    }
    public void nextLevel(){
        gsm.states.push(new Level5State(gsm));
    }
    public  void keyPressed(int k){
        player.keyPressed(k);
    }
    public  void keyReleased(int k){
        player.keyReleased(k);
    }
}
