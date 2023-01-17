package hadoop;

import matrix.Partitioner;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;


public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        manageDatamartDirectory();
        new Partitioner().partitionate(new File(args[0]), Integer.parseInt(args[2]));
        Job job = Job.getInstance(new Configuration(), "Hadoop Matrix Multiplication");
        job.setJarByClass(Driver.class);
        job.setMapperClass(Mapper.class);
        job.setReducerClass(Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(".\\submatrixes"));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        if (!job.waitForCompletion(true)) System.exit(1);

    }

    public static void manageDatamartDirectory() throws IOException {
        FileUtils.deleteDirectory(new File(".\\submatrixes"));
    }
}
