import breeze.linalg._

object practical_6 {
  def main(args: Array[String]): Unit = {

    // Create a 4x4 matrix
    val matrix = DenseMatrix(
      (1, 2, 3, 4),
      (5, 6, 7, 8),
      (9, 10, 11, 12),
      (13, 14, 15, 16)
    )

    println(s"Original Matrix:\n$matrix")

    // Slice a sub-matrix: rows 1 to 2, columns 1 to 3 (0-based indexing)
    val subMatrix = matrix(1 to 2, 1 to 3)

    println(s"\nSub-Matrix (rows 1-2, cols 1-3):\n$subMatrix")

    // Row sums
    val rowSums = sum(subMatrix(*, ::))
    println(s"\nRow Sums: $rowSums")

    // Column sums
    val colSums = sum(subMatrix(::, *))
    println(s"Column Sums: $colSums")
  }
}