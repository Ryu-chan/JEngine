package engine.render;

import engine.util.ImageModifier;

import java.awt.image.BufferedImage;
/**
 * The {@code FadeOutModifier} fades out an animation.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.21
 */
public class FadeOutModifier implements AnimationModifier{
    /**
     * The modifier for fading out.
     * @param bim image to modify.
     * @param c completeness of the animation.
     * @return the modified image.
     */
    public BufferedImage modify(BufferedImage bim, float c){
        return ImageModifier.editTranslucency(bim,1.0f-c);
    }
}