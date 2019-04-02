object PartialFunctionTest {


  // partial function
  def fun1: PartialFunction[String, Int] = {
    case "a" => 1
    case _ => 0
  }

  def fun2: PartialFunction[String, Int] = {
    case "b" => 2
    case _ => 1
  }

  //  run small example here
  def main(args: Array[String]) = {


    //fun2 处理不了就func1
    val fun = fun2 orElse fun1

    println(fun("b"))

    println(fun("c"))
  }
}
