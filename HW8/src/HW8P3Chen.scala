import java.security.KeyStore.TrustedCertificateEntry

object HW8P3Chen extends App {
  val t = new Node(5, Leaf, Leaf)
  val t2 = new Node(7, t, Leaf)
  val t3 = t2 insert 3 insert 9
  val t4 = t3 insert 12
  // u and u2 are made the same way as t and t2, just with different values
  val u = new Node(7, Leaf, Leaf)
  val u2 = new Node(1, u, Leaf)
/*  println(t3 member 9)
  println(t3 member 8)
  println(t3 equals t2)

          7
        /   \
       5     9
      / \   / \
     3   L  L  12
   /  \        / \
  L    L       L  L
*/

  println(t4.depth) // Should be 4
  println(t4.max)   // Should be 12
  println(t exactsubtree u2)
  println(t2 exactsubtree u2)
}

abstract class BSTree { //Can't be instantiated - can't make an object of type BSTree.
  def member(x: Int): Boolean //Tells us if x is in the BSTree
  def insert(x: Int): BSTree
  //creates a new tree that is the same as this one, but with x added
  def equals(other: BSTree): Boolean
  def depth: Int
  def max: Int
  def exactsubtree(that:BSTree):Boolean
}

//Add case in front of all children - allows us to do pattern matching!!
case object Leaf extends BSTree { //Leaf refers to the type AND the single object of that type
  override def toString = "."
  def member(x: Int): Boolean = false
  def insert(x: Int): BSTree = new Node(x, Leaf, Leaf)
  def equals(other: BSTree): Boolean = {
    other match {
      case Leaf => true
      case Node(_, _, _) => false //_ is "don't care"
    }
  }
  def depth: Int = 1
  def max: Int = -1
  def exactsubtree(that:BSTree):Boolean = {
    that match {
      case Leaf => true
      case Node(_, _, _) => false
    }
  }
}

case class Node(value: Int, left: BSTree, right: BSTree) extends BSTree {
  override def toString = "{" + left.toString + value.toString + right.toString + "}"
  def member(x: Int): Boolean = {
    if (x == value) true //BC
    else if (x < value) left.member(x)
    else right.member(x)
  }
  def insert(x: Int): BSTree = {
    if (x == value) this //new Node(value, left, right)
    else if (x < value) new Node(value, left insert x, right)
    else new Node(value, left, right insert x)
  }
  def equals(other: BSTree): Boolean = {
    other match {
      case Leaf => false
      case Node(v, l, r) => (value == v) && (left equals l) && (right equals r)
    } //Above case binds other's value to v, etc - especially useful since parameters can't be accessed like fields
  }
  def depth: Int = {
    def maxDepth(node: BSTree): Int = {
      if (node.isInstanceOf[Node]) {
        val leftDepth = left.depth
        val rightDepth = right.depth
        if (leftDepth > rightDepth) 1 + leftDepth
        else 1 + rightDepth
      }
      else 1
    }
    maxDepth(this)
  }
  def max: Int = math.max(value, right.max)
  def exactsubtree(that:BSTree):Boolean = {
    that match {
      case Leaf => false
      case Node(v, l, r) => (left exactsubtree l) && (right exactsubtree r)
    }
  }
}
