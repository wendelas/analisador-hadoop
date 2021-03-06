package net.marcoreis.hadoop.mapreduce.parte1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ShortWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ProposicoesPorPeriodoDriver {
    public static void main(String[] args) throws Exception {
	String arquivoEntrada = System.getProperty("user.home") + "/dados/legislativo/entrada/proposicoes.csv";
	String diretorioSaida = System.getProperty("user.home") + "/dados/legislativo/saida";
	//
	Job job = Job.getInstance();
	//
	job.setJarByClass(ProposicoesPorPeriodoDriver.class);
	job.setJobName("Contador de proposições legislativas por período");
	//
	FileInputFormat.addInputPath(job, new Path(arquivoEntrada));
	FileOutputFormat.setOutputPath(job, new Path(diretorioSaida));
	//
	job.setMapperClass(ProposicoesPorPeriodoMapper.class);
	job.setReducerClass(ProposicoesPorPeriodoReducer.class);
	//
	job.setOutputKeyClass(IntWritable.class);
	job.setOutputValueClass(IntWritable.class);
	//
	job.setMapOutputKeyClass(ShortWritable.class);
	job.setMapOutputValueClass(LongWritable.class);
	//
	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
