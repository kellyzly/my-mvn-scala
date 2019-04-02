package scala.variance

// 协变
// 定义一个类型 Writer[+A]，如果 A 是协变的，意思是：对类型 A 和 B，A 是 B 的子类型，那么 Writer[A] 是 Writer[B] 的子类型。

object CoVariance {

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  /////////////////////////////////////////////////////////////
  abstract class Printer[+A]

  case class AnimalPrinter(name: String) extends Printer[Animal]

  case class DogPrinter(name: String) extends Printer[Dog]

  def printPrint(printer: AnimalPrinter) {
    println(s"printAnimalPrinter ${printer.name}")
  }

  def printList(animalList: List[Animal]) {
    animalList.foreach(p => println(p.name))
  }

  def main(args: Array[String]): Unit = {
    val dogPrinter: DogPrinter = new DogPrinter("dog printer")
    val animalPrinter: AnimalPrinter = new AnimalPrinter("animal printer")

    val doglist = List(new Dog("dog"))

    val animalList: List[Animal] = doglist

    printPrint(animalPrinter)
    // 遗憾报错 解决不了
    //printPrint(dogPrinter)

    // Dog extends Animal List[Dog] 继承List[Animal]
    printList(doglist)
  }


}
