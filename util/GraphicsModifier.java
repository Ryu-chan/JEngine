package engine.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
/**
 * The {@class GraphicsModifier} class acts as a wrapper for more 
 * complex graphics manipulation features.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.20
 */
public final class GraphicsModifier{
    /**
     * Sets the graphics member to antialias.
     * @param g graphics member to work with.
     */
    static public void antialias(Graphics2D g){
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    }
    /**
     * Sets the graphics member to interpolate.
     * @param g graphics member to work with.
     */
    static public void interpolate(Graphics2D g){
        g.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

}