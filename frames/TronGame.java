package engine.frames;

import engine.core.*;
import engine.events.*;
import engine.io.*;
import engine.physics.*;
import engine.render.*;
import engine.storage.*;
import engine.util.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class TronGame{
    static public final int SIZE = 10;
    private FSEMFrame frame;
    private VarientInputListener vil;
    private RenderTimer timer;
    private Animator anim;
    
    private boolean titleSeq = false;
    public TronGame(){
        makeImages();
        makeListener();
        makeFrame();
        makeTimer();
        makeTitleAnimation();
        //Remove cursor
        CursorModifier.setImageCursor(
            frame,ImageLibrary.getImage("cursor"));
        
        timer.start();
        frame.setVisible(true);
        try{Thread.sleep(300);}catch(Exception e){}
        frame.requestFocus();
    }
    private void makeTitleAnimation(){
        anim = new Animator(ImageLibrary.getImage("title"),160,
            new Executable(){
                @Override
                public void execute(){
                    titleSeq = true;
                }
            }
        );
        anim.addModifier(new FadeInModifier());
        anim.addModifier(new ZoomInModifier());
        anim.setCurrentFrame(60);
    }
    private void makeTimer(){
        timer = new RenderTimer(60){
            @Override public void pre_execution(){}
            @Override public void post_execution(){}
            @Override public Graphics2D getGraphics(){
                return frame.getGraphics();
            }
            @Override public void pre_render(Graphics2D g){
                g.drawImage(ImageLibrary.getImage("buffer"),0,0,null);
                //g.drawImage(ImageLibrary.getImage("title"),100,100,null);
                BufferedImage a = anim.next();
                g.drawImage(a,
                    frame.getWidth()/2-a.getWidth()/2,
                    frame.getHeight()/2-a.getHeight()/2-100,null);
                /*if(titleSeq){
                    g.setColor(Color.WHITE);
                    g.setFont(new Font(g.getFont().getFontName(),Font.BOLD,30));
                    g.drawString("Press ENTER to start!",
                        frame.getWidth()/2-
                        g.getFontMetrics().stringWidth("Press ENTER to start!")/2,
                        frame.getHeight()/2+a.getHeight()/2-100
                        
                        );
                }*/
                if(frame.shouldShow())
                    frame.showScreen();
            }
        };
    }
    private void makeFrame(){
        frame = new FSEMFrame();
        frame.setBackground(Color.BLACK);
        frame.addKeyListener(vil); 
        frame.setFocusable(true);
        //add buffer here cuase now we have a frame
        ImageLibrary.add("buffer",ImageMaker.makeRect(
            frame.getWidth(),frame.getHeight(),frame.getBackground()));
    }
    private void makeListener(){
        vil = new VarientInputListener(){
            @Override
            public void key_pressed(java.awt.event.KeyEvent e){
                if(e.getKeyChar()==java.awt.event.KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        };
    }
    private void makeImages(){
            try{
        ImageLoader.loadImage("title","tron.jpg");
        ImageLoader.loadImage("cursor","cursor.png");
            }catch(Exception e){System.out.println("File not found");}
        
        ImageLibrary.add(
            "bluebody",
            ImageMaker.makeRect(SIZE,SIZE,new Color(100,149,237)));
        ImageLibrary.add(
            "bluehead",
            ImageMaker.makeRect(SIZE,SIZE,new Color(25 ,25 ,112)));
        ImageLibrary.add(
            "redbody",
            ImageMaker.makeRect(SIZE,SIZE,new Color(223, 82, 82)));
        ImageLibrary.add(
            "redhead",
            ImageMaker.makeRect(SIZE,SIZE,new Color(216, 43, 43)));
    }
}