package models

import play.api.libs.json.Json

case class User (
    email: String,
    password: String
)

object User {
    implicit val format = Json.format[User]
}