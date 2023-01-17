package matrix;


import utils.FileProccesor;
import utils.MultiplicationManager;

import java.io.*;
import java.util.*;

public class Partitioner {

    private int matrixSize;
    private final List<String> subMatrixes = new ArrayList<>();

    public void partitionate(File pathToMatrixes, int nSubMatrixes)  {

        List<List<String>> originalMatrixes = MatrixReader.readMatrixes(pathToMatrixes);
        matrixSize = originalMatrixes.get(0).get(0).split(" ").length;

        MatrixReader.checkSize(matrixSize,nSubMatrixes);
        new SubMatrixCreator(matrixSize, subMatrixes)
                .createSubMatrixes(SubMatrixCreator
                        .createSubMatrixContainer(nSubMatrixes),originalMatrixes.get(0),nSubMatrixes, "Matrix1");
        new SubMatrixCreator(matrixSize, subMatrixes)
                .createSubMatrixes(SubMatrixCreator
                        .createSubMatrixContainer(nSubMatrixes),originalMatrixes.get(1),nSubMatrixes, "Matrix2");

        String allMapperItems = MultiplicationManager.checkMultiplication(nSubMatrixes, subMatrixes);
        FileProccesor.writeSubMatrixesToDisk(allMapperItems);
    }
}





