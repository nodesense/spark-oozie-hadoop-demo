package ai.nodesense.kafka

import java.util.{Date, Properties}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import scala.util.Random
import ai.nodesense.models.{Click}


import org.joda.time._;
import play.api.libs.json._;


object WordProducer {


  def runProducer() = {
    // val events = 10;
    val topic = "words"

    //default
    //val brokers = "localhost:9092"

    //ambari
    //val brokers = "localhost:6667"
    val brokers = "sandbox-hdp.hortonworks.com:6667"

    val rnd = new Random()
    val props = new Properties()

    props.put("bootstrap.servers", brokers)
    props.put("client.id", "ScalaProducer")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("security.protocol", "PLAINTEXT")

    val producer = new KafkaProducer[String, String](props)
    val t = System.currentTimeMillis()

    val thread = new Thread {
      override def run {
        while (true) {
          // your custom behavior here
          Thread.sleep(2000);
          println("by thread");

          val runtime = new Date().getTime()

          val sessionId =  rnd.nextInt(255)
          val itemId =  rnd.nextInt(255)
          val categoryId =  rnd.nextInt(255)
          val ip ="123.45.64.34";
          var word = s"Word $itemId";



          val msg = word

          val data = new ProducerRecord[String, String](topic, ip, msg)

          println("Sendign ", msg);
          //async
          //producer.send(data, (m,e) => {})
          //sync
          producer.send(data)
        }

      }
    };

    thread.start();

    //System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t))
    // producer.close()
  }

  def main(args: Array[String]) = {

    runProducer();
  }

}