import scala.util.matching.Regex

object RegexPatternTest {

  def main(args: Array[String]): Unit = {

    //换行写
    val multiline: String =
      """kelly
        |kelly1
        |kelly2"""
    println(multiline.stripMargin)
    val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+)@([0-9a-zA-Z-#() ]+).com".r

    val input: String =
      """background-color@1.com;
        |background-image@2.com;
        |width@3.com;""".stripMargin

    for (patternMatch <- keyValPattern.findAllMatchIn(input))
      println(s"key:${patternMatch.group(1)} value:${patternMatch.group(2)}  ")

  }
}
