package ai.nodesense.examples

import ai.nodesense.models.{Click}
import org.joda.time.DateTime
import play.api.libs.json._;
//
import play.api.libs.json.JodaWrites
import play.api.libs.json.JodaReads

object DateJson {

  def main(args: Array[String]): Unit = {
      println("Joda Date Parser Json ");
      val click = Click(1, new DateTime(), 100, "10000");


      val jsValue:JsValue = Json.toJson(click)

      val jsonText = jsValue.toString
      println("DateJson ", jsonText);




    val jsonObj = Json.parse(jsonText)

    val result: JsResult[Click] = Json.fromJson[Click](jsonObj)

    val click2: Click = result.get

    println("Converted DateJson ", click2);


  }
}
