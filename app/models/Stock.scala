package models

import play.api.libs.json.Json
import play.api.libs.json.Reads

case class Stock (
    symbol: String,
    price: Double
)

object Stock {
    implicit val format = Json.format[Stock]
    implicit val reads: Reads[Stock] = Json.reads[Stock]
}