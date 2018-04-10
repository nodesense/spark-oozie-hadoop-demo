package ai.nodesense.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
 

  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)



    println("Loading file");

//    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    //val textFile = sc.textFile("src/main/resources/readme.md")
    val textFile = sc.textFile("/user/root/apps/spark-starter/input.txt");

    println("Loaded  file");
    //word count
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

   counts.foreach(println)
   System.out.println("Total words: " + counts.count());

  val hadoopConf = new org.apache.hadoop.conf.Configuration()
  val hdfs = org.apache.hadoop.fs.FileSystem.get(new java.net.URI("hdfs://sandbox-hdp.hortonworks.com:8020"), hadoopConf)

  try { hdfs.delete(new org.apache.hadoop.fs.Path("/tmp/shakespeareWordCount"), true) } catch { case _ : Throwable => { } }

   counts.saveAsTextFile("/tmp/shakespeareWordCount");

  }

}