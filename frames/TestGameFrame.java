package engine.frames;

import engine.events.VarientInputListener;
import java.awt.event.KeyEvent;

//import engine.render.RenderableActiveAnimated;
//import engine.render.AnimationRing;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import engine.physics.ClosedBox;
import engine.physics.Particle;

class TestGameFrame{
    //static int x=100,y=100;
    //static int modx=5,mody=5;
    static int speed = 5;
    static final int WIDTH=400,HEIGHT=300;
    static public void main(String[]args){
        VarientInputListener vip = new VarientInputListener(){
            @Override
            public void key_pressed(KeyEvent e){
                System.out.println("key pressed");
            }
            @Override
            public void key_released(KeyEvent e){
                System.out.println("key released");
            }
            @Override
            public void key_typed(KeyEvent e){
                System.out.println("key typed");
                toggleFlag("pause");
            }
        };
        
        vip.addFlag("pause",false);
        vip.applyFlag("pause",
            VarientInputListener.KEY_PRESSED +
            VarientInputListener.KEY_RELEASED);
        
        /*BufferedImage[] imgs = new BufferedImage[30];
        Graphics g;
        for(int i=0;i<30;i++){
            imgs[i] = new BufferedImage(30,30,BufferedImage.TYPE_INT_ARGB);
            g = imgs[i].getGraphics();g.setColor(java.awt.Color.BLACK);
            g.fillOval(30-i,30-i,i,i);
            g.dispose();
        }*/
        /*
        final RenderableActiveAnimated raa = new RenderableActiveAnimated(100,100,new AnimationRing(10,imgs)){
            public void update(){}
        };*/
        final Particle p = new Particle(100,100,10,10){};
        final ClosedBox box = new ClosedBox(50,50,300,250);
        box.add(p);
        
        GameFrame f = new GameFrame(new GamePanel(60){
            public void update(){
                //x+=modx;y+=mody;
                /*raa.move(modx,mody);
                if(raa.getX()<0||raa.getX()>TestGameFrame.WIDTH)
                    modx*=-1;
                if(raa.getY()<0||raa.getY()>TestGameFrame.HEIGHT)
                    mody*=-1;*/
                p.move(speed*p.getXMod(),speed*p.getYMod());
                box.update();
            }
            public void render(java.awt.Graphics g){
                g.setColor(java.awt.Color.BLUE);
                g.fillRect(50,50,300,250);
                g.setColor(java.awt.Color.WHITE);
                g.fillOval((int)p.getX(),(int)p.getY(),(int)p.getWidth(),(int)p.getHeight());
                
            }
        });
        f.accessPanel().setPreferredSize(
            new java.awt.Dimension(WIDTH,HEIGHT));
        f.addKeyListener(vip);
        f.pack();
        f.setVisible(true);
    }
}