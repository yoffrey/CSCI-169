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

  def l1 = List(1, 3, 5, 2, 4, 10, 15)
  def range(xs: List[Int], start:Int, end:Int): List[Int] = {
    xs match {
      case Nil => Nil
      case head::tail => {
        if (start <= 0 && end>=0) head::range(tail, start-1, end-1) else range(tail, start-1, end-1)
      }
    }
  }
  println(range(l1, 2, 3))

  def average(xs: List[Int]): Int = {
    def inner(xs: List[Int], total: Int): (Int, Int) = {
      xs match {
        case Nil => (total, 0)
        case head::tail => {
          val (sum, count) = inner(tail, total+head)
          (sum, count+1)
        }
      }
    }
    val (sum, count) = inner(xs, 0)
    if (count==0) 0 else sum/count
  }
  println(average(l1))

  val xs1 = List(1, 3, 5, 2, 4, 10, 15)
  def applyrange(xs: List[Int], start: Int, end:Int, f:Int=>Int):List[Int] = {
    xs match {
      case Nil => Nil
      case x::xs => if (start <= 0 && end>=0) f(x)::applyrange(xs, start-1, end-1, f) else x::applyrange(xs, start-1, end-1, f)
    }
  }
  println(applyrange(xs1, 2, 4, x => x * x))

  def applyrangecurried(f: Int=> Int)(xs: List[Int], start: Int, end:Int):List[Int] = {
    xs match {
      case Nil => Nil
      case x :: xs => if (start <= 0 && end >= 0) f(x) :: applyrange(xs, start - 1, end - 1, f) else x :: applyrange(xs, start - 1, end - 1, f)
    }
  }
  println(applyrangecurried(x => x * x)(xs1, 2, 4))
}

