package test.tinkoff.detectbacknumber

import scala.util.Try

object Detectbacknumber {

  def mapStr2Date(
                   data:Map[String,String],
                   frmt:String="yyyy.MM.dd"
                 ):Try[Map[Int,java.util.Date]] = {
    val format = new java.text.SimpleDateFormat(frmt)
    format.format(new java.util.Date())
    Try(
      data map {
        case (key, value) => (key.toInt,format.parse(value))
      }
    )
  }

  def getBackNumberField(data:Map[Int,java.util.Date]):Iterable[Int] = {
    val minKey = data.keys.min
    val sortedByDate = data.toSeq.sortBy(_._2)
    val detectBackNumberField = {
      sortedByDate.zipWithIndex.map({
        case (x, i) =>
          if (x._1 == minKey)
            sortedByDate.slice(0, i)
          else null
      }).filter(_!= null).flatten.toMap.keys
    }
    detectBackNumberField
  }
}
