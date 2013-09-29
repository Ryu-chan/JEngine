package engine.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * {@code VarientInputListener} class implements a method of determining 
 * whether an event should be cast beyond the specific event having been 
 * matched.  In this way each input event must also have all of its flags 
 * be on for the event to execute.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.09
 */
public class VarientInputListener 
    implements KeyListener, MouseListener, MouseMotionListener
{
    ///Constants to be used for mapping
    static public final int
        KEY_PRESSED     = 1,
        KEY_RELEASED    = 2,
        KEY_TYPED       = 4,
        MOUSE_ENTERED   = 8,
        MOUSE_EXITED    = 16,
        MOUSE_PRESSED   = 32,
        MOUSE_RELEASED  = 64,
        MOUSE_CLICKED   = 128,
        MOUSE_MOVED     = 256,
        MOUSE_DRAGGED   = 512,
        MAX             = 512;
    //Maps the flags to be applied 
    private HashMap<String,Boolean> flagmap;
    //Maps methods to flags
    private HashMap<Integer,ArrayList<String>> appmap;
    /**
     * Defualt constuctor sets up hash maps for tracking flags.
     */
    public VarientInputListener(){
        flagmap = new HashMap<String, Boolean>();
        appmap = new HashMap<Integer, ArrayList<String>>();
        for(int i=1;i<=MAX;i <<= 1)
            appmap.put(i,new ArrayList<String>());
    }
    /**
     * Adds a flag to the list of available flags.
     * @param flag to be added to the list.
     */
    public void addFlag(String flag){
        flagmap.put(flag,true);
    }
    /**
     * Adds a flag to the list of available flags and sets 
     * its initial value to the specified one.
     * @param flag to be added to the list.
     * @param value to initialize the flag with.
     */
    public void addFlag(String flag, boolean value){
        flagmap.put(flag,value);
    }
    /**
     * Toggles the specified flag's value.
     * @param flag which flag to toggle.
     */
    public void toggleFlag(String flag){
        flagmap.put(flag,!flagmap.remove(flag));
    }
    /**
     * Sets the specified flags value to the given value.
     * @param flag to be reset.
     * @param value to set the flag to.
     */
    public void setFlag(String flag, boolean value){
        flagmap.remove(flag);
        flagmap.put(flag,value);
    }
    /**
     * Applies the specified flag to the passed methods. 
     * The methods must be a combination of the constants defined 
     * within this class. The final value should be the addition 
     * of all desired methods.
     * @param flag to apply to the given methods.
     * @param methods addition of desired methods 
     *  to which to apply the flag.
     */
    public void applyFlag(String flag, int methods){
        if(!flagmap.containsKey(flag))
            addFlag(flag);
        for(int i=1;i<=MAX;i <<= 1)
            if( (methods & i) == i)
                appmap.get(i).add(flag);
    }
    /**
     * Removes the given flag from the passed methods. 
     * The methods must be a combination of the constants defined 
     * within this class. The final value should be the addition 
     * of all desired methods.
     * @param flag to remove from the given methods.
     * @param methods addition of desired methods from 
     *  which to remove the flag.
     */
    public void removeFlag(String flag, int methods){
        for(int i=1;i<=MAX;i <<= 1)
            if( (methods & i) == i)
                appmap.get(i).remove(flag);
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        for(String flag:appmap.get(KEY_PRESSED))
            if(!flagmap.get(flag))
                return;
        key_pressed(e);
    }
    public void key_pressed(KeyEvent e){}
    
    @Override
    public void keyReleased(KeyEvent e){
        for(String flag:appmap.get(KEY_RELEASED))
            if(!flagmap.get(flag))
                return;
        key_released(e);
    }
    public void key_released(KeyEvent e){}
    
    @Override
    public void keyTyped(KeyEvent e){
        for(String flag:appmap.get(KEY_TYPED))
            if(!flagmap.get(flag))
                return;
        key_typed(e);
    }
    public void key_typed(KeyEvent e){}
    
    @Override
    public void mouseEntered(MouseEvent e){
        for(String flag:appmap.get(MOUSE_ENTERED))
            if(!flagmap.get(flag))
                return;
        mouse_entered(e);
    }
    public void mouse_entered(MouseEvent e){}
    
    @Override
    public void mouseExited(MouseEvent e){
        for(String flag:appmap.get(MOUSE_EXITED))
            if(!flagmap.get(flag))
                return;
        mouse_exited(e);
    }
    public void mouse_exited(MouseEvent e){}
    
    @Override
    public void mousePressed(MouseEvent e){
        for(String flag:appmap.get(MOUSE_PRESSED))
            if(!flagmap.get(flag))
                return;
        mouse_pressed(e);
    }
    public void mouse_pressed(MouseEvent e){}
    
    @Override
    public void mouseReleased(MouseEvent e){
        for(String flag:appmap.get(MOUSE_RELEASED))
            if(!flagmap.get(flag))
                return;
        mouse_released(e);
    }
    public void mouse_released(MouseEvent e){}
    
    @Override
    public void mouseClicked(MouseEvent e){
        for(String flag:appmap.get(MOUSE_CLICKED))
            if(!flagmap.get(flag))
                return;
        mouse_clicked(e);
    }
    public void mouse_clicked(MouseEvent e){}
    
    @Override
    public void mouseMoved(MouseEvent e){
        for(String flag:appmap.get(MOUSE_MOVED))
            if(!flagmap.get(flag))
                return;
        mouse_moved(e);
    }
    public void mouse_moved(MouseEvent e){}
    
    @Override
    public void mouseDragged(MouseEvent e){
        for(String flag:appmap.get(MOUSE_DRAGGED))
            if(!flagmap.get(flag))
                return;
        mouse_dragged(e);
    }
    public void mouse_dragged(MouseEvent e){}
    
}