package models

import play.api.libs.json.Json

case class Post(
    id: Int,
    content: String
)

object Post {
  /* This is used by the Play Framework when it comes 
  to sending our Post object back to the client as JSON - e.g. Ok(Json.toJson(post))
  It is also able to go the other way and rehydrate our Post model from JSON. 
  This is convenient, as rather than manually specify what the exact shape of the JSON is, 
  we can use this built-in formatter which generates the JSON based on the properties and 
  types that are present on the model.  */
  implicit val format = Json.format[Post]
}
