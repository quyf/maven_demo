/**
  * @author quyf 
  * @date 2019/3/30 15:43 
  */


trait Human{
  val age:Int
  val name:String
}

trait Action{
  def act() :String 
}

trait Acton1{
  def eat():String
}
class Person() extends Human with Action with Acton1 {
  val age = 1;
  val name = "hello"

  override def act(): String =  "hello world"

  override def eat(): String = "aaaaaa"
}
object TraitDemo extends App {
  
  val p = new Person
  println(p.act())
  println(p.eat())
}
