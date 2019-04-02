import scala.collection.immutable.Stack

object YieldTest {
  // find (i,j) match i+j=10
  def foo(n: Int, v: Int) =
    for (i <- 0 until n;
         j <- 0 until n if i + j == v)
      yield (i, j)

  def foo1(n: Int, v: Int) =
    for (i <- 0 until n)
      for (j <- 0 until n)
        if ((i + j == v)) {
          println(s"($i, $j)")
        }

  def main(args: Array[String]): Unit = {

    val s = new Stack[Int]
    s.push(1)
    s.pop
    foo(10, 10) foreach {
      case (i, j) =>
        println(s"($i,$j)")
    }

  }
}
