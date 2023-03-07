object HW8P12Chen extends App {
  // Problem 1
  def ls1 = 1::2::3::4::Nil
  def ls2 = 7::8::9::10::11::12::Nil
  def alternate(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1, l2) match {
      case (Nil, Nil) => Nil
      case (Nil, y::ys) => y :: alternate(Nil, ys)
      case (x::xs, Nil) => x :: alternate(xs, Nil)
      case (x::xs, y::ys) => x :: y :: alternate(xs, ys)
    }
  }
  println(alternate(ls1, ls2))

  // Problem 2
  def reduce[T <: AnyRef](f: (T, T) => T, xs: List[T]): T = {
    xs match {
      case x::Nil => x
      case x::xs => f(x, reduce(f, xs))
    }
  }
}
