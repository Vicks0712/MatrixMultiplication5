package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileProccesor {

    public static void writeSubMatrixesToDisk(String createdSubmatrixes) {
        Path path = Paths.get("./submatrixes/", "divisionTest");
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, createdSubmatrixes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}



