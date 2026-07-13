import breeze.linalg._
object practical_7{
  def main(args: Array[String]): Unit = {
    // Create two matrices of same size
    val mat1 = DenseMatrix((1.0, 2.0), (3.0, 4.0))
    val mat2 = DenseMatrix((5.0, 6.0), (7.0, 8.0))
    // Element-wise operations
    val addition = mat1 + mat2
    val subtraction = mat1 - mat2
    val multiplication = mat1 * mat2 // Element-wise multiplication
    val division = mat1 / mat2 // Element-wise division
    // Print results
    println("Matrix 1:")
    println(mat1)
    println("\nMatrix 2:")
    println(mat2)
    println("\nAddition:")
    println(addition)
    println("\nSubtraction:")
    println(subtraction)
    println("\nElement-wise Multiplication:")
    println(multiplication)
    println("\nElement-wise Division:")
    println(division)
  }
}