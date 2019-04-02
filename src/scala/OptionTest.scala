


object OptionTest {


  def main(args: Array[String]): Unit = {
    val list: List[String] = "nh" :: Nil

    val list1: List[String] = List("nh1")
    println(list ::: list1) // 两个集合并集
    println("a" +: list) // 加在列头 +： 和 ：：没有区别
    println("b" :: list) // 加在列头


    /** **  tranverse  *********/
    for (ele <- list) {
      println(s"ele:${ele}")
    }

    /** ***
      * how to use head
      */
    val fruit: List[String] = List("banana", "apple", "melon")
    fruit.head
    /**
      * how to use tail
      */
    val map: Map[String, Int] = Map("key1" -> 1)


    val notFoundValue: Option[Int] = map.get("key2")
    println(notFoundValue) //None
    println(notFoundValue.getOrElse(0))
    // getOrElse
    val foundValue: Option[Int] = map.get("key1")
    println(foundValue) //Some(1)


  }
}
