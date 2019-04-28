package repositories

import javax.inject.Singleton
import models.{Post, User, Comment}

@Singleton
class DataRepository {

  // A mock for serving posts
  private val posts = Seq(
    Post(1, "This is an example of a blog post"),
    Post(2, "A second blog post")
  )

  /**
    * Specify some comments for our API to serve up
    */
  private val comments = Seq(
    Comment(1, 1, "This is an awesome blog post", "Fantastic Mr Fox"),
    Comment(2, 1, "Thanks for the insights", "Jane Doe"),
    Comment(3, 2, "Great, thanks for this post", "Joe Bloggs")
  )

  /**
    * Returns a blog post that matches the specified id, or None if no
    * post was found (collectFirst returns None if the function is undefined for the
    * given post id)
    */
  def getPost(postId: Int): Option[Post] = posts.collectFirst {
    case p if p.id == postId => p
  }

  def getComments(postId: Int): Seq[Comment] = comments.collect {
    case c if c.postId == postId => c
  }

}
