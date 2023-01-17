package matrix;

import utils.MultiplicationManager;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SubMatrixCreator {
    int matrixSize;
    List<String> subMatrixes;

    public SubMatrixCreator(int matrixSize, List<String> subMatrixes) {
        this.matrixSize = matrixSize;
        this.subMatrixes = subMatrixes;
    }

    public static void saveSubMatrixes(List<List<String>> createdSubmatrixes, List<String> subMatrixes, int nSubmatrixes, String matrixName, int col) {
        IntStream.range(0, nSubmatrixes)
                .forEach(i -> {
                    subMatrixes.add(col + " " + i + " " + matrixName + " " + String.join(" ", createdSubmatrixes.get(i)));
                    createdSubmatrixes.set(i, new ArrayList<>());
                });
    }

    public static List<List<String>> createSubMatrixContainer(int numberOfSubmatrixes) {
        return IntStream.range(0, numberOfSubmatrixes)
                .mapToObj(i -> new ArrayList<String>())
                .collect(Collectors.toList());
    }
    public void createSubMatrixes(List<List<String>> createdSubmatrixes, List<String> row, int nSubMatrixes, String matrixName) {
        AtomicInteger col = new AtomicInteger();
        row.forEach(matrixARow -> {
            for (int i = 0; i < nSubMatrixes; i++) {
                for (int j = 0; j < matrixSize / nSubMatrixes; j++) {
                    createdSubmatrixes.get(i).add(matrixARow.split(" ")[j + i * (matrixSize / nSubMatrixes)]);
                }
            }
            if (MultiplicationManager.isMatrixComplete(matrixSize / nSubMatrixes, createdSubmatrixes)) {
                saveSubMatrixes(createdSubmatrixes, this.subMatrixes, nSubMatrixes, matrixName, col.get());
                col.getAndIncrement();
            }
        });
    }

}








