package engine.render;

import java.awt.image.BufferedImage;
/**
 * The {@code AnimationModifier} interface ensueres the methods modify returns a 
 * buffered image with the passed percentage completion.
 */
public interface AnimationModifier{
    /**
     * The modify method should take a buffered image and a percent completion and return 
     * the image modified by that amount of completion. Input to this method from the 
     * {@code Animator} class will be a float value between 0 and 1.
     * @param bim buffered image to modify.
     * @param c completeness of the modification.
     * @return the modified image with the specified completion.
     */
    public BufferedImage modify(BufferedImage bim, float c);
}
