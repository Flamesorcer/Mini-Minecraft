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
public class Level5State extends GameState
{
    
    private Player player;
    private ArrayList<Block> b;
    private Map map;
    private boolean why = false;
    private boolean whyy = false;
    public Level5State(GameStateManager gsm){
        super(gsm);
        
    }
    public void init(){
        player = new Player(30, 150, this);
        xOffset = -200;
        yOffset = 300;
        map = new Map("/Maps/map5.map");
        
        
    }
    public  void tick(){
        
        player.tick(map.getBlocks());
    }
    public  void draw(Graphics g){
        Color sky = new Color(19, 27, 41);
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
        g.drawString("Level 5", GamePanel.WIDTH - 75, 30);
        if (why){
            
            Color death = new Color(194, 81, 85, 150);
            g.setColor(death);
            g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", Font.PLAIN, 72));
            g.drawString("You died!", GamePanel.WIDTH / 2 - 140, 300);
            g.setFont(new Font("Ariel", Font.PLAIN, 32));
            g.drawString("Respawning", GamePanel.WIDTH / 2 - 60, 370);
            if(whyy){
                try {
                
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                gsm.states.push(new Level5State(gsm));
            }
            else{
                whyy = true;
            }
        }
    }
    public void nextLevel(){
        gsm.states.push(new Level1State(gsm));
    }
    public  void keyPressed(int k){
        if (k == KeyEvent.VK_ESCAPE) gsm.states.push(new MenuState(gsm));
        player.keyPressed(k);
    }
    public  void keyReleased(int k){
        player.keyReleased(k);
    }
    public void repeatLevel(Graphics g){
        why = true;
    }
}
