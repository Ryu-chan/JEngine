package engine.render;

import engine.util.ImageModifier;

import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * The {@code SpritePlayer} class holds a sprite sheet to animate 
 * a sprite.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.20
 */
public class SpriteSheet{
    //Holds the sprite sheet
    private BufferedImage sheet;
    //holds the breakdown of the sheet
    private int xstop, ystop;
    //holds the current position in the sheet
    private Point pos;
    public SpriteSheet(BufferedImage spriteSheet, int xsize, int ysize){
        sheet = spriteSheet;
        xstop = xsize;
        ystop = ysize;
    }
    public void step(){
        if(pos.x++>=sheet.getWidth()/xstop){
            pos.y++;
            pos.x=0;
        }
        if(pos.y>=sheet.getHeight()/ystop)
            pos.y=0;
    }
    public void setFrame(int x, int y){
        pos.move(
            x%sheet.getWidth()/xstop,
            y%sheet.getHeight()/ystop);
    }
    public BufferedImage getCurrentImage(){
        return ImageModifier.editCrop(
            sheet,sheet.getWidth()/xstop,sheet.getHeight()/ystop,
            xstop*pos.x,ystop*pos.y
        );
    }
    public boolean isDone(){
        return (pos.x+1)*xstop>=sheet.getWidth()
                &
               (pos.y+1)*ystop>=sheet.getHeight();
    }
}