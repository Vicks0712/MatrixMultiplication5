package matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

class MatrixReader {
    private static final Logger log =  LoggerFactory.getLogger(MatrixReader.class);
    public static List<List<String>> readMatrixes(File path) {
        List<List<String>> matrixes = new ArrayList<>();
        for (File file : Objects.requireNonNull(path.listFiles())) {
            try {
                matrixes.add(Files.readAllLines(file.toPath()));
            } catch (IOException e) {
                log.error("Error reading file" + file.getName(), e);
            }
        }
        return matrixes;
    }

    public static boolean checkSize(int size, int numberOfSubMatrixes) {
        return (size % numberOfSubMatrixes == 0) ? true : false;
    }
}




