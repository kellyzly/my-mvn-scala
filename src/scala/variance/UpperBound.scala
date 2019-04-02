package scala.variance

object UpperBound {

  //上界定义： T <: A ，表示类型变量 T 必须是类型 A 子类
  abstract class Pet {
    def printMe
  }

  case class Dog(name: String) extends Pet {
    override def printMe: Unit = println("dog")
  }

  case class Cat(name: String) extends Pet {
    override def printMe: Unit = println("cat")
  }

  class PetContainer[T <: Pet](t: T) {
    def printme = {
      t.printMe
    }
  }

  def main(args: Array[String]): Unit = {
    val catContainer = new PetContainer[Dog](new Dog("hui"))

    val dogContainer = new PetContainer[Cat](new Cat("kitty"))

    val animalContainerList = List(catContainer, dogContainer)

    animalContainerList.foreach(p => p.printme)
  }
}
