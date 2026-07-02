object IrisStatistics {
  def main(args: Array[String]): Unit = {
    val sepalLengths = List(5.1, 4.9, 4.7, 4.6, 5.0, 5.4, 4.6, 5.0, 4.4, 4.9, 5.4, 4.8, 4.8, 4.3, 5.8)

    // 1. Mean
    val mean = sepalLengths.sum / sepalLengths.size

    // 2. Median
    val sorted = sepalLengths.sorted
    val median = if (sorted.size % 2 == 0) {
      (sorted(sorted.size / 2 - 1) + sorted(sorted.size / 2)) / 2.0
    } else {
      sorted(sorted.size / 2)
    }

    // 3. Mode
    // Group by value, count the size of each group, and find the max frequency
    val frequencies = sepalLengths.groupBy(identity).view.mapValues(_.size).toMap
    val maxFreq = frequencies.values.max
    val modes = frequencies.filter(_._2 == maxFreq).keys.toList

    // Print Results
    println(f"Mean: $mean%.2f")
    println(s"Median: $median")
    println(s"Mode(s): ${modes.mkString(", ")}")
  }
}