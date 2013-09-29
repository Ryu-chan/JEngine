package engine.physics;

import java.awt.Rectangle;
/**
 * The {@code OpenBox} class implements a box whose borders allow the 
 * particles to wrap around to the other side.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.11
 */
public class OpenBox extends Box{
    public OpenBox(Rectangle bounds){
        super(bounds);
    }
    public OpenBox(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    @Override
    public void update(){
        int x,y;
        Particle p;
        for(int i=0;i<size();i++){
            p= get(i);
            x=(int)p.getPosition().getX();y=(int)p.getPosition().getY();
            if(x<bounds.x)
                x=(int)(bounds.x+bounds.width-p.getSize().getX());
            else if(x+p.getSize().getX()>bounds.x+bounds.width)
                x=bounds.x;
            if(y<bounds.y)
                y=(int)(bounds.y+bounds.height-p.getSize().getY());
            else if(y+p.getSize().getY()>bounds.y+bounds.height)
                y=bounds.y;
            p.getPosition().setPosition(x,y);
        } 
    }
}