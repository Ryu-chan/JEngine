package engine.render;

import engine.util.ImageModifier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class ImageMaker{
    static public BufferedImage makeRect(int w,int h,Color c){
        BufferedImage copy = ImageModifier.getBIM(w,h);
        Graphics g = copy.getGraphics();
        g.setColor(c);
        g.fillRect(0,0,w,h);
        g.dispose();
        return copy;
    }
    static public BufferedImage makeOval(int w,int h,Color c){
        BufferedImage copy = ImageModifier.getBIM(w,h);
        Graphics g = copy.getGraphics();
        g.setColor(c);
        g.fillOval(0,0,w,h);
        g.dispose();
        return copy;
    }
}