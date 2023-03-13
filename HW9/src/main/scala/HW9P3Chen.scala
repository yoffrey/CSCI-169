object HW9P3Chen extends App{
  //the function f returns true for elements of the set and false for all other numbers
  class Set(f: Int => Boolean) {
    //returns true if elem is in the set and false for all other numbers
    def contains(elem: Int): Boolean = f(elem)

    //Returns the union of this set and t.
    def \/(t: Set): Set = new Set(x => this.contains(x) || t.contains(x))

    //Returns the intersection of this set and t
    def /\(t: Set): Set = new Set(x => this.contains(x) && t.contains(x))

    //Returns the difference of this set and t
    def -(t: Set): Set = new Set(x => this.contains(x) && !t.contains(x))


    // Returns a new set that consists of the elements of s that satisfy the predicate.
    def filter(p: Int => Boolean): Set = new Set(x => this.contains(x) && p(x))

    //Returns true if the predicate is true for all elements of this set, and false otherwise.
    // In order to make it possible to implement this function, we will consider a predicate true for all integers if it is true for integers from -1000 to 1000.
    def forall(p: Int => Boolean): Boolean = {
      def iter(a: Int): Boolean = {
        if (a > 1000) true
        else if (!this.contains(a) && p(a)) false
        else iter(a + 1)
      }
      iter(-1000)
    }
  }

  def setA = new Set(x => x<10)
  println("Test Contains")

  println(setA.contains(11)) // should be false
  println(setA.contains(8)) // should be true
  def setB = new Set(x => x>15)
  def setC = new Set(x => x>5)

  println("Test Union")
  def setAunionB = setA \/ setB
  println(setAunionB.contains(8)) // should be true
  println(setAunionB.contains(13)) // should be false
  println(setAunionB.contains(18)) // should be true

  println("Test Intersect")
  def setAintersectC = setA /\ setC
  println(setAintersectC.contains(3)) // should be false
  println(setAintersectC.contains(8)) // should be true
  println(setAintersectC.contains(13)) // should be false

  println("Test Difference")
  def setAdiffC = setA - setC
  println(setAdiffC.contains(3)) // should be true
  println(setAdiffC.contains(8)) // should be false
  println(setAdiffC.contains(13)) // should be false

  println("Test Filter")
  def setAfilter = setA.filter(x => x>5)
  println(setAfilter.contains(3)) // should be false
  println(setAfilter.contains(8)) // should be true
  println(setAfilter.contains(13)) // should be false

  println("Test For All")
  println(setA.forall(x => x>5)) // should be false
  println(setA.forall(x => x<5)) // should be true
}
