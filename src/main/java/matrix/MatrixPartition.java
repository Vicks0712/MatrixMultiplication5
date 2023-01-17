package matrix;

public class MatrixPartition implements Partition {
    public final int row;
    public final int col;
    private long[][] elements;
    public final int size;

    public MatrixPartition(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }
    @Override
    public void setValues(long[][] elements) {
        this.elements = elements;
    }
    @Override
    public long getValues(int i, int j){
        return this.elements[i][j];
    }

    public static MatrixPartition fromString(String[] originalMatrix) {
        int size = (int) (Math.sqrt(originalMatrix.length - 3));
        int row = Integer.parseInt(originalMatrix[0]);
        int col = Integer.parseInt(originalMatrix[1]);
        MatrixPartition partition = new MatrixPartition(row, col, size);
        setValuesFromString(partition, originalMatrix);
        return partition;
    }

    private static void setValuesFromString(MatrixPartition partition, String[] originalMatrix) {
        int size = partition.size;
        long[][] elements = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                elements[j][i] = Long.parseLong(originalMatrix[3 + i + j * size]);
            }
        }
        partition.setValues(elements);
    }
}






