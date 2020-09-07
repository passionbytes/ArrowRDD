package com.passion

import org.apache.spark.sql.execution.arrow._
import org.apache.spark.sql._
import org.apache.arrow.memory._
import org.apache.arrow.vector.dictionary._
import java.io._


object Sample {
def main(args: Array[String]) {
val spark = SparkSession.builder().appName("spark-arrow").master("local[*]").getOrCreate();
import spark.implicits._

//val values = List(1,2,3,4,5)
//val df = values.toDF() 
//val df = spark.read.parquet("file:///Users/ravishankarnair/data/people.parquet")
val df = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("file:///Users/ravishankarnair/data/ratings.csv")

val myRdd = new ArrowRDD(df)

myRdd.mapPartitions( it => 
    { 
      while(it.hasNext) {
       val vsr = it.next()
       import scala.util.Random
       val dictProvider = new DictionaryProvider.MapDictionaryProvider();
       val randomfileName = new Random().nextInt()
       val fd = new FileOutputStream(new File(s"/Users/ravishankarnair/scalaexp/sparkarrow/data/arrow${randomfileName}.arrow"))
       val fileWriter = new org.apache.arrow.vector.ipc.ArrowFileWriter(vsr, dictProvider, fd.getChannel()) 
       fileWriter.start()
       fileWriter.writeBatch()
       

       fileWriter.end();
       fileWriter.close();
       fd.flush();
       fd.close();

        println(vsr.contentToTSVString())
       }//End of partitions block
     Iterator[Int](1)
  }).cache.collect

//println(myRdd)
}

}
