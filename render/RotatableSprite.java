package engine.render;

import engine.physics.Coordinate;
import engine.physics.Particle;
import engine.util.ImageModifier;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class RotatableSprite extends Particle implements Renderable{
    //Holds the rotation angle of this sprite
    private float rotation;
    //Rendering position
    private Coordinate renderpos;
    //Holds the sprite
    private BufferedImage sprite;
    //Rendering image
    private BufferedImage render;
    
    /**
     * First constructor takes a sprite and sets the rotation to 0 degress.
     * @param bim sprite to hold.
     */
    public RotatableSprite(BufferedImage bim){
        this(bim,0.0f);
    }
    /**
     * Second constructor takes a sprite and initial rotation in degress.
     * @param bim sprite to hold.
     * @param rot initial rotation of this sprite.
     */
    public RotatableSprite(BufferedImage bim, float rot){
        rotation = rot;
        sprite = bim;
        size.setPosition(bim.getWidth(),bim.getHeight());
        renderpos = new Coordinate();
        int square = (int)Math.sqrt( (size.getX()*size.getX()) + (size.getY()*size.getY()) );
        render = ImageModifier.getBIM(square,square);
    }
    
    public abstract void update();
    @Override
    public void render(Graphics2D g){
        Graphics2D g2 = ImageModifier.clear(render);
        AffineTransform affine = new AffineTransform();
        affine.setToTranslation( (render.getWidth() - sprite.getWidth() ) /2,
                                 (render.getHeight()-sprite.getHeight() ) /2);
        affine.rotate(Math.toRadians(rotation), sprite.getWidth()/2, sprite.getHeight()/2);
        g2.drawImage(sprite,affine,null);
        g2.dispose();
        g.drawImage(render,
            (int)( position.getX() - ( render.getWidth() - sprite.getWidth() ) ),
            (int)( position.getY() - ( render.getHeight()- sprite.getHeight()) ),
            null);
    }
}