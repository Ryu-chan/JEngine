package engine.io;

import engine.storage.ImageLibrary;
import engine.util.ImageModifier;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 * The {@code ImageLoader} class loads images into the image library.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.19
 */
public final class ImageLoader{
    //holds the images folder path
    static private String imgpath = "";
    /**
     * Loads the specified file into images library with the key as the file prefix.
     * @param file name of the image file to load.
     * @return if the image was succesfully loaded.
     */
    static public boolean loadImage(String file){
        return loadImage(
            file.substring(0, file.indexOf('.') ),
            file);
    }
    /**
     * Loads the specified file into images library with the specified key.
     * @param name key value to store the image under.
     * @param file name of the image file to load.
     * @return if the image was successfully loaded.
     */
    static public boolean loadImage(String name, String file){
        try{
            ImageLibrary.add(name,
                ImageModifier.compatibleBIM(
                    ImageIO.read(
                        new java.io.File(imgpath+file)
                    )
                ) 
            );
            return true;
        }catch(Exception ignored){
            // Captures read error - IOException
            // Captures duplicate key error - duplicate name/load
        }
        return false;
    }
    /**
     * Changes the path to the images folder.
     * @param path where the images folder is located.
     */
    static public void changeImagePath(String path){
        imgpath = path;
    }
}