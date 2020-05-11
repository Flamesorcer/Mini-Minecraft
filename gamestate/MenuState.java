package gamestate;
import main.GamePanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import resources.Images;
public class MenuState extends GameState
{
    
    private String[] options = {"Start", "Help", "Levels", "Quit"};
    private int currentSelection = 0;
    
    public MenuState(GameStateManager gsm){
        super(gsm);
        
    }
    public void init() {

    }
    public void tick(){
        
    }
    public void draw(Graphics g){
        g.setColor(new Color(128, 176, 255));
        g.drawImage(Images.getMenu()[0], 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
        
        // for (int i = 0; i < options.length; i++) {
        //     if (i == currentSelection) { 
        //         g.setColor(Color.GREEN);
        //     }
        //     else {
        //         g.setColor(Color.BLACK);
        //     }
        //     //g.drawLine(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT);
        //     g.setFont(new Font("Ariel", Font.PLAIN, 72));
        //     g.drawString(options[i], GamePanel.WIDTH / 2 - 75, 100 + i * 120);
        // }
        if (currentSelection == 0){
            g.drawImage(Images.getMenu()[5], GamePanel.WIDTH / 2 - 150, 40, 342, 84, null);
        }
        else{
            g.drawImage(Images.getMenu()[1], GamePanel.WIDTH / 2 - 150, 40, 342, 84, null);
        }
        if (currentSelection == 1){
            g.drawImage(Images.getMenu()[6], GamePanel.WIDTH / 2 - 150, 160, 342, 84, null);
        }
        else{
            g.drawImage(Images.getMenu()[2], GamePanel.WIDTH / 2 - 150, 160, 342, 84, null);
        }
        if (currentSelection == 2){
            g.drawImage(Images.getMenu()[7], GamePanel.WIDTH / 2 - 150, 280, 342, 84, null);
        }
        else{
            g.drawImage(Images.getMenu()[3], GamePanel.WIDTH / 2 - 150, 280, 342, 84, null);
        }
        if (currentSelection == 3){
            g.drawImage(Images.getMenu()[8], GamePanel.WIDTH / 2 - 150, 400, 342, 84, null);
        }
        else{
            g.drawImage(Images.getMenu()[4], GamePanel.WIDTH / 2 - 150, 400, 342, 84, null);
        }
        }
        
        
        
        
    
    public void keyPressed(int k){
        if(k == KeyEvent.VK_S){
            currentSelection++;
            if(currentSelection >= options.length){
                currentSelection = 0;
            }
        }
        else if(k == KeyEvent.VK_W){
            currentSelection--;
            if (currentSelection < 0){
                currentSelection = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_ENTER){
            if (currentSelection == 0){
                gsm.states.push(new Level1State(gsm));
            }
            else if (currentSelection == 1){
                gsm.states.push(new HelpState(gsm));
            }
            else if (currentSelection == 2){
                gsm.states.push(new LevelSelect(gsm));
            }
            else if (currentSelection == 3){
                System.exit(0);
            }
        }
    }
    public void keyReleased(int k){
        
    }
    public void nextLevel(){
        
    }
    public void repeatLevel(Graphics g){
        
    }
}
