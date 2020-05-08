package entities;
import main.GamePanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Point;
import objects.Block;
import physics.Collision;
import gamestate.GameState;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
public class Player
{
    //Bounds
    private double hx, hy; 
    public int x;
    public int y;
    public int width;
    public int height;
    
    
    //Animation
    private boolean right = false, left = false;
    private boolean isAttacking;
    private int walkingCount;
    private int walkingRate;
    private boolean isWalking;
    private int walkingStatus;
    private boolean facingRight;
    //MoveSpeed
    private double moveSpeed;
    private double maxMoveSpeed = 2.5;
    
    //Falling or Jumping
    private boolean jumping;
    private boolean falling;
    private boolean rightCollision, leftCollision;
    private boolean onGround;
    private boolean running;
    //Jump Speed
    private double maxJumpSpeed = 6;
    private double jumpSpeed = maxJumpSpeed;
    private double currentJumpSpeed = jumpSpeed;
    //Fall Speed
    private double maxMaxFallSpeed = 10;
    private double maxFallSpeed = maxMaxFallSpeed;
    private double currentFallSpeed = 0.12;

    private double fallAccelleration;
    //Health
    private int health;
    //Water
    private boolean inWater;
    private double waterJump = jumpSpeed * .7;
    private double waterSpeed = maxMoveSpeed * .5;
    private double waterFall = maxMaxFallSpeed * .25;
    private boolean inLava;
    private int lavaDamage = 0;
    private GameState gs;
    public Player(int w, int h, GameState gs){
        hx = GamePanel.WIDTH/2;
        hy = GamePanel.HEIGHT/2;
        width = w;
        height = h;
        //Walking
        isWalking = false;
        walkingRate = 3;
        walkingCount = 50;
        walkingStatus = 0;
        facingRight = true;
        isAttacking = false;
        moveSpeed = maxMoveSpeed;
        jumping = false;
        falling = false;
        onGround = false;
        leftCollision = true;
        rightCollision = true;
        fallAccelleration = .2;
        running = false;
        inWater = false;
        health = 20;
        this.gs = gs;
    }

    public void tick(Block[][] b){
        onGround = false;
        x = (int)hx;
        y = (int)hy;
        inWater = false;
        inLava = false;
        //Collisions
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                if(b[i][j].getID() >= 1 && !(b[i][j].getID() >= 4 && b[i][j].getID() <= 7) && b[i][j].getID() != 10){
                    //right Collision
                    if(new Line2D.Float(x + 2 + width + (int)GameState.xOffset, y + 2+ (int)GameState.yOffset, 
                    x + 2 + width + (int)GameState.xOffset, y -1 +  height + (int)GameState.yOffset).intersects(b[i][j])){
                        rightCollision = false;

                    }
                    //Left Collision
                    if(new Line2D.Float(x -2 + (int)GameState.xOffset, y + 2+ (int)GameState.yOffset, 
                    x -2 + (int)GameState.xOffset, y -1 +  height + (int)GameState.yOffset).intersects(b[i][j])){
                        leftCollision = false;
                    }
                    //Top Collision
                    if(Collision.playerBlock(new Point(x +1 + (int)GameState.xOffset, y + 
                            (int)GameState.yOffset), b[i][j]) || Collision.playerBlock(new Point(x + width/2 + (int)GameState.xOffset, y + (int)GameState.yOffset), b[i][j]) ||
                    Collision.playerBlock(new Point(x -1 + width + (int)GameState.xOffset, y+ (int)GameState.yOffset), b[i][j])){
                        jumping = false;
                        falling = true;
                    }
                    //Bottom Collision
                    if(Collision.playerBlock(new Point(x + 3 + (int)GameState.xOffset, y +1 + height + 
                            (int)GameState.yOffset), b[i][j]) || Collision.playerBlock(new Point(x + width/2 + (int)GameState.xOffset, y + 1+ height + (int)GameState.yOffset), b[i][j]) ||
                    Collision.playerBlock(new Point(x -3 + width + (int)GameState.xOffset, y + height + 1 + (int)GameState.yOffset), b[i][j])){
                        hy = b[i][j].getY() - height - GameState.yOffset;
                        y = (int)hy;
                        falling = false;
                        if(currentJumpSpeed < jumpSpeed){
                            jumping = false;
                        }
                        onGround = true;

                        currentJumpSpeed = jumpSpeed;
                    }
                    else if(!onGround && !jumping){
                        falling = true;
                    }
                    
                }
                else if (b[i][j].getID() >= 4 && b[i][j].getID() <= 7){
                        if (new Rectangle2D.Float(x -2 + (int)GameState.xOffset, y + 2+ (int)GameState.yOffset,
                        width, height).intersects(b[i][j])){
                            inWater = true;
                            if(b[i][j].getID() == 6 || b[i][j].getID() == 7){
                                inLava = true;
                                
                            }
                        }
                    }
                else if(b[i][j].getID() == 10){
                    if (new Rectangle2D.Float(x -2 + (int)GameState.xOffset, y + 2+ (int)GameState.yOffset,
                        width, height).intersects(b[i][j])){
                            gs.nextLevel();
                        }
                }
            }
        }
        if(inWater){
            moveSpeed = waterSpeed;
            jumpSpeed = waterJump;
            maxFallSpeed = waterFall;
            if(currentFallSpeed > maxFallSpeed){
                currentFallSpeed = maxFallSpeed;
            }
        }
        else{
            moveSpeed = maxMoveSpeed;
            jumpSpeed = maxJumpSpeed;
            maxFallSpeed = maxMaxFallSpeed;
        }
        x = (int)hx;
        y = (int)hy;
        if(inLava){
            if(lavaDamage == 0){
                lavaDamage = 10;
                health --;
            }
            else{
                lavaDamage--;
            }
        }
        else{
            lavaDamage = 0;
        }
        
        //Right Movement
        if(right && rightCollision) {
            if (running) hx += 2* moveSpeed;
            else hx += 1.5 * moveSpeed;
            //GameState.xOffset += moveSpeed;
        }
        //Left Movement
        if(left && leftCollision) {
            if (running) hx -= 2* moveSpeed;
            else hx -= 1.5 * moveSpeed;
            //GameState.xOffset -= moveSpeed;
        }
        leftCollision = true;
        rightCollision = true;
        //Jumping and Falling
        if(jumping){
            hy -= currentJumpSpeed;
            currentJumpSpeed -= fallAccelleration;
            if(currentJumpSpeed <= 0){
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            hy += currentFallSpeed;
            if(currentFallSpeed < maxFallSpeed){
                currentFallSpeed += fallAccelleration;
            }
        }
        if(!falling){
            currentFallSpeed = fallAccelleration;
        }
        //For Animations
        if ((right && left)){
            isWalking = false;
        }
        else if (left){
            isWalking = true;
            facingRight = false;
        }
        else if(right){
            isWalking = true;
            facingRight = true;
        }
        else{
            isWalking = false;

        }
        if (isWalking){
            walkingCount -= walkingRate;
            if(walkingCount <= 0){
                walkingCount = 50;
                walkingStatus++;
            }
            if(walkingStatus >= 4){
                walkingStatus = 0;
            }
        }
        
        GameState.xOffset += (hx + width/2 - GamePanel.WIDTH/2)/15;
        hx -= (hx + width/2 - GamePanel.WIDTH/2)/15;
        GameState.yOffset += (hy + height/2 - GamePanel.HEIGHT/2)/15;
        hy -= (hy + height/2 - GamePanel.HEIGHT/2)/15;
        x = (int)hx;
        y = (int)hy;
        
    }

    public void draw(Graphics g){
        
        int wp = width/5;
        int hp = height/25;
        //Colors
        Color skin = new Color(137, 100, 83);
        Color hair = new Color(41, 29, 13);
        Color eye = new Color(60, 45, 100);
        Color sleve = new Color(0, 101, 101);
        // Color shirt = new Color(0, 128, 128);
        // Color handle = new Color(0, 101, 101);
        // Color guard = new Color(30, 138, 119);
        // Color blade = new Color(43, 200, 173);
        Color lpant = new Color(51, 42, 121);
        Color dpant = new Color(37, 30, 90);
        Color shoe = new Color(78, 78, 78);
        if(health <= 0){
            gs.repeatLevel(g);
        }
        //Drawing the Bottom
        if (walkingStatus == 0){
            g.setColor(lpant);
            g.fillRect(x+wp, y+14*hp, 3*wp, 10*hp);
            g.setColor(shoe);
            g.fillRect(x+wp, y+24*hp, 3*wp, 1*hp);
        }
        else if(walkingStatus == 1){

            g.setColor(dpant);
            g.fillRect(x+wp, y+17*hp, 4*wp, 3*hp);
            g.setColor(dpant);
            g.fillRect(x+3*wp, y+19*hp, 3*wp, 3*hp);
            g.setColor(dpant);
            g.fillRect(x+4*wp, y+21*hp, 3*wp, 3*hp);
            g.setColor(shoe);
            g.fillRect(x+4*wp, y+24*hp, 3*wp, 1*hp);

            g.setColor(lpant);
            g.fillRect(x+wp, y+15*hp, 3*wp, 4*hp);
            g.setColor(lpant);
            g.fillRect(x, y+17*hp, 3*wp, 3*hp);
            g.setColor(lpant);
            g.fillRect(x-wp, y+19*hp, 3*wp, 3*hp);
            g.setColor(lpant);
            g.fillRect(x-2*wp, y+21*hp, 3*wp, 3*hp);
            g.setColor(shoe);
            g.fillRect(x-2*wp, y+24*hp, 3*wp, 1*hp);
        }
        else if(walkingStatus == 2){
            g.setColor(lpant);
            g.fillRect(x+wp, y+14*hp, 3*wp, 10*hp);
            g.setColor(shoe);
            g.fillRect(x+wp, y+24*hp, 3*wp, 1*hp);
        }
        else{
            g.setColor(lpant);
            g.fillRect(x+wp, y+17*hp, 4*wp, 3*hp);
            g.setColor(lpant);
            g.fillRect(x+3*wp, y+19*hp, 3*wp, 3*hp);
            g.setColor(lpant);
            g.fillRect(x+4*wp, y+21*hp, 3*wp, 3*hp);
            g.setColor(shoe);
            g.fillRect(x+4*wp, y+24*hp, 3*wp, 1*hp);

            g.setColor(dpant);
            g.fillRect(x, y+17*hp, 3*wp, 3*hp);
            g.setColor(dpant);
            g.fillRect(x-wp, y+19*hp, 3*wp, 3*hp);
            g.setColor(dpant);
            g.fillRect(x-2*wp, y+21*hp, 3*wp, 3*hp);
            g.setColor(shoe);
            g.fillRect(x-2*wp, y+24*hp, 3*wp, 1*hp);

            g.setColor(lpant);
            g.fillRect(x+wp, y+15*hp, 3*wp, 4*hp);
        }
        //Drawing the Top
        //Facing Right
        if (facingRight){
            if (isAttacking){
                g.setColor(skin);
                g.fillRect(x, y, 5*wp, 5*hp);

            }
            else{
                //head
                g.setColor(skin);
                g.fillRect(x, y, 5*wp, 5*hp);
                g.setColor(eye);
                g.fillRect(x+4*wp, y+2*hp, wp, 1*hp);
                g.setColor(eye);
                g.fillRect(x+4*wp, y+2*hp, wp, 1*hp);
                g.setColor(hair);
                g.fillRect(x, y, 5*wp, hp);
                g.setColor(hair);
                g.fillRect(x, y, 3*wp, 2*hp);
                g.setColor(hair);
                g.fillRect(x, y, 2*wp, 3*hp);
                g.setColor(hair);
                g.fillRect(x, y, 1*wp, 4*hp);
                g.setColor(skin);
                g.fillRect(x+wp, y+5*hp, 3*wp, 1*hp);
                //Body
                g.setColor(sleve);
                g.fillRect(x+wp, y+6*hp, 3*wp, 3*hp);
                g.setColor(skin);
                g.fillRect(x+wp, y+9*hp, 3*wp, 6*hp);
            }
        }
        //Facing Left
        else{
            if (isAttacking){
                g.setColor(skin);
                g.fillRect(x, y, 5*wp, 5*hp);
            }
            else{
                //head
                g.setColor(skin);
                g.fillRect(x, y, 5*wp, 5*hp);
                g.setColor(eye);
                g.fillRect(x, y+2*wp, wp, hp);
                g.setColor(hair);
                g.fillRect(x, y, 5*wp, hp);
                g.setColor(hair);
                g.fillRect(x+2*wp, y+1*hp, 3*wp, 1*hp);
                g.setColor(hair);
                g.fillRect(x+3*wp, y+2*hp, 2*wp, 1*hp);
                g.setColor(hair);
                g.fillRect(x+4*wp, y+3*hp, 1*wp, 1*hp);
                g.setColor(skin);
                g.fillRect(x+wp, y+5*hp, 3*wp, 1*hp);
                //Body
                g.setColor(sleve);
                g.fillRect(x+wp, y+6*hp, 3*wp, 3*hp);
                g.setColor(skin);
                g.fillRect(x+wp, y+9*hp, 3*wp, 6*hp);
            }
        }
    }

    public void keyPressed(int k){
        if (k == KeyEvent.VK_D) right = true;
        if (k == KeyEvent.VK_A) left = true;
        if ((k == KeyEvent.VK_SPACE && !jumping && !falling)) jumping = true;
        else if( k == KeyEvent.VK_SPACE && inWater) {
            currentJumpSpeed = jumpSpeed;
            jumping = true;
            falling = false;
        }
        if (k == KeyEvent.VK_SHIFT) running = true;
        //if (k == KeyEvent.VK_S) isAttacking = true;
    }

    public void keyReleased(int k){
        if (k == KeyEvent.VK_D) right = false;
        if (k == KeyEvent.VK_A) left = false;
        if (k == KeyEvent.VK_SHIFT) running = false;
    }
    public int getHealth(){
        return health;
    }
}
