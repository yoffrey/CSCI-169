object HW5Chen extends App{
  def isPrime(x: Int): Boolean = {
    def helper(n: Int): Boolean = {
      if (n > math.sqrt(x)) return true
      if (x % n == 0) return false
      helper(n+1)
    }
    if (x < 2) return false
    helper(2)
  }

  println(isPrime(5))
  println(isPrime(8))

  def add_fth(f: Int => Int, x: Int): Int = {
    println(x)
    if (x < 1) 0
    else x + add_fth(f, f(x))
  }

  def f(x: Int): Int = {
    x - 3
  }

  println(add_fth(f, 10))

  def add(x: Int, y: Int): Int = x + y

  def square(x: Int): Int = x * x

  def multiply(x: Int, y: Int): Int = x * y

  def apply_combine(f: Int => Int, g: (Int, Int) => Int, x: Int): Int = {
    if (x == 1) f(1)
    else g(f(x), apply_combine(f, g, x - 1))
  }

  println(apply_combine(square, add, 4))
  println(apply_combine(identity, multiply, 4))
}
