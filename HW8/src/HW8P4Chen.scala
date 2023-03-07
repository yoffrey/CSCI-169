object HW8P4Chen extends App {
  // Part 1
  val s = setlist(List(1, 2, 3, 4, 8))
  println(s)
  println(s(2))
  println(s(5))
  def setlist(l: List[Int]): Int => Boolean = Set() ++ l

  // Part 2
  def p(x: Int): Boolean = x % 2 == 0
  def pos(x:Int): Boolean = x > 0
  def filter(s: Int=>Boolean, p: Int=>Boolean): Int => Boolean = {
    (x: Int) => p(x) && s(x)
  }
  println(filter(pos, p)(9))
  println(filter(pos, p)(10))

  // Part 3
  val s1 = Set(2, 4, 6, 8, 10)
  val s2 = Set(2, 4, 6, 8, 10, 9)
  def forall(s:Int=>Boolean, p:Int=>Boolean): Boolean= {
    def inner(n: Int): Boolean = {
      if (n > 1000) true
      else if (s(n) && !p(n)) false
      else inner(n + 1)
    }
    inner(-1000)
  }
  println(forall(s1, p))
  println(forall(s2, p))
}
