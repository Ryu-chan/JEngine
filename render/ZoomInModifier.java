package engine.render;

import engine.util.ImageModifier;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * The {@code ZoomInModifier} fades out an animation.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.21
 */
public class ZoomInModifier implements AnimationModifier{
    /**
     * The modifier for zooming in.
     * @param bim image to modify.
     * @param c completeness of the animation.
     * @return the modified image.
     */
    public BufferedImage modify(BufferedImage bim, float c){
        if(c>0){
            BufferedImage mod = ImageModifier.editScale(bim,c);
        
            BufferedImage copy = ImageModifier.getBIM(bim.getWidth(),bim.getHeight());
            Graphics2D g = ImageModifier.getHighGraphics(copy);
            g.drawImage(mod,
                bim.getWidth()/2 - mod.getWidth()/2,
                bim.getHeight()/2- mod.getHeight()/2,
                null);
            g.dispose();
            return copy;
        }
        return bim;
    }
}
