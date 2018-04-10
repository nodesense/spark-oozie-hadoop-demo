object SubOfAnnonymous {

  def sum(f: Int => Int, a: Int ,b : Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }

  def sumId(a: Int, b: Int): Int = sum(x => x, a, b)
  def sumSq(a: Int, b: Int): Int = sum(x => x * x, a, b)
  def sumCube(a: Int, b: Int): Int = sum(x => x * x * x, a, b);

  sumId(0, 2)
  sumSq(0, 2)
  sumCube(0, 2)
}