package engine.frames;

import engine.core.Executable;
import engine.core.ExecutableChain;
import engine.core.Timer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
/**
 * {@code GamePanel} class has a basic rendering and is meant to 
 * speed up conversion from applets to windowed frames. This should 
 * not be used for full screen applications.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.09
 */
public abstract class GamePanel extends JPanel{
    static public final int ACTIVE = 0,
                            PAUSED = 1,
                            ENDED  = 2;
    private volatile int gamestate = ACTIVE;
    private volatile Graphics2D bg;
    private volatile BufferedImage bimg;
    //Timer for active rendering of this panel
    private Timer timer;
    
    public GamePanel(int fps){
        setBackground(Color.WHITE);
        
        timer = new Timer((long)1000d/fps*1000000L){
            @Override
            public void pre_execution(){
                preExecution();
            }
            @Override
            public void post_execution(){
                postExecution();
            }
        };
        timer.getPrimaryChain().add(
            new Executable(){
                @Override
                public void execute(){
                    update();
                    inner_render();
                    paint();
                }
            }
        );
        timer.getInsuredChain().add(
            new Executable(){
                @Override
                public void execute(){
                    update();
                }
            }
        );
        
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        timer.start();
    }
    public void inner_render(){
        if(getWidth()>0&&getHeight()>0){
            if(null==bimg){
                bimg = new BufferedImage(
                    this.getWidth(),this.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
                if(null==bimg)
                    throw new RuntimeException("malformed buffer");
                bg = (Graphics2D)bimg.getGraphics();
            }
            bg.setColor(getBackground());
            bg.fillRect(0,0,getWidth(),getHeight());
            render(bg);
        }
    }
    public void paint(){
        Graphics2D g;
        try{
            g = (Graphics2D)this.getGraphics();
            if(null!=g && null!=bimg)
                g.drawImage(bimg,0,0,null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }catch(Exception ex){
            //Display not caught
        }
    }
    public void pause(){
        gamestate = PAUSED;
    }
    public void resume(){
        gamestate = ACTIVE;
    }
    public void endgame(){
        gamestate = ENDED;
    }
    public void setGameState(int gs){
        gamestate = gs;
    }
    public synchronized int getGameState(){
        return gamestate;
    }
    public void stop(){
        timer.stop();
    }
    public abstract void update();
    public abstract void render(Graphics2D g);
    protected void preExecution(){}
    protected void postExecution(){
        System.exit(0);
    }
}