package hadoop;

import matrix.MatrixPartition;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text item, Context context) throws IOException, InterruptedException {
        String[] stringMatrix = item.toString().split(";");
        MatrixPartition matrix1 = MatrixPartition.fromString(stringMatrix[0].split(" "));
        MatrixPartition matrix2 = MatrixPartition.fromString(stringMatrix[1].split(" "));
        multiply(matrix1, matrix2, context);
    }

    private void multiply(MatrixPartition a, MatrixPartition b, Context context) throws IOException, InterruptedException {
        for (int i = 0; i < a.size; i++)
            for (int j = 0; j < a.size; j++)
                for (int k = 0; k < a.size; k++)
                    context.write(
                            new Text( (i + a.row *a.size) + "," + (j + b.col *b.size)),
                            new Text(String.valueOf(a.getValues(i,k) * b.getValues(k,j)))
                    );
    }

}
