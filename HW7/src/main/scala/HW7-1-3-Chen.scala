object HW7 extends App{
  def ls = 7 :: 2 :: 5 :: 1 :: Nil
  def add(x: Int, y: Int) = x + y
  def mul(x: Int, y: Int): Int = x * y
  def xs = 3 :: 8 :: 1 :: 5 :: Nil
  def ys = 12 :: 6 :: 23 :: 1 :: 8 :: 4 :: Nil

  // Problem 1
  def curriedReduce(f: (Int, Int) => Int)(xs: List[Int]): Int = {
    if (xs.tail == Nil) xs.head
    else curriedReduce(f)(f(xs.head, xs.tail.head) :: xs.tail.tail)
  }

  println(curriedReduce(mul)(ls))
  println(curriedReduce(add)(ls))

  def curryInnerCombine(f: (Int, Int) => Int)(xs: List[Int])(ys: List[Int]): List[Int] = {
    if (xs.isEmpty || ys.isEmpty) Nil
    else f(xs.head, ys.head) :: curryInnerCombine(f)(xs.tail)(ys.tail)
  }

  println(curryInnerCombine(add)(xs)(ys))
  println(curryInnerCombine(mul)(xs)(ys))

  // Problem 3
  def findlast(xs: List[Int], x: Int): Int = xs match {
    // Base case, start with -1
    case Nil => -1

    case h :: t =>
      // recurse until you get to base case
      val index = findlast(t, x)
      // increment after finding h==x
      if (index != -1) index + 1
      // Once you find h==x, you can start incrementing from 0
      else if (h == x) 0
      // keep -1
      else -1
  }
  // 12 :: 6 :: 23 :: 1 :: 8 :: 4 :: Nil 8

  println(findlast(ls, 5))
  println(findlast(ys, 8))
  println(findlast(ys, 10))
  println(findlast(List(), 10))
}
