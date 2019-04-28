package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class User(
    email: String,
    password: String
)

object User {
  implicit val format = Json.format[User]

  implicit val userWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "email" -> user.email,
      "password" -> user.password
    )
  }

  implicit val userReads: Reads[User] = (
    (JsPath \ "email").read[String] and
    (JsPath \ "password").read[String]
  )(User.apply _)

  def save(u: User): Unit = {
    println("saving user")
  }
}
