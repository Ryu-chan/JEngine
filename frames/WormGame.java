package engine.frames;

import engine.core.*;
import engine.events.*;
import engine.physics.*;
import engine.render.*;
import engine.storage.*;
import engine.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import javax.swing.JFrame;
public class WormGame{
    private static final int DOTSIZE = 20;
    private static int WORMLEN = 40;
    //private ArrayList<Renderable> worm;
    private BufferedImage wormpart;
    
    //private ArrayList<Renderable> obs;
    private BufferedImage obspart;
    int skip=5,wormskip=30;
    //private GameFrame f;
    public WormGame(){
        //store the worm part
        wormpart = new BufferedImage(DOTSIZE+3,DOTSIZE+3,
            BufferedImage.TYPE_INT_ARGB);
        Graphics g = wormpart.getGraphics();
        g.setColor(java.awt.Color.BLACK);
        g.fillOval(0,0,DOTSIZE,DOTSIZE);
        g.dispose();
        wormpart = ImageModifier.editTranslucency(wormpart,0.5f);
        ImageLibrary.add("wormpart",wormpart);
        //store the obs part
        obspart = new BufferedImage(DOTSIZE+3,DOTSIZE+3,
            BufferedImage.TYPE_INT_ARGB);
        g = obspart.getGraphics();
        g.setColor(java.awt.Color.BLUE);
        g.fillOval(4,4,DOTSIZE-8,DOTSIZE-8);
        g.dispose();
        obspart = ImageModifier.editTranslucency(obspart,0.2f);
        ImageLibrary.add("obspart",obspart);
        
        
        
        final FSEMFrame frame = new FSEMFrame();
        frame.setBackground(new java.awt.Color(200,185,136));
        
        
        //frame.setDisplayMode(800,600,32);
            
        frame.setDefaultCloseOperation(FSEMFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
            
        final Obstacles obstacles = new Obstacles();
            
        final OpenBox wormbox = 
            new OpenBox (0,0,frame.getWidth(),frame.getHeight());
            
        RenderableParticle p = new RenderableParticle(){
            @Override public void update(){}
            @Override public void render(Graphics2D g){
                g.drawImage(ImageLibrary.getImage("wormpart"),
                    (int)getPosition().getX(),
                    (int)getPosition().getY(),null);
            }
        };
        p.getPosition().setPosition(100,100);
        p.getSize().setPosition(DOTSIZE,DOTSIZE);
        
        wormbox.add(p);
        
        final Animator a = new Animator(ImageLibrary.getImage("wormpart"),30);
        System.out.println(a.getFrame(0));
        a.addModifier(new FadeInModifier());
        
        //final BufferedImage img = new BufferedImage(
        //    frame.getWidth(),frame.getHeight(),BufferedImage.TYPE_INT_ARGB);
        //final Graphics ig = img.getGraphics();
        final RenderTimer t = new RenderTimer(60){
            @Override public void pre_execution(){}
            @Override public void post_execution(){}
            @Override public Graphics2D getGraphics(){
                return frame.getGraphics();
            }
            @Override public void pre_render(Graphics2D g){
                g.setColor(frame.getBackground());
                g.fillRect(0,0,frame.getWidth(),frame.getHeight());
                g.setColor(java.awt.Color.BLACK);
                g.drawImage(a.next(),500,500,null);
                g.drawImage(ImageLibrary.getImage("wormpart"),400,400,null);
                System.out.println(a.getCurrentFrame());
                g.drawRect(100,100,100,100);
                //System.out.println(a.getCurrentFrame());
            }
        };
        t.getInsuredChain().add(new Executable(){
            @Override public void execute(){
                RenderableParticle p = new RenderableParticle(){
                    @Override public void update(){}
                    @Override public void render(Graphics2D g){
                        g.drawImage(ImageLibrary.getImage("wormpart"),
                            (int)getPosition().getX(),
                            (int)getPosition().getY(),null);
                    }
                };
                int newx=(int)(
                    (Math.random()*2-1)*(DOTSIZE-2)+
                    ((Particle)wormbox.get(wormbox.size()-1)).getPosition().getX());
                int newy=(int)(
                    (Math.random()*2-1)*(DOTSIZE-2)+
                    ((Particle)wormbox.get(wormbox.size()-1)).getPosition().getY());
                        
                p.getPosition().setPosition(newx,newy);
                p.getSize().setPosition(DOTSIZE,DOTSIZE);
                
                wormbox.add(p);
                t.add(p);
                
                if(wormbox.size()>=WORMLEN)
                    t.remove((Renderable)wormbox.remove(0));
                
                wormbox.update();   
                
                Particle op = obstacles.checkSquareSquare(wormbox.get(wormbox.size()-1));
                if(op!=null){
                    obstacles.remove(op);
                    t.remove((Renderable)op);
                    WORMLEN++;
                }
                //////
                if(frame.shouldShow())
                    frame.showScreen();
            }
        });
        frame.addMouseListener(new VarientInputListener(){
            @Override
            public void mouse_pressed(java.awt.event.MouseEvent e){
                //obstacles.add(new RenderableParticle(e.getX(),e.getY(),obspart));
                
                RenderableParticle p = new RenderableParticle(){
                    @Override public void update(){}
                    @Override public void render(Graphics2D g){
                        g.drawImage(ImageLibrary.getImage("obspart"),
                            (int)getPosition().getX(),
                            (int)getPosition().getY(),null);
                    }
                };
                        
                p.getPosition().setPosition(e.getX()-DOTSIZE/2,e.getY()-DOTSIZE/2);
                p.getSize().setPosition(DOTSIZE,DOTSIZE);
                
                obstacles.add(p);
                t.add(p);
            }
        });
        t.start();
        
    }
    
    static public void main(String[]args){
        WormGame wg = new WormGame();
    }
}