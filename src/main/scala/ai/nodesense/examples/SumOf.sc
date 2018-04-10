object SumOf {
  def sum (f: Int => Int, a: Int, b: Int):Int =
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)

  def id(n: Int): Int = n
  def sq(n: Int): Int = n * n
  def cube(n: Int): Int = n * n * n

  def sumSq(a: Int, b: Int)=sum(sq, a, b)
  def sumCube(a: Int, b: Int) = sum(cube, a, b)

  sumSq(0, 2)
  sumCube(0, 2)





}