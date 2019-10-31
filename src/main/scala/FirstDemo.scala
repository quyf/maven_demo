/**
  * @author quyf 
  * @date 2019/3/23 17:48 
  */
object FirstDemo {

  def main(args: Array[String]): Unit = {
    println("hello scala");
    println(myFactot(5))
  }
  
  def myFactot(x:Int):Int = {
    if(x==1){
      1
    }else{
      x*myFactot(x-1)
    }
  }
}
