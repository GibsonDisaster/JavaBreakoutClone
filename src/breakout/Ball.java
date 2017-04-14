package breakout;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
        
    private int x, y, width, height, score, lives, chance = 0;
    private float deltaX, deltaY;
    private boolean shot;

    public Ball(int x, int y, int width, int height, boolean shot) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shot = shot;
        this.deltaX = 5;
        this.deltaY = 5;
        this.score = 0;
    }
    
    public void move() {
        
        if (this.x > 800) {
            this.deltaX = -this.deltaX;
        } else if (this.x < 0) {
            this.deltaX = -this.deltaX;
        } else if (this.y > 600) {

        } else if (this.y < 0) {
            this.deltaY = -this.deltaY;
        }
        
        this.x += deltaX;
        this.y += deltaY;
    }
    
    public void collide(Paddle player) {
        
        if (this.x > player.getX() && this.x < player.getX() + player.getWidth()) {
            if (this.y+this.height > player.getY() && this.y < player.getY() + this.height) {
                this.deltaY = -this.deltaY;
            }
        }
        
    }
    
    public void collide(Brick brick, ArrayList<PowerUp> pups, int multi) {
        
        if (this.x > brick.getX() && this.x < brick.getX() + brick.getWidth()) {
            if (this.y+this.height > brick.getY() && this.y < brick.getY() + this.height) {
                if (chance % 2 == 0)
                    this.deltaY = -this.deltaY;
                if (chance % 2 != 0)
                    this.deltaX = -this.deltaX;
                brick.setVisible(false);
                score += 1 * multi;
                chance++;
                Random rand = new Random();
                int rnd = rand.nextInt(5 - 1 + 1) + 1;
                if (rnd < 5)
                    pups.add(new PowerUp(this.x, this.y, 10, 10, true));
            }
        }
        
    }
    //Getters And Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean visible) {
        this.shot = visible;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }
    
    
}
