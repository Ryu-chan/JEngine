package engine.render;

import engine.core.Executable;
import engine.core.Timer;
import engine.data.structures.MutableArray;

import java.awt.Graphics2D;
/**
 * The {@code RenderTimer} class handles rendering particles based on the given 
 * frames per second.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.20
 */
public abstract class RenderTimer extends Timer{
    //holds the items to be rendered
    private MutableArray<Renderable>items;
    /**
     * Sole constructor for this timer takes a frames per second mark to hit.
     * @param fps frames per second to attempt to hit.
     */
    public RenderTimer(int fps){
        super((long)(1000d/fps*1000000L));
        items = new MutableArray<Renderable>();
        this.getPrimaryChain().add(new Executable(){
            @Override
            public void execute(){
                RenderTimer.this.update();
                RenderTimer.this.render();
            }
        });
        this.getInsuredChain().add(new Executable(){
            @Override
            public void execute(){
                RenderTimer.this.update();
            }
        });
    }
    /**
     * Adds the specified item to the list to be rendered.
     * @param item renderable item to add to the list.
     */
    public void add(Renderable item){
        items.add(item);
    }
    /**
     * Removes the specified item from the list to be rendered.
     * @param item renderable item to be removed from the list.
     */
    public void remove(Renderable item){
        items.remove(item);
    }
    /**
     * Accesses the entire items list for more control.
     * @return the entire mutable array of renderable items.
     */
    public MutableArray<Renderable> getItems(){
        return items;
    }
    //updates all internal renderable items in the list
    private void update(){
        for(int i=0;i<items.size();i++)
            items.get(i).update();
    }
    //renders all internal renderable items in the list
    //with the graphics obtainted through the abstract class
    public void render(){
        Graphics2D g = getGraphics();
        this.pre_render(g);
        for(int i=0;i<items.size();i++)
            items.get(i).render(g);
    }
    /**
     * Obtains the graphics context with which to render the items.
     * @return a graphics2d context with which to render the items.
     */
    public abstract Graphics2D getGraphics();
    /**
     * Renders something before the particles.
     * @param g graphics context to use.
     */
    public abstract void pre_render(Graphics2D g);
}