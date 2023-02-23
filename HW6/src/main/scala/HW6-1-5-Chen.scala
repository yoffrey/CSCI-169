import scala.::

object HW6 extends App {
  def ls = 7 :: 2 :: 5 :: 1 :: Nil
  def add(x: Int, y: Int) = x + y
  def mul(x: Int, y: Int): Int = x * y
  def reduce(f: (Int, Int) => Int, xs: List[Int]): Int = {
    if (xs.tail == Nil) xs.head
    else {
      reduce(f, f(xs.head, xs.tail.head) :: xs.tail.tail)
    }
  }
  println(reduce(mul, ls))
  println(reduce(add, ls))

  def xs = 3 :: 8 :: 1 :: 5 :: Nil
  def ys = 12 :: 6 :: 23 :: 1 :: 8 :: 4 :: Nil
  def combine(xs: List[Int], ys: List[Int], f: (Int, Int) => Int): List[Int] = {
    if (xs.isEmpty || ys.isEmpty) Nil
    else {
      f(xs.head, ys.head) :: combine(xs.tail, ys.tail, f)
    }
  }
  println(combine(xs, ys, add))
  println(combine(xs, ys, mul))

  def curriedReduce(f: (Int, Int) => Int) = {
    def inner(xs: List[Int]): Int = {
      if (xs.tail == Nil) xs.head
      else f(xs.head, inner(xs.tail))
    }
    inner(_)
  }
  println(curriedReduce(mul)(ls))
  println(curriedReduce(add)(ls))

  def curriedCombine(f: (Int, Int) => Int) = {
    def inner(xs: List[Int], ys: List[Int]): List[Int] = {
      if (xs.isEmpty || ys.isEmpty) Nil
      else f(xs.head, ys.head) :: inner(xs.tail, ys.tail)
    }
    inner(_, _)
  }

  println(curriedCombine(add)(xs, ys))
  println(curriedCombine(mul)(xs, ys))

  def curryInnerCombine(f: (Int, Int) => Int): List[Int] => List[Int] => List[Int] = {
    def firstInner(xs: List[Int]): List[Int] => List[Int] = {
      def secondInner(ys: List[Int]): List[Int] = {
        if (xs.isEmpty || ys.isEmpty) Nil
        else f(xs.head, ys.head) :: firstInner(xs.tail)(ys.tail)
      }
      secondInner(_)
    }
    firstInner(_)
  }

  println(curryInnerCombine(add)(xs)(ys))
  println(curryInnerCombine(mul)(xs)(ys))
}

