package engine.render;

import engine.util.ImageModifier;

import java.awt.image.BufferedImage;
/**
 * The {@code FadeInModifier} fades in an animation.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.21
 */
public class FadeInModifier implements AnimationModifier{
    /**
     * The modifier for fading in.
     * @param bim image to modify.
     * @param c completeness of the animation.
     * @return the modified image.
     */
    public BufferedImage modify(BufferedImage bim, float c){
        return ImageModifier.editTranslucency(bim,c);
    }
}