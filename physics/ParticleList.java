package engine.physics;

import engine.data.structures.MutableArray;
/**
 * The {@code ParticleList} class holds many renderable items.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.13
 */
public class ParticleList extends MutableArray<Particle>{
    /**
     * Default constructor creates a list with an initial capacity of 32.
     */
    public ParticleList(){
        super();
    }
    /**
     * Second constructor creates a list with the specified initial capacity.
     * @param initialCapacity specifies size of original array.
     */
    public ParticleList(int initialCapacity){
        super(initialCapacity);
    }
}