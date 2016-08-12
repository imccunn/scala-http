
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization._
import org.json4s.NoTypeHints

import scala.util.Try

/**
  * Created by imccunn on 8/8/16.
  */
object Hello extends App {

  implicit val formats = Serialization.formats(NoTypeHints)

  val url = "http://localhost:3333/data"
  val payload = Map[String, Either[String, Int]](
    "id" -> Right(2232),
    "cool"-> Left("beans"),
    "data" -> Right(1234322)
  )
  println(payload)
  val json = write(payload)
  println(json)

  val responseString = httpPost(json, url)

  println(responseString)

  def httpPost(payload:String, url:String):Try[String] =
    Try {
      implicit val formats = Serialization.formats(NoTypeHints)
      println(s"Attempting http POST with payload: $payload")
      val httpclient:HttpClient = HttpClients.createDefault()
      val post:HttpPost = new HttpPost(url)
      val entity:StringEntity = new StringEntity(payload)
      post.setEntity(entity)
      val res = httpclient.execute(post)
      val resEntity = res.getEntity
      val responseString = EntityUtils.toString(resEntity, "UTF-8")
      println(responseString)
      responseString
    }
}
