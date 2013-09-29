package engine.core;

/**
 * {@code ExecutableStructure} interface ensures implementation of 
 * execute and executeAll methods.
 * @author Christopher Kelley
 * @version 1.0
 * @since 2013.03.08
 */
public interface ExecutableStructure{
    /**
     * Execute one executable.
     */
    void execute();
    /**
     * Execute all executables in the structure.
     */
    void executeAll();
}