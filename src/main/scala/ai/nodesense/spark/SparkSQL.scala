package ai.nodesense.spark

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSQL {
    val spark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()


// For implicit conversions like converting RDDs to DataFrames

  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Spark SQL")

        val jdbcHostname = "localhost"
        val jdbcPort = 3306
        val jdbcDatabase ="clicksdb"
        val jdbcUsername = "root";
        val jdbcPassword = "";
        // Create the JDBC URL without passing in the user and password parameters.
        val jdbcUrl = s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}"

        // Create a Properties() object to hold the parameters.
        val connectionProperties = new Properties()

        connectionProperties.put("user", s"${jdbcUsername}")
        connectionProperties.put("password", s"${jdbcPassword}")

        // deprecated, still works
        // val driverClass = "com.mysql.jdbc.Driver"

        val driverClass = "com.mysql.cj.jdbc.Driver"

        Class.forName(driverClass)

        connectionProperties.setProperty("Driver", driverClass)

        val clicks_table = spark.read.jdbc(jdbcUrl, "clicks", connectionProperties)

        // DF vs RDD

        clicks_table.printSchema
        clicks_table.show()
        // TODO: aggregattion, window based analytics

        clicks_table.rdd.saveAsTextFile("hdfs://localhost:8020/user/krish/clicks-sql")


    println("Loading file");

     
    //val df = spark.read.json("src/main/resources/products.json")

    // Displays the content of the DataFrame to stdout
    //df.show()

    //df.printSchema()

    //df.select("name").show()

    println("**DONE**");


 
  }

}