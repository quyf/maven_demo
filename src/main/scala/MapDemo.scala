/**
  * @author quyf 
  * @date 2019/3/26 8:18 
  */
object MapDemo {

  def main(args: Array[String]): Unit = {
    var scores = Map("tom"->80, "quyf"->100, "andy"->99);
    
    //scores.foreach(println)
    
    println(scores.get("quyf").get)
    println(scores("quyf"))
    println(scores("andy"))
    println(scores.get("xx"))
    println(scores.getOrElse("XX",60))
    //可变的map才能更新 MutableMap
    //scores("quyf")=1001
    println(scores.get("quyf").get)
  }
}
