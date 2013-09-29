package engine.physics;

public abstract class Particle{
    //Holds the velocity and accelerations of this particle
    protected Coordinate position,
                         velocity,
                         acceleration,
                         size;
    
    public Particle(){
        this(0d,0d);
    }
    public Particle(double x, double y){
        position        = new Coordinate(x,y);
        size            = new Coordinate(0d,0d);
        velocity        = new Coordinate(0d,0d);
        acceleration    = new Coordinate(0d,0d);
    }
    public Coordinate getPosition(){    return position;    }
    public Coordinate getVelocity(){    return velocity;    }
    public Coordinate getAcceleration(){return acceleration;}
    public Coordinate getSize(){        return size;        }
    public java.awt.Rectangle getRect(){
        return new java.awt.Rectangle(
            (int)position.getX(), (int)position.getY(),
            (int)size.getX(), (int)size.getY());
    }
}