import java.io.{FileOutputStream, File}
import java.util.zip.{ZipEntry, ZipOutputStream}

import com.google.common.io.Files


object Summer{
  def main(args:Array[String]): Unit ={
    val startTime = System.currentTimeMillis()
    val jarsDir = new File("/home/zly/spark-2.0.0-bin-hadoop2-without-hive.build.by.sdp/jars")
    val jarsArchive = File.createTempFile("__spark_libs__", ".zip",
      new File("/home/zly/spark-2.0.0-bin-hadoop2-without-hive.build.by.sdp/conf"))
    val jarsStream = new ZipOutputStream(new FileOutputStream(jarsArchive))

    try {
      jarsStream.setLevel(0)
      jarsDir.listFiles().foreach { f =>
        if (f.isFile && f.getName.toLowerCase().endsWith(".jar") && f.canRead) {
          jarsStream.putNextEntry(new ZipEntry(f.getName))
          Files.copy(f, jarsStream)
          jarsStream.closeEntry()
        }
      }
    } finally {
      jarsStream.close()
    }
    val duration = System.currentTimeMillis()-startTime
    println("duration:"+duration +" millis")
    println("duration:"+(duration/1000)+" seconds")
  }
}

