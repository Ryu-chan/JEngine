package engine.physics;

/**
 * The {@Coordinate} class extends double data member to modify getters and setters.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.19
 */
public class Coordinate extends engine.util.DoubleDataMember<Double>{
    public Coordinate(){super();}
    public Coordinate(Double a,Double b){super(a,b);}
    
    public double getX(){
        return getA();
    }
    public double getY(){
        return getB();
    }
    public void setX(double x){
        setA(x);
    }
    public void setY(double y){
        setB(y);
    }
    public void setPosition(double x, double y){
        setA(x);setB(y);
    }
}
