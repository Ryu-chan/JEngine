package engine.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
/**
 * The {@code Hardware} class provides abstracted access to hardware 
 * components used for acceleration.
 */
public final class Hardware{
    static public GraphicsEnvironment getGraphicsEnvironment(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment();
    }
    static public GraphicsDevice getGraphicsDevice(){
        return getGraphicsEnvironment().getDefaultScreenDevice();
    }
    static public GraphicsConfiguration getGraphicsConfiguration(){
        return getGraphicsDevice().getDefaultConfiguration();
    }
}
