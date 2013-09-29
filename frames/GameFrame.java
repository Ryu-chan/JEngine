package engine.frames;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
/**
 * {@code GameFrame} class has a game panel and controls windowing
 * aspects.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.09
 */
public class GameFrame extends JFrame{
    private GamePanel panel;
    public GameFrame(GamePanel gp){
        this.panel = gp;
        addWindowListener(new WindowListener(){
            @Override
            public void windowActivated(WindowEvent e){
                panel.resume();
            }
            @Override
            public void windowDeactivated(WindowEvent e){
                panel.pause();
            }
            @Override
            public void windowDeiconified(WindowEvent e){
                panel.resume();
            }
            @Override
            public void windowIconified(WindowEvent e){
                panel.pause();
            }
            @Override
            public void windowClosing(WindowEvent e){
                panel.stop();
            }
            @Override
            public void windowClosed(WindowEvent e){}
            @Override
            public void windowOpened(WindowEvent e){}
        });
        add(panel);
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocus();
    }
    public GamePanel accessPanel(){
        return panel;
    }
}