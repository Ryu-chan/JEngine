package engine.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
public class CursorModifier{
    static public void hideCursor(JComponent comp){
        comp.setCursor(
            Toolkit.getDefaultToolkit().createCustomCursor(
            ImageModifier.getBIM(16,16), 
            new java.awt.Point(0,0),
            "blank cursor"));
    }
    static public void setDefaultCursor(JComponent comp){
        comp.setCursor(Cursor.getDefaultCursor());
    }
    static public void setImageCursor(JComponent comp, BufferedImage bim){
        comp.setCursor(
            Toolkit.getDefaultToolkit().createCustomCursor(
            bim,
            new java.awt.Point(0,0),
            "custom image"));
    }
    static public void hideCursor(Component comp){
        comp.setCursor(
            Toolkit.getDefaultToolkit().createCustomCursor(
            ImageModifier.getBIM(16,16), 
            new java.awt.Point(0,0),
            "blank cursor"));
    }
    static public void setDefaultCursor(Component comp){
        comp.setCursor(Cursor.getDefaultCursor());
    }
    static public void setImageCursor(Component comp, BufferedImage bim){
        comp.setCursor(
            Toolkit.getDefaultToolkit().createCustomCursor(
            bim,
            new java.awt.Point(0,0),
            "custom image"));
    }
}