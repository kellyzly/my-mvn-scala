package scala.variance

// 逆变
// 定义一个类型 Writer[-A]，如果 A 是逆变的，意思是：对类型 A 和 B，A 是 B 的子类型，那么 Writer[B] 是 Writer[A] 的子类型。


object Contravariance {

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  /////////////////////////////////////////////////////////////
  // Contravariance (逆变）
  abstract class Printer[-A] {
    def printWangwangdui(animal: A): Unit
  }

  class AnimalPrinter extends Printer[Animal] {
    def printWangwangdui(animal: Animal): Unit =
      println(s"animal :${animal.name}")
  }

  val marshal = new Dog("marshal")

  class DogPrinter extends Printer[Dog] {
    def printWangwangdui(dog: Dog): Unit =
      println(dog.name)

  }

  def printAnimalPrinter(printer: Printer[Animal]): Unit = {
    printer.printWangwangdui(marshal)
  }

  def printDogPrinter(printer: Printer[Dog]): Unit = {
    printer.printWangwangdui(marshal)
  }

  def main(args: Array[String]): Unit = {
    val animalPrinter = new AnimalPrinter
    animalPrinter.printWangwangdui(new Dog("luma"))

    // unexpect it does not show compile error because
    // the parameter is Animal but Printer[-A] means
    // Dog extends Animal
    // Printer[Animal] extends Printer[Dog]
    printDogPrinter(animalPrinter)

  }
}
