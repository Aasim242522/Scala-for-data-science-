import scala.util.Random

// You should wrap your code in an 'object'
object VarianceCalculator {
  def main(args: Array[String]): Unit = {
    val data = List.fill(10)(Random.nextInt(100))
    val mean = data.sum / data.size.toDouble

    val variance = data.map(x => math.pow(x - mean, 2)).sum / data.size
    val stdDev = math.sqrt(variance)

    println(s"Data: $data")
    println(s"Variance: $variance")
    println(s"Standard Deviation: $stdDev")
  }
}
