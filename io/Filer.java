package engine.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * The {@code Filer} class provides functions for file management.
 * 
 * @author Christopher Kelley
 * @version 1.0
 * @since 2012.10.30
 */
public final class Filer{
    //Which charset should be used for reading and writing
    static private Charset ENCODING = StandardCharsets.UTF_8;
    /**
     * Changes the default encoding to be used when no charset is provided.
     * @param encoding specifies the new default charset for encoding.
     */
    static public void changeDefaultEncoding(Charset encoding){
        ENCODING = encoding;
    }
    //READING TEXT FILES
    /**
     * Reads quickly a small text file with the default encoding - not recommended for larger files. 
     * Use {@link readLargeTextFile(String)} instead.
     * @param file name of the file to read from.
     * @return a list of strings where each string in the file was separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public List<String> readSmallTextFile(String file) throws IOException{
        Path path = Paths.get(file);
        return Files.readAllLines(path,ENCODING);
    }
    /**
     * Reads quickly a small text file with the specified encoding - not recommended for larger files. 
     * Use {@link readLargeTextFile(String,Charset)} instead.
     * @param file name of the file to read from.
     * @param encoding specifies what type of encoding to use when reading the file.
     * @return a list of strings where each string in the file was separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public List<String> readSmallTextFile(String file, Charset encoding) throws IOException{
        Path path = Paths.get(file);
        return Files.readAllLines(path,encoding);
    }
    /**
     * Reads large a text file with the default encoding.
     * @param file name of the file to read from.
     * @return a list of strings where each string in the file was separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public List<String> readLargeTextFile(String file) throws IOException{
        List<String> list = new ArrayList<>();
        Path path = Paths.get(file);
        try (Scanner scanner = new Scanner(path, ENCODING.name())){
            while(scanner.hasNextLine())
                list.add(scanner.nextLine());
        }
        return list;
    }
    /**
     * Reads large a text file with the specified encoding.
     * @param file name of the file to read from.
     * @param encoding specifies what type of encoding to use when reading the file.
     * @return a list of strings where each string in the file was separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public List<String> readLargeTextFile(String file, Charset encoding) throws IOException{
        List<String> list = new ArrayList<>();
        Path path = Paths.get(file);
        try (Scanner scanner = new Scanner(path, encoding.name())){
            while(scanner.hasNextLine())
                list.add(scanner.nextLine());
        }
        return list;
    }
    //WRITING TEXT FILES
    /**
     * Writes quickly to a small text file with the default encoding - not recommended for larger files. 
     * Use {@link writeLargeTextFile(String,List)} instead.
     * @param file name of the file to write to.
     * @param lines a list of strings where each string will be separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public void writeSmallTextFile(String file, List<String> lines) throws IOException{
        Path path = Paths.get(file);
        Files.write(path, lines, ENCODING);
    }
    /**
     * Writes quickly to a small text file with the specified encoding - not recommended for larger files. 
     * Use {@link writeLargeTextFile(String,List,Charset)} instead.
     * @param file name of the file to write to.
     * @param lines a list of strings where each string will be separated by a newline character.
     * @param encoding specifies what type of encoding to use when writing the file.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public void writeSmallTextFile(String file, List<String> lines, Charset encoding) throws IOException{
        Path path = Paths.get(file);
        Files.write(path, lines, encoding);
    }
    /**
     * Writes to large a text file with the default encoding.
     * @param file name of the file to read from.
     * @param lines a list of strings where each string will be separated by a newline character.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public void writeLargeTextFile(String file, List<String> lines) throws IOException{
        Path path = Paths.get(file);
        try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
            for(String line:lines){
                writer.write(line);
                writer.newLine();
            }
        }
    }
    /**
     * Writes to large a text file with the specified encoding.
     * @param file name of the file to read from.
     * @param lines a list of strings where each string will be separated by a newline character.
     * @param encoding specifies what type of encoding to use when writing the file.
     * @throws IOException if the specified file does not exist or cannot be accessed.
     */
    static public void writeLargeTextFile(String file, List<String> lines, Charset encoding) throws IOException{
        Path path = Paths.get(file);
        try(BufferedWriter writer = Files.newBufferedWriter(path, encoding)){
            for(String line:lines){
                writer.write(line);
                writer.newLine();
            }
        }
    }
}