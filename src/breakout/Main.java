package breakout;

import Screens.LoseScreen;
import Screens.PlayScreen;
import Screens.StartScreen;
import Screens.WinScreen;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    
    //Declaring Screen ID's
    public static final int startScreen = 0;
    public static final int playScreen = 1;
    public static final int loseScreen = 2;
    public static final int winScreen = 3;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Main(String title) {
        super(title);
        this.addState(new StartScreen(startScreen));
        this.addState(new PlayScreen(playScreen));
        this.addState(new WinScreen(winScreen));
        this.addState(new LoseScreen(loseScreen));
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main("Breakout"));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //Initializing the Screens and entering the start menu
        this.getState(startScreen).init(gc, this);
        this.getState(winScreen).init(gc, this);
        this.getState(loseScreen).init(gc, this);
        this.enterState(startScreen);
    }
    
    //Getters and Setters
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
    
}
