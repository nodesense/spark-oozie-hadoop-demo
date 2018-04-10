package ai.nodesense.models

import org.joda.time.DateTime
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._
import play.api.libs.json._

import play.api.libs.json.JodaWrites
import play.api.libs.json.JodaReads

case class Click(sessionId: Int,
                 timeStamp: DateTime,
                 itemId: Int,
                 category: String
                ) {


}



object Click {
  val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
  implicit val dateFormat = Format[DateTime](Reads.jodaDateReads(pattern), Writes.jodaDateWrites(pattern))
  implicit val clickFormat = Json.format[Click]
}
