object HW9P2Chen extends App {
  def square(x: Int): Int = x * x
  def f(b: => Boolean)(x: => Any, y: => Any): Any = {
    if (b) x else y
  }

  println(f(5 < 8)(
    {
      println("execute first expression");
      square(2)
    },
    {
      println("execute second expression");
      4 + 3 * 2
    }))
  println(f(10 < 8)(
    {
      println("execute first expression");
      square(2)
    },
    {
      println("execute second expression");
      4 + 3 * 2
    }))

}
