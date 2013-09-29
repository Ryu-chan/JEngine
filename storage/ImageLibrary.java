package engine.storage;

import java.awt.image.BufferedImage;
import java.util.HashMap;
public final class ImageLibrary{
    //holds the actual data structure hash of the library contents
    static private HashMap<String, BufferedImage>lib = new HashMap<String,BufferedImage>();
    /**
     * Gets the whole library.
     * @return the library reference as a hash map.
     */
    static public HashMap<String, BufferedImage> getLib(){return lib;}
    /**
     * Gets the image from the corresponding key value.
     * @param key how to search for the image.
     * @return the buffered image represented by the specified key.
     */
    static public BufferedImage getImage(String key){return lib.get(key);}
    /**
     * Adds the passed image to hte library with the passed key.
     * @param key how to later refer to this image from the library.
     * @param img the image to store under the passed key.
     */
    static public void add(String key,BufferedImage img){lib.put(key,img);}
    /**
     * Removes the image corresponding to the passed key.
     * @param key how to find the image in the library.
     * @return the removed image.
     */
    static public BufferedImage remove(String key){return lib.remove(key);}
}