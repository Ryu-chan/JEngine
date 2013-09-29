package engine.physics;

/**
 * {@code Obstacles} class defines a list of given particles 
 * for which collisions may be checked against.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.11
 */
public class Obstacles extends ParticleList{
    /**
     * Default constructor creates empty box.
     */
    public Obstacles(){
        super();
    }
    /**
     * Secondary constructor puts particles into the box.
     * @param initalCapacity capacity of the initial list.
     */
    public Obstacles(int initialCapacity){
        super(initialCapacity);
    }
    /**
     * Checks the specified particle with square approximation to the list.
     * @param part which particle to check against the list.
     * @return the item intersected or null.
     */
    public Particle checkSquareSquare(Particle part){
        for(int i=0;i<size();i++)
            if(!get(i).equals(part) && ((Particle)get(i)).getRect().intersects(part.getRect()))
                return (Particle)get(i);
        return null;
    }
    /**
     * Checks the specified particle with circular approximation to the list.
     * @param part which particle to check against the list.
     * @return the item intersected or null.
     */
    public Particle checkCircleCircle(Particle part){
        for(int i=0;i<size();i++){
            Particle p = (Particle)get(i);
            if(!p.equals(part)
                && (p.getSize().getX()+p.getSize().getY())/2+
                   (part.getSize().getX()+part.getSize().getY())/2
                   <
                   Math.sqrt(
                    Math.pow(p.getPosition().getX()+part.getPosition().getX(),2)+
                    Math.pow(p.getPosition().getY()+part.getPosition().getY(),2)))
                return (Particle)get(i);
        }
        return null;
    }
}