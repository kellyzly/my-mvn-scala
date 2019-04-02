package scala

object TraitTest {

  trait People {
    // scala 的trait can be implemented
    def print() = println("I am people")
  }

  class Student extends People {

  }

  class Teacher extends People {

  }

  def main1(args: Array[String]): Unit = {
    var student = new Student
    student.print()
  }

  ////////////////////////////////////////////////////////////////
  //Self-type 感觉就是mix trait

  trait User {
    def userName: String
  }

  trait Tweeter {
    this: User =>
    def logMessage(msg: String) = {}


  }

  class RealUser(userName1: String) extends Tweeter with User {

    override def userName: String = s"real user ${userName1}"

    override def logMessage(msg: String): Unit = {
      println(s"${userName1} - ${msg}")
    }


  }

  def main(args: Array[String]): Unit = {
    var realUser = new RealUser("bioyce")
    realUser.logMessage("drink lemon tea")

  }

}
