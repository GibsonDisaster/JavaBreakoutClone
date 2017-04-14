package Screens;

import breakout.Ball;
import breakout.Brick;
import breakout.Paddle;
import breakout.PowerUp;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayScreen extends BasicGameState {
    
    private int multiplier;
    private Paddle player;
    private Ball ball;
    private boolean ableToGrab;
    private ArrayList<Brick> bricks;
    private ArrayList<Ball> balls;
    private ArrayList<PowerUp> powers;
    
    public PlayScreen(int playScreen) {
        player = new Paddle(320, 550, 80, 10, true);
        ball = new Ball(player.getX()+player.getWidth()/2-10, player.getY()-10, 10, 10, false);
        this.multiplier = 1;
        bricks = new ArrayList<>();
        balls = new ArrayList<>();
        powers = new ArrayList<>();
        balls.add(ball);
        setupBricks();
    }
    
    public void setupBricks() {
        //each row starts at an x value of 10, and then grows by 60 each time
        //to space out the bricks evenly
        int r1 = 10;
        int r2 = 10;
        int r3 = 10;
        int r4 = 10;
        
        for (int i = 0; i < 13; i++) {
            bricks.add(new Brick(r1, 10, 50, 30, true));
            r1 += 60;
        }
        
        for (int i = 0; i < 13; i++) {
            bricks.add(new Brick(r2, 50, 50, 30, true));
            r2 += 60;
        }
        
        for (int i = 0; i < 13; i++) {
            bricks.add(new Brick(r3, 90, 50, 30, true));
            r3 += 60;
        }
        
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Drawing player
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        
        //Drawing balls
        for (Ball ball : balls) {
            g.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        }
        
        //Drawing bricks
        for (Brick b : bricks) {
            if (b.isVisible())
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
        
        //Drawing powerups and setting appropriate colors for each
        for (PowerUp pup : powers) {
            g.setColor(pup.getColor());
            g.fillRect(pup.getX(), pup.getY(), pup.getWidth(), pup.getHeight());
            g.setColor(Color.white);
        }
        
        //Writing game info to the screen
        g.drawString("Score: " + Integer.toString(ball.getScore()), 20, 550);
        g.drawString("Lives: " + Integer.toString(player.getHealth()), 700, 550);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        
        //Movement and exiting
        if (input.isKeyPressed(input.KEY_ESCAPE))
            System.exit(0);
        else if (input.isKeyDown(input.KEY_RIGHT))
            player.move("right");
        else if (input.isKeyDown(input.KEY_LEFT))
            player.move("left");  
            
        //Shooting the ball
        if (input.isKeyPressed(input.KEY_SPACE)) {
            for (Ball b : balls) {
                b.setShot(true);
            }
        }
        
        //Updating the balls
        for (Ball ball : balls) {
            if (ball.isShot()) {
                ball.move();
                ball.collide(player);
                if (ball.getY() > 600) { //if the ball goes below the player, reset
                    player.setHealth(-1);
                    ball.setX(player.getX()+player.getWidth()/2-10);
                    ball.setY(player.getY() - 10);
                    ball.setShot(false);
                }
                
            } else if (ball.isShot() == false) {
                ball.setX(player.getX()+player.getWidth()/2-10);
                ball.setY(player.getY() - 10);
            }
            
            //Grabbing functionality, enables player to re-shoot ball;
            if (input.isKeyPressed(input.KEY_G)) {
                ball.setX(player.getX()+player.getWidth()/2-10);
                ball.setY(player.getY()-10);
                ball.setShot(false);
            }
        }
        
        //Drawing bricks
        for (Brick b : bricks) {
            if (b.isVisible()) {
                for (Ball ba : balls) {
                    ba.collide(b, powers, multiplier);
                }
            }
        }
        
        //Drawing powerups
        for (PowerUp pup : powers) {
            pup.update();
            if (collide(pup, player)) {
                applyPowerUp(pup.getPower(), player);
                pup.setX(3000);
            }
        }
        
        //Debugging and lose condition
        if (input.isKeyDown(input.KEY_W))
            win(sbg);
        else if (player.getHealth() <= 0)
            lose(sbg);
        
        System.out.println(ableToGrab);
    }
    
    public void lose(StateBasedGame sbg) {
        balls.clear();
        balls.add(new Ball(player.getX()+player.getWidth()/2-10, player.getY()-10, 10, 10, false));
        for (Brick b : bricks) {
            b.setVisible(true);
        }
        ball.setScore(0);
        player.setHealth(3);
        sbg.enterState(2);
    }
    
    public void win(StateBasedGame sbg) {
        balls.clear();
        balls.add(new Ball(player.getX()+player.getWidth()/2-10, player.getY()-10, 10, 10, false));
        for (Brick b : bricks) {
            b.setVisible(true);
        }
        ball.setScore(0);
        player.setHealth(3);
        sbg.enterState(3);
    }
    
    public boolean collide(PowerUp pup, Paddle player) {
        Rectangle rectUp = pup.bounds();
        Rectangle rectPlay = player.bounds();
        
        if (rectUp.intersects(rectPlay))
            return true;
        else
            return false;
    }
    
    public void applyPowerUp(String pwr, Paddle player) {
        switch(pwr) {
            case "1+":
                multiplier += 1;
                break;
            case "2x":
                multiplier *= 2;
                break;
            case "life":
                player.setHealth(1);
                break;
            case "widen":
                player.setWidth(player.getWidth()+50);
                break;
            case "Grabber":
                ableToGrab = true;
                break;
        }
    }

}
