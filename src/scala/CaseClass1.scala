import java.io.{ByteArrayInputStream, ObjectInputStream, ObjectOutputStream, ByteArrayOutputStream}


object CaseClass1 {


  // abstract 和trait 区别
  //  优先使用 trait 可以继承多个接口
  // trait 没有构造函数 Device1(i:Int)  abstract class 可以
  trait Device1 {
    def source: String

  }


  class Phone1 extends Device1 {
    override def source: String = "phone1"
  }

  class Compute1 extends Device1 {
    override def source: String = "compute1"
  }


  abstract class Device {
    def name: String

    def source: String = {
      s" this is a $name"
    }
  }

  case class Phone(name: String) extends Device {


    override def toString: String = s"i am  a phone"
  }


  case class Compute(name: String) extends Device {

    override def toString: String = s"i am  a compute"
  }


  def testSerializeDeSerializeForCaseClass(compute: Compute): Unit = {
    try {
      val bos = new ByteArrayOutputStream


      val oos = new ObjectOutputStream(bos)
      oos.writeObject(compute)
    } catch {
      case e: Exception => println(e) // no exception as case class define serialize and deserialize
    }
  }

  def testSerializeDeSerializeForNonCaseClass(compute: Compute1): Unit = {
    try {
      val bos = new ByteArrayOutputStream


      val oos = new ObjectOutputStream(bos)
      oos.writeObject(compute)
    } catch {
      case e: Exception => println(e) // exception as  class does not define serialize and deserialize
    }
  }

  def main(args: Array[String]): Unit = {

    val compute = Compute("compute")
    val phone = Phone("phone")

    //scala Seq 对应 Java 的List
    // scala List 对应Java 的LinkedList
    val deviceSeq = Seq(compute, phone)
    //deviceSeq.map(x=>println(x.source))
    deviceSeq.map(x => println(testMatchCase(x)))

    val compute1 = new Compute1()
    //    val phone1 = new Phone1()
    //    val deviceSeq1 = Seq(compute1, phone1)
    //    deviceSeq1.map(x=> x.source)


    testSerializeDeSerializeForNonCaseClass(compute1)


    //testSerializeDeSerializeForCaseClass(compute)
  }

  //  //序列化（将对象传入，变成字节流）
  //  def serialize[T](o:T):Array[Byte]={
  //    val bos = new ByteArrayOutputStream()//内存输出流，和磁盘输出流从操作上讲是一样的
  //    val oos = new ObjectOutputStream(bos)
  //    oos.writeObject(o)
  //    oos.close()
  //    bos.toByteArray
  //  }
  //  //反序列化
  //  def deserialize[T](bytes:Array[Byte]):T={
  //    val bis=new ByteArrayInputStream(bytes)
  //    val ois=new ObjectInputStream(bis)
  //    ois.readObject.asInstanceOf[T]//进行类型转换，因为你要返回这个类型
  //  }

  // 总结  match 只能用在 case class
  def testMatchCase(device: Device): String = {
    device match {
      case Compute(source: String) => s" source:$source"
      case Phone(source: String) => s" source:$source"
    }
  }
}
