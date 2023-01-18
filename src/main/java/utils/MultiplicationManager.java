package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiplicationManager {


    private static int submatrixSize = 0;

    public static boolean isMatrixComplete(int submatrixesSize, List<List<String>> currentSubmatrixes) {
        submatrixSize = submatrixesSize * submatrixesSize;
        return currentSubmatrixes.get(0).size() == submatrixSize;
    }

    public static String checkMultiplication(int nSubMatrixes, List<String> createdSubmatrixes) {
        return IntStream.range(0, nSubMatrixes)
                .boxed()
                .flatMap(i -> IntStream.range(0, nSubMatrixes)
                        .boxed()
                        .flatMap(j -> IntStream.range(0, nSubMatrixes)
                                .boxed()
                                .map(k -> new int[]{i, j, k})
                        )
                )
                .filter(index -> verifyMultiplicationSize(createdSubmatrixes.get(index[0] * nSubMatrixes + index[2]),
                        createdSubmatrixes.get(index[2] * nSubMatrixes + index[1] + nSubMatrixes * nSubMatrixes)))
                .map(index -> createdSubmatrixes.get(index[0] * nSubMatrixes + index[2]) + ";" + createdSubmatrixes.get(index[2] * nSubMatrixes + index[1] + nSubMatrixes * nSubMatrixes))
                .collect(Collectors.joining("\n"));
    }

    private static int[] extractMatrixSize(String matrix) {
        String[] matrixValues = matrix.split(" ");
        return new int[] {Integer.parseInt(matrixValues[0]), Integer.parseInt(matrixValues[1])};
    }

    private static boolean verifyMultiplicationSize(String matrixA, String matrixB) {
        int[] matrixASize = extractMatrixSize(matrixA);
        int[] matrixBSize = extractMatrixSize(matrixB);
        return matrixASize[1] == matrixBSize[0];
    }

}
