class Biguint (digits: List[Int]){
  // Biguint stored as a list of Int
  val list: List[Int] = digits
  // If given a string instead, convert it into list
  def this(s:String) = this({def convert(s:String):List[Int] = {if(s.isEmpty) Nil else convert(s.tail):::List((s.head-'0'))}; convert(s)})
  // If no arguments, 0::Nil
  def this() = this(List(0))

  def +(other: Biguint): Biguint = {
    def addHelper(xs: List[Int], ys: List[Int], carry: Int, result: List[Int]): List[Int] = (xs, ys) match {
      case (Nil, Nil) => if (carry == 0) result else carry :: result
      case (x :: xs, Nil) => addHelper(xs, Nil, (x + carry) / 10, ((x + carry) % 10) :: result)
      case (Nil, y :: ys) => addHelper(Nil, ys, (y + carry) / 10, ((y + carry) % 10) :: result)
      case (x :: xs, y :: ys) => addHelper(xs, ys, (x + y + carry) / 10, ((x + y + carry) % 10) :: result)
    }

    val combined = addHelper(this.list, other.list, 0, Nil)
    new Biguint(combined.reverse)
  }

  def print(): Unit = {
    println(list)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val a = new Biguint(List(5, 1, 7, 3))
    val b = new Biguint("19283")
    val c = new Biguint()

    a.print()
    b.print()
    c.print()

    // List(5, 1, 7, 3) = 3715
    // 3715 + 19283 = 22998
    // Should be: List(8, 9, 9, 2, 2)
    val d = a + b

    // 19283 + 0 = 19283
    // Should be List(3, 8, 2, 9, 1)
    val e = b + c

    d.print()
    e.print()
  }
}

