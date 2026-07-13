import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try
import scala.math._

object practical_8 {
  def main(args: Array[String]): Unit = {
    // Updated file path
    val reader = CSVReader.open(new File("Salary_Dataset_DataScienceLovers.csv"))
    val allRows = reader.allWithHeaders()
    reader.close()

    // Explicitly targeting the numeric columns found in the new dataset
    val numericCols = List("Rating", "Salary", "Salaries Reported")

    println(f"Numeric Columns: ${numericCols.mkString(", ")}\n")

    for (col <- numericCols) {
      val values = allRows.flatMap(row =>
        Try(row(col).toDouble).toOption)

      if (values.nonEmpty) {
        val count = values.size
        val mean = values.sum / count
        val minVal = values.min
        val maxVal = values.max
        val stdDev = sqrt(values.map(x => pow(x - mean, 2)).sum / count)

        println(s"Column: $col")
        println(f" Count   : $count")
        println(f" Mean    : $mean%.2f")
        println(f" Min     : $minVal%.2f")
        println(f" Max     : $maxVal%.2f")
        println(f" Std Dev : $stdDev%.2f\n")
      } else {
        println(s"Column: $col - No numeric data found.\n")
      }
    }
  }
}