package engine.physics;

import java.awt.Rectangle;
/**
 * The {@code ClosedBox} class implements a box whose borders won't allow 
 * the interior particles to pass.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.11
 */
public class ClosedBox extends Box{
    public ClosedBox(Rectangle bounds){
        super(bounds);
    }
    public ClosedBox(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    @Override
    public void update(){
        int x,y;
        Particle p;
        for(int i=0;i<size();i++){
            p=get(i);
            x=(int)p.getPosition().getX();y=(int)p.getPosition().getY();
            if(x<bounds.x){
                x=bounds.x;
                p.getVelocity().setX(p.getVelocity().getX()*-1);
            }else if(x+p.getSize().getX()>bounds.x+bounds.width){
                x=(int)(bounds.x+bounds.width-p.getSize().getX());
                p.getVelocity().setX(p.getVelocity().getX()*-1);
            }
            if(y<bounds.y){
                y=bounds.y;
                p.getVelocity().setY(p.getVelocity().getY()*-1);
            }else if(y+p.getSize().getY()>bounds.y+bounds.height){
                y=(int)(bounds.y+bounds.height-p.getSize().getY());
                p.getVelocity().setY(p.getVelocity().getY()*-1);
            }
            p.getPosition().setPosition(x,y);
        } 
    }
}