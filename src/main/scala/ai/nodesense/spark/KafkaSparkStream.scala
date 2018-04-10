package ai.nodesense.spark

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming._

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

//object KafkaCount extends    App {
object KafkaSparkStream {
  def main(args: Array[String]) {


    println("**STARTING***");
    val kafkaServer = "localhost:2181"
    val numThreads = 1
    val group = "spark-streaming-consumer-group"
    //val topics = "words"


    val sparkConf = new SparkConf().setAppName("KafkaWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(30))
    ssc.checkpoint("checkpoint")


    val kafkaParams = Map[String, Object](
      // "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
      "bootstrap.servers" -> "sandbox-hdp.hortonworks.com:6667",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      //  "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("clicks")
    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val lines = messages.map(_.value())
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)

    // local file
    wordCounts.saveAsTextFiles("/tmp/spark-click-results")

    //hdfs files

    wordCounts.saveAsTextFiles("hdfs://localhost:8020/user/root/clicks")


    wordCounts.print()


    ssc.start()
    ssc.awaitTermination()
    // }
  }
}