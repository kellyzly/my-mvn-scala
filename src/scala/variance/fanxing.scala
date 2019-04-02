package scala.variance

object fanxing {

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  def printAnimals(animals: List[Animal]): Unit = {
    animals.foreach {
      animal => print(animal.name)
    }
  }

  // 泛型 Dog extends Animal
  // printAnimals(animals: List[Animal])
  //可以传入 printAnimal(dogs:List[Dog])
  def main(args: Array[String]): Unit = {
    val animalList: List[Animal] = List(new Dog("dog1"))
    val dogList: List[Dog] = List(new Dog("dog"))

    printAnimals(dogList)

  }

}
