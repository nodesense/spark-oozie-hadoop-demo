def gcd(a: Int, b: Int): Int = {
  if (b == 0) a else gcd(b, a % b)
}

var r = gcd(2,4)
r = gcd(8, 4)
r = gcd(6, 4)
r = gcd(4, 6)
