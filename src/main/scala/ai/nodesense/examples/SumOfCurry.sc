object SubOfCurry {

  def sum(f: Int => Int): (Int, Int) => Int = {
     def sumF(a: Int, b: Int): Int = {
        return a + b;
     }

    sumF;
  }

  def sumId = sum(x => x)
  def sumSq = sum(x => x * x)
  def sumCube  = sum(x => x * x * x);

  sumId(0, 2)
  sumSq(0, 2)
  sumCube(0, 2)
}