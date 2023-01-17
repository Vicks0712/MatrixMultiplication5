package hadoop;

import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class Reducer extends org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text position, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(null, new Text(position + "," +
                StreamSupport.stream(values.spliterator(), false)
                .mapToLong(val -> Long.parseLong(val.toString()))
                .sum()));
    }
}