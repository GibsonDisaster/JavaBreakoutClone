package breakout;

import java.awt.Rectangle;
import java.util.Random;
import org.newdawn.slick.Color;

public class PowerUp {

    private int x, y, width, height;
    private String power;
    private boolean visible;
    private Color color;
    
    public PowerUp(int x, int y, int w, int h, boolean visible) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.power = choosePower();
        this.color = setupColor();
    }
    
    public String choosePower() {
        String p = "null";
        Random rand = new Random();
        
        int rnd = rand.nextInt(6 - 1 + 1) + 1;
        
        switch(rnd) {
            case 1:
                p = "1+";
                break;
            case 2:
                p = "2x";
                break;
            case 3:
                p = "Ball";
                break;
            case 4:
                p = "Grabber";
                break;
            case 5:
                p = "life";
                break;
            case 6:
                p = "widen";
                break;
        }
        return p;
    }
    
    public void update() {
        this.y += 3;
    }
    
    public Color setupColor() {
        Color c = Color.white;
        
        switch(this.power) {
            case "1+":
                c = Color.gray;
                break;
            case "2x":
                c = Color.darkGray;
                break;
            case "Ball":
                c = Color.orange;
                break;
            case "Grabber":
                c = Color.red;
                break;
            case "life":
                c = Color.white;
                break;
            case "widen":
                c = Color.green;
                break;
        }
        return c;
    }
    
    public Rectangle bounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Getters and Setters
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
    
    public Color getColor() {
        return this.color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
