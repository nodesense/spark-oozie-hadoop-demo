package ai.nodesense.util

import java.sql.{Connection, DriverManager, PreparedStatement, Timestamp}

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import java.util.Calendar

import ai.nodesense.models.Click

import scala.collection.mutable.ListBuffer
import scala.io.Source;

object FeedData   {

  val driver = "com.mysql.cj.jdbc.Driver"

  def feedMySQL(url: String, username: String, password: String, filename: String) = {
    var connection:Connection = null;
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      println("Connected");

      println("Reading file");
      val clicksList: ListBuffer[Click] = ReadFile.readFile(filename)

      println("read input file, inserting into db");

      for (click <- clicksList) {
        println(click);

        val  statement:PreparedStatement = connection.prepareStatement("INSERT INTO clicks(session,timestamp, item, category) VALUES (?, ?, ?, ?)");
        statement.setInt(1, click.sessionId);

        val timeNow = Calendar.getInstance.getTimeInMillis
        //val ts = new Timestamp(timeNow)
        val ts = new Timestamp(click.timeStamp.getMillis())

        statement.setTimestamp(2, ts);

        statement.setInt(3, click.itemId);
        statement.setString(4, click.category);

        statement.executeUpdate();

      }


    } catch {
      case e: Exception => e.printStackTrace
    }
    connection.close
  }

  def main(args: Array[String]) = {
    println("mysql")

    val url = "jdbc:mysql://localhost:3306/clicksdb"
    //val driver = "com.mysql.jdbc.Driver"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "hadoop"

    val filename = "/root/apps/sparkpro/data/yoochoose-clicks.csv";
    feedMySQL(url, username, password, filename);

  }


}