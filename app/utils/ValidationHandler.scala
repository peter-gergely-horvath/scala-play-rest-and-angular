package utils

trait ValidationHandler {
  def isValid = (param: Any) => Boolean
}


trait EmailValidationHandler extends ValidationHandler {
  protected val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def isValid(email: String): Boolean = emailRegex.findFirstIn(email) match {
    case Some(_)  => true
    case None     => false
  }
}
