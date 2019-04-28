package repositories

import org.scalatestplus.play._
import play.api.libs.json._
import org.scalatest._
// import repositories.userRepository
import models.User

/**
  * NOTE: To execute only this spec => [ng-scala-play] $ testOnly repositories.UserRepositorySpec*
  */
class UserRepositorySpec extends PlaySpec {

  val userRepository = new UserRepository()

  "userRepository#getUser" should {

    "return None for a user that doesn't match" in {
      userRepository.getUser("myemail@test.test", "1234") mustBe None
    }

    "return Some for a user that matches" in {
      val email = "alfre@test.test"
      val password = "foo"
      val expected: Some[User] = Some(User(email, password))
      userRepository.getUser(email, password) mustBe expected
    }
  }

  "User case class" should {

    "be converted automatically into a JsonValue" in {
      val mail = "spec@test.test"
      val passw = "bar"
      val json: JsValue = Json.parse("""
        {
          "email" : "spec@test.test",
          "password" : "bar"
        }
      """)
      val user = User(mail, passw)
      Json.toJson(user) mustBe json
    }

    /**
      * @link https://www.playframework.com/documentation/2.7.x/ScalaJson#Using-validation
      */
    "be created from a JsValue" in {
      val mail = "spec@test.test"
      val passw = "bar"
      val json: JsValue = Json.parse("""
        {
          "email" : "spec@test.test",
          "password" : "bar"
        }
      """)
      val user = User(mail, passw)
      // validate returns a JsSuccess or JsError
      val validated = json.validate[User].getOrElse("Nothing")
      validated mustBe user
    }
  }
}
