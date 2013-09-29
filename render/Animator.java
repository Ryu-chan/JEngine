package engine.render;

import engine.core.Executable;
import engine.data.structures.MutableArray;
import engine.util.ImageModifier;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * The {@code Animator} class allows a single image to be animated over time with 
 * specified animation modifiers. A function may be given in the constructior in the 
 * form of an executable (found in the engine core) which will execute when the 
 * animation has finished. Otherwise a call can be made to the function hasNext 
 * to see if the animation has finished.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.21
 */
public class Animator{
    //Holds the modifiers for animation
    private MutableArray<AnimationModifier>mods;
    //Holds the original image for animating
    private BufferedImage buffer;
    //Holds the current time tick.
    private int  cnt,
    //Holds the full duration
                duration;
    //Holds the executable to execute on finish
    private Executable ondone;
    /**
     * Constructor takes an image to animate and the duration over which to animate.
     * @param bim buffered image to animate.
     * @param duration how long should the animation last.
     */
    public Animator(BufferedImage bim, int duration){
        buffer = bim;
        this.duration = duration;
        mods = new MutableArray<AnimationModifier>(8);
    }
    /**
     * Constructor takes the image and duration for animation as well as an executable 
     * to activate once the animation has finished.
     * @param bim buffered image to animate.
     * @param duration how long should the animation last.
     * @param exec executable to execute.
     */
    public Animator(BufferedImage bim, int duration, Executable exec){
        this(bim,duration);
        ondone = exec;
    }
    /**
     * This method returns the current phase in animation of the image based on 
     * the set animation modifiers unless the animation has finished at which time 
     * it will continue to return the finished animation.  For efficiency the hasNext 
     * method should be check before every call to this method and the instantiation 
     * of this class disposed of if the animation has finished.
     * @return the image in its current state depending on where the animation process is.
     */
    public BufferedImage next(){
        BufferedImage copy = null;
        copy = getFrame(cnt);
        if(cnt<duration) cnt++;
        if(cnt==duration&&ondone!=null) ondone.execute();
        return copy;
    }
    /**
     * Gets if the animation has another frame or is finished.
     * @return what the description says.
     */
    public boolean hasNext(){
        return cnt<duration;
    }
    /**
     * Get a particular frame.
     * @param f the frame to get (must be between 0 and duration).
     */
    public BufferedImage getFrame(int f){
        BufferedImage copy = ImageModifier.compatibleBIM(
                    ImageModifier.getBIM(buffer.getWidth(),buffer.getHeight()) );
        if(f<0)f=0;
        if(f>duration)f=duration;
        copy.createGraphics().drawImage(buffer,0,0,null);
        if(!mods.isEmpty())
            copy = mods.get(0).modify(buffer, ((float)cnt)/duration);
        for(int i=1;i<mods.size();i++)
            copy = mods.get(i).modify(copy, ((float)cnt)/duration);
        return copy;
    }
    /**
     * Sets the frame to the specified value (between 0 and duration).
     * @param f frame to set to.
     */
    public void setCurrentFrame(int f){
        if(f<0)f=0;
        if(f>duration)f=duration;
        cnt = f;
    }
    /**
     * Get the current frame.
     * @return the current frame.
     */
    public int getCurrentFrame(){
        return cnt;
    }
    /**
     * Get the duration.
     * @return the duration of this animation.
     */
    public int getDuration(){
        return duration;
    }
    /**
     * Add modifiers to the animator.
     * @param mod modifier (or modifiers) to add to this animator.
     */
    public void addModifier(AnimationModifier... mod){
        for(AnimationModifier m:mod)
            mods.add(m);
    }
    /**
     * Remove modifiers from the animator.
     * @param mod modifier (or modifiers) to be removed from the list.
     */
    public void removeModifier(AnimationModifier... mod){
        for(AnimationModifier m:mod)
            mods.remove(m);
    }
}