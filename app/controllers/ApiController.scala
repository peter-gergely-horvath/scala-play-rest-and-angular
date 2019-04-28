package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{Json, JsError}
import repositories.{DataRepository, UserRepository}
import models.{Stock, User}
import scala.concurrent.Future

/**
 * This controller will serve the main /api/ methods
 */
@Singleton
class ApiController @Inject()(
    cc: ControllerComponents,
    dataRepository: DataRepository,
    userRepository: UserRepository
) extends AbstractController(cc) {

  def preflight(all: String) = Action {
    Ok("").withHeaders(
      "Access-Control-Allow-Origin" -> "*",
      "Allow" -> "*",
      "Access-Control-Allow-Methods" -> "POST, GET, PUT, DELETE, HEAD, OPTIONS", // OPTIONS for pre-flight
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Referrer, User-Agent, Authorization, X-Auth-Token",
      "Access-Control-Allow-Credentials" -> "true"
    );
  }

  // Create a simple 'ping' endpoint for now, so that we
  // can get up and running with a basic implementation
  def ping = Action { implicit request =>
    Ok("PONG!")
  }

  def getPost(id: Int) = Action { implicit request =>
    dataRepository.getPost(id) map { post =>
      Ok(Json.toJson(post))
    } getOrElse NotFound
  }

  def getUser(email: String, password: String) = Action { implicit request =>
    userRepository.getUser(email, password) map { user =>
      Ok(Json.toJson(user))
    } getOrElse NotFound
  }

  def userLogin = Action(parse.json) { request =>
    val userResult = request.body.validate[User]
    userResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      user => {
        userRepository.getUser(user.email, user.password) map { found =>
          Ok(Json.toJson(found))
        } getOrElse NotFound(Json.obj("status" ->"KO", "message" -> "User not found")) 
        // Ok(Json.obj("status" ->"OK", "message" -> ("User '"+user.email+"' saved.") ))
      }
    )
  }

  /**
   * http localhost:9000/api/comment/1
   */
  def getComments(postId: Int) = Action { implicit request =>
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }

  /**
   * http POST localhost:9000/api/stock symbol=GOOG price:=1234.5
   */
  def saveStock = Action.async(parse.json) { implicit request =>
    request.body.validate[Stock].fold(
      error => Future.successful(InternalServerError("JSON did not validate.")),
      stock => Future.successful(Ok(Json.obj("status" -> "OK", "message" -> """Stock saved successfully""")))
    )
  }
}
