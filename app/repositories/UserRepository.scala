package repositories

import javax.inject.Singleton
import models.User

@Singleton
class UserRepository {

  private val users = Seq(
    User("alfre@test.test", "foo"),
    User("admin@test.test", "test")
  )
  
  def getUser(email: String, password: String): Option[User] =
    users.collectFirst {
      case u if u.email == email && u.password == password => u
    }
}
