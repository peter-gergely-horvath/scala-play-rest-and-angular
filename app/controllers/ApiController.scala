package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import repositories.DataRepository

@Singleton
class ApiController @Inject()(cc: ControllerComponents,
                              dataRepository: DataRepository
                              )
  extends AbstractController(cc) {

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
    dataRepository.getUser(email, password) map { user => 
      Ok(Json.toJson(user))
    } getOrElse NotFound
  }

  def getComments(postId: Int) = Action { implicit request =>
    Ok(Json.toJson(dataRepository.getComments(postId)))
  }

}
