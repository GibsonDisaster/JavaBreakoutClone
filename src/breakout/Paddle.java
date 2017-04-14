package breakout;

import java.awt.Rectangle;

public class Paddle {
    
    private int x, y, width, height, health;
    private boolean visible;

    public Paddle(int x, int y, int width, int height, boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
        this.health = 3;
    }
    
    public void move(String dir) {
        int speed = 10;
        
        switch(dir) {
            case "right":
                this.x += speed;
                break;
            case "left": 
                this.x -= speed;
                break;
        }
    }
    
    public Rectangle bounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    
    
    
    
    
    
    
    
    
    //Getters And Setters
    public int getHealth() {
        return this.health;
    }
    
    public void setHealth(int v) {
        this.health += v;
    }
    
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
