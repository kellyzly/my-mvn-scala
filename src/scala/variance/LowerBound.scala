package scala.variance

object LowerBound {

  //下界的语法也很诡异，感觉就是为了  Collection 多态准备的
  // 语法 B >: A 表示参数类型或抽象类型 B 须是类型 A 的父类。通常，A 是类的类型参数，B 是方法的类型参数。
  //

  trait Node[+B] {
    def prepend[U >: B](elem: U): Node[U]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)

    def head: B = h

    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)

  }

  trait Bird

  case class AfricanSwallow() extends Bird

  case class EuropeanSwallow() extends Bird

  def main(args: Array[String]): Unit = {
    val africanSwallow = ListNode[AfricanSwallow](AfricanSwallow(), Nil())
    val birdList: Node[Bird] = africanSwallow
    birdList.prepend(new EuropeanSwallow)

  }
}
