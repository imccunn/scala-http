
import org.apache.http.{HttpEntity, HttpRequest}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization._
import org.json4s.{DefaultFormats, NoTypeHints}

/**
  * Created by imccunn on 8/8/16.
  */
object Hello extends App {

  implicit val formats = Serialization.formats(NoTypeHints)

  val payload = Map[String, String](
    "data" -> "neat",
    "cool"-> "wow"
  )
  println(payload)
  val json = write(payload)
  println(json)
  val httpclient:CloseableHttpClient = HttpClients.createDefault()
  val post:HttpPost = new HttpPost("http://localhost:3333/data")

  val entity:StringEntity = new StringEntity(json)
  post.setEntity(entity)
  val res = httpclient.execute(post)
  val resEntity = res.getEntity
  val responseString = EntityUtils.toString(resEntity, "UTF-8")
  println(responseString)
  println("End")
}
