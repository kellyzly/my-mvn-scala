object Calculator {
  def main(args: Array[String]): Unit = {
    testPartialFunc()
    testPattern(Some("a"))
    testPattern(None)

    val op1 = simplifyTop(UnOp("-", UnOp("-", Var("x"))))
    op1 match {
      case Var(x) => println(x)
      case _ => println("not match")
    }
    val op2 = simplifyTop_withSealed(UnOpSealed("-", UnOpSealed("-", VarSealed("x"))))

    val list1 = List(0, 1, 4)
    // _* express 0 or more input parameter
    list1 match {
      case List(0, _*) => println("found it")
      case _ => println("not found")
    }
  }

  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    case _ => expr
  }


  def testPartialFunc() = {
    val second: List[Int] => Int = {
      case x :: y :: _ => y
    }

    second(List(1, 2, 3))

    //second(List(1))// throw error scala.MatchError List(1)

    // use partial function to solve the MatchError
    val secondPartial: PartialFunction[List[Int], Int] = {
      case x :: y :: _ => y
    }

    // val res = secondPartial(List(1)) // still throw error
    println(secondPartial.isDefinedAt(List(1))) //false
    println(secondPartial.isDefinedAt(List(1, 2))) //true

  }

  def simplifyTop_withSealed(expr: ExprSealed): ExprSealed = expr match {
    case UnOpSealed("-", x) => println(x); x
  }

  def testPattern(x: Option[String]): Unit = {
    if (x.isDefined) {
      println(x)
    } else {
      println("nothing")
    }
    x match {
      case Some(x) => println(x)
      case None => println("nothing")
    }
    val BinOp(operator, left, right) = BinOp("+", Var("x"), Var("y"))
    println(s"operator:$operator,left:$left, right:$right")


  }


  // 被sealed 定义的trait/abstract class 只能被同在一个文件里的类继承
  sealed trait ExprSealed

  case class VarSealed(name: String) extends ExprSealed

  case class NumberSealed(name: String) extends ExprSealed

  case class UnOpSealed(operator: String, arg: ExprSealed) extends ExprSealed

  case class BinOpSealed(operator: String, left: ExprSealed, right: ExprSealed) extends ExprSealed


  abstract class Expr

  case class Var(name: String) extends Expr

  case class Number(num: Double) extends Expr

  case class UnOp(operator: String, arg: Expr) extends Expr

  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

}