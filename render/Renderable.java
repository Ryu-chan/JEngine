package engine.render;

/**
 * The {@code Renderable} interface ensures an update and a render method.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.13
 */
public interface Renderable{
    void update();
    void render(java.awt.Graphics2D g);
}