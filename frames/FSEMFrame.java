package engine.frames;

import java.awt.image.BufferStrategy;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import javax.swing.JFrame;
/**
 * The {@code FSEMFrame} class implements an FSEM Frame.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.13
 */
public class FSEMFrame extends JFrame{
    static private final int BUFFERS = 2;
    private GraphicsDevice gd;
    private Graphics2D gscr;
    private BufferStrategy bufferstrategy;
    public FSEMFrame(){
        initFS();
    }
    private void initFS(){
        gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        setUndecorated(true);
        setIgnoreRepaint(true);
        setResizable(false);
        
        //gd=null;
        if(!gd.isFullScreenSupported()){
            System.out.println("full screen not supported");
            initUFS();
            gd=null;
            return;
        }
        try{
            gd.setFullScreenWindow(this); //switch to fsem
        }catch(NullPointerException npe){
            System.out.println("null pointer - screen change");
            initUFS();
            gd=null;
            return;
        }
        //setdisplaymode here
        setBufferStrategy();
        
        initUFS();
    }
    private void setBufferStrategy(){
        try{
            java.awt.EventQueue.invokeAndWait(new Runnable(){
                @Override
                public void run(){
                    createBufferStrategy(BUFFERS);
                }
            });
        }catch(Exception e){
            System.out.println("buffer strategy not created");
            gd=null;
            initUFS();
        }
        try{Thread.sleep(500);//wait for buffer to catch up
        }catch(InterruptedException ignored){}
        bufferstrategy = getBufferStrategy();
    }
    private void initUFS(){
        java.awt.Dimension srcDims = 
            java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)srcDims.getWidth(),(int)srcDims.getHeight());
        setLocationRelativeTo(null);
    }
    @Override
    public Graphics2D getGraphics(){
        return (Graphics2D) ( (gd==null)?
            super.getGraphics():
            bufferstrategy.getDrawGraphics() );
    }
    public void showScreen(){
        if(gd!=null)
            bufferstrategy.show();
        java.awt.Toolkit.getDefaultToolkit().sync();
    }
    public boolean shouldShow(){
        return (gd!=null)? !bufferstrategy.contentsLost():true;
    }
    public void restore(){
        if(gd!=null){
            java.awt.Window w = gd.getFullScreenWindow();
            if(w!=null)
                w.dispose();
            gd.setFullScreenWindow(null);
        }
    }
    public boolean setDisplayMode(int width, int height, int bitDepth){
        if(null!=gd&&gd.isDisplayChangeSupported()){
            if(!isDisplayModeAvailable(width,height,bitDepth)){
                return false;
            }
            DisplayMode dm = 
                new DisplayMode(width,height,bitDepth,
                    DisplayMode.REFRESH_RATE_UNKNOWN);
            try{
                gd.setDisplayMode(dm);
                try{Thread.sleep(1000);
                }catch(InterruptedException ignored){}
            }catch(IllegalArgumentException iae){
                return false;
            }
            return true;
        }
        return false;
    }
    public DisplayMode[] getDisplayModes(){
        return (gd!=null) ? gd.getDisplayModes() : null;
    }
    public boolean isDisplayModeAvailable(int width, int height, int bitDepth){
        DisplayMode[] modes = gd.getDisplayModes();
        for(DisplayMode mode:modes){
            if(width    == mode.getWidth()  &&
               height   == mode.getHeight() &&
               bitDepth == mode.getBitDepth() )
                return true;
        }
        return false;
    }
}