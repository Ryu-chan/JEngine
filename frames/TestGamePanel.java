package engine.frames;

import javax.swing.JFrame;
class TestGamePanel{
    static int i,j;
    static public void main(String[]args){
        GamePanel p = new GamePanel(60){
            @Override
            public void render(java.awt.Graphics g){
                g.setColor(java.awt.Color.BLACK);
                g.drawString("CNT: "+i+" : "+j++,100,100);
            }
            @Override
            public void update(){
                i++;
            }
        };
        p.setPreferredSize(new java.awt.Dimension(400,300));
        JFrame f = new JFrame();
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }
}