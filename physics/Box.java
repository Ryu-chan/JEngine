package engine.physics;

import java.awt.Rectangle;
/**
 * The {@code Box} class holds particles and checks them against bounds 
 * either rendering them as wrap around or causing them to bounce.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.11
 */
public abstract class Box extends ParticleList{
    /** Holds the bounds of the box */
    protected Rectangle bounds;
    /**
     * First constructor takes a rectangle as the bounds.
     * @param bounds rectangle representing the bounds of the box.
     */
    public Box(Rectangle bounds){
        this.bounds = bounds;
    }
    /**
     * Second constructor takes the parts to form the box.
     * @param x position of the box.
     * @param y position of the box.
     * @param width dimension of the box.
     * @param height dimension of the box.
     */
    public Box(int x,int y,int width,int height){
        this(new Rectangle(x,y,width,height));
    }
    /**
     * Redefines the bounds of the box.
     * @param bounds rectangle representing the bounds of the box.
     */
    public void redefineBounds(Rectangle bounds){
        this.bounds = bounds;
    }
    /**
     * Redefines the bounds of the box.
     * @param x position of the box.
     * @param y position of the box.
     * @param width dimention of the box.
     * @param height dimension of the box.
     */
    public void redefineBounds(int x,int y,int width,int height){
        this.bounds = new Rectangle(x,y,width,height);
    }
    /**
     * Updates the particles' positions within the box.
     */
    public abstract void update();
}