package gamestate;
import main.GamePanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import resources.Images;
/**
 * Write a description of class HelpState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HelpState extends GameState
{
    // instance variables - replace the example below with your own
    private String[] options = {"Controls:", "A - Move left", "D - Move right", "Shift - Sprint", 
        "Space - Jump and swim", "Esc - To go to Menu","", "Objective: Get to the crafting table", 
        "", "", "", "", "Press Enter to return to menu"};

    /**
     * Constructor for objects of class HelpState
     */
    public HelpState(GameStateManager gsm)
    {
        super(gsm);
        
    }
    public void init(){
        
    }
    public  void tick(){
        
        
    }
    public  void draw(Graphics g){
        g.setColor(new Color(128, 176, 255));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        
        for (int i = 0; i < options.length; i++) {
            g.setColor(Color.BLACK);
            
            //g.drawLine(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT);
            g.setFont(new Font("Ariel", Font.PLAIN, 32));
            g.drawString(options[i], GamePanel.WIDTH / 3 - 75, 100 + i * 30);
        }
        g.drawImage(Images.getBlocks()[9], 230, 330, 100, 100, null);
    }
    public  void keyPressed(int k){
        if(k == KeyEvent.VK_ENTER){
            gsm.states.pop();
        }
    }
    public  void keyReleased(int k){
        
    }
    public void nextLevel(){
        
    }
    public void repeatLevel(Graphics g){
        
    }
}
