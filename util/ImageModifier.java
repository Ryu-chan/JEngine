package engine.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
/**
 * The {@code ImageModifier} class provides implementation of image utilities.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.19
 */
public final class ImageModifier{
    /**
     * Gets a standard buffered image with the passed width and height.
     * @param width width of the new buffered image.
     * @param height height of the new buffered image.
     */
    static public BufferedImage getBIM(int width, int height){
        return new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    }
    /**
     * Creates a hardware acessible model image.
     * @param bim image to give over to hardware.
     */
    static public BufferedImage compatibleBIM(BufferedImage bim){
        int transparency = bim.getColorModel().getTransparency();
        BufferedImage copy = 
            Hardware.getGraphicsConfiguration()
            .createCompatibleImage(bim.getWidth(),bim.getHeight(),transparency);
        Graphics2D g = copy.createGraphics();
        g.drawImage(bim,0,0,null);
        g.dispose();
        return copy;
    }
    /**
     * Clears the passed buffered image.
     * @param bim buffered image to clear.
     * @return the graphics used to write on the image.
     */
    static public Graphics2D clear(BufferedImage bim){
        Graphics2D g = bim.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g.setColor(new java.awt.Color(0,0,0,0));
        g.fillRect(0,0,bim.getWidth(),bim.getHeight());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        return g;
    }
    /**
     * Convert a standard image format into buffered image for ease of access 
     * to the raster and color model of the buffered image.
     * @param im image to convert.
     * @param width width of the image to convert.
     * @param height height of the image to convert.
     * @return a buffered image mapped by the passed image. 
     */
    static public BufferedImage makeBIM(Image im, int width, int height){
        BufferedImage copy = getBIM(width,height);
        Graphics2D g = copy.createGraphics();
        g.drawImage(im,0,0,null);
        g.dispose();
        return copy;
    }
    /**
     * Change brightness of buffered image using ops.
     * @param bim buffered image to edit.
     * @param b brightness factor.
     */
    static public BufferedImage editBrightness(BufferedImage bim, float b){
        return new RescaleOp(b,0.0f,null).filter(bim,null);
    }
    /**
     * Change tint of buffered image using ops.
     * @param bim buffered image to edit.
     * @param t tint factor.
     */
    static public BufferedImage editTint(BufferedImage bim, float t){
        return new RescaleOp(1.0f,t,null).filter(bim,null);
    }
    /**
     * Change translucency of buffered image using graphics rendering.
     * @param bim buffered image to edit.
     * @param t translucency factor.
     */
    static public BufferedImage editTranslucency(BufferedImage bim, float t){
        BufferedImage copy = getBIM(bim.getWidth(),bim.getHeight());
        Graphics2D g = getHighGraphics(copy);
        g.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER,t));
        g.drawImage(bim,0,0,null);
        g.dispose();
        return copy;
    }
    /**
     * Crop the passed image.
     * @param bim image to crop.
     * @param width of the cropped image.
     * @param height of the cropped image.
     * @param xoffset how much x to offset the draw by.
     * @param yoffset how much y to offset the draw by.
     */
    static public BufferedImage editCrop(
        BufferedImage bim, int width, int height, int xoffset, int yoffset)
    {
        BufferedImage copy = getBIM(width,height);
        Graphics2D g = copy.createGraphics();
        g.drawImage(bim,-xoffset,-yoffset,null);
        g.dispose();
        return copy;
    }
    /**
     * Change scale of the buffered image using graphics rendering.
     * @param bim buffered image to edit.
     * @param s scale factor.
     */
    static public BufferedImage editScale(BufferedImage bim, float s){
        BufferedImage copy = getBIM((int)(bim.getWidth()*s),(int)(bim.getHeight()*s));
        Graphics2D g = getHighGraphics(copy);
        g.scale(s,s);
        g.drawImage(bim,0,0,null);
        g.dispose();
        return copy;
    }
    /**
     * Shapen the specified buffered image using ops.
     * @param bim buffered image to edit.
     */
    static public BufferedImage sharpen(BufferedImage bim){
        float[] sharpen = new float[] {
             0.0f, -1.0f, 0.0f,
            -1.0f, 5.0f, -1.0f,
             0.0f, -1.0f, 0.0f
        };
        return convolve(sharpen,bim);
    }
    /**
     * Blur the specified buffered image using ops.
     * @param bim buffered image to edit.
     */
    static public BufferedImage blur(BufferedImage bim){
        float frac = 1.0f/9.0f;
        float[] blur = new float[] {
             frac, frac, frac,
             frac, frac, frac,
             frac, frac, frac
        };
        return convolve(blur,bim);
    }
    /**
     * Blur the specified buffered image using ops.
     * @param bim buffered image to edit.
     * @param ksize size of the blur.
     */
    static public BufferedImage blur(BufferedImage bim, int ksize){
        float frac = 1.0f/ksize;
        float[]blur = new float[ksize*ksize];
        for(int i=0;i<ksize*ksize;i++)
            blur[i]=frac;
        return convolve(blur,bim);
    }
    /**
     * Edge the specified buffered image using ops.
     * @param bim buffered image to edit.
     */
    static public BufferedImage edge(BufferedImage bim){
        float[] edge = new float[] {
             0.0f, -1.0f, 0.0f,
            -1.0f, 4.0f, -1.0f,
             0.0f, -1.0f, 0.0f
        };
        return convolve(edge,bim);
    }
    //convolve helper
    static private BufferedImage convolve(float[]kern, BufferedImage bim){
        int ksize = (int)Math.sqrt(kern.length);
        BufferedImage copy = getBIM(
            bim.getWidth() + ksize - 1,
            bim.getHeight()+ ksize - 1);
        Graphics2D g = getHighGraphics(copy);
        g.drawImage(bim, (ksize -1)/2, (ksize -1)/2, null);
        g.dispose();
        
        Kernel kernel = new Kernel(ksize, ksize, kern);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(copy,null);
    }
    /**
     * Gets the graphics set up for high rendering from an image.
     * @param bim buffered image to get graphics from.
     */
    static public Graphics2D getHighGraphics(BufferedImage bim){
        Graphics2D g = bim.createGraphics();
        GraphicsModifier.antialias(g);
        GraphicsModifier.interpolate(g);
        return g;
    }
}