import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try

object practical_9 {
  def main(args: Array[String]): Unit = {
    // 1. Setup reader with Semicolon delimiter
    val inputFile = new File("Peptamant P Sales per Country.csv")

    // Explicitly define the CSV format with semicolon as delimiter
    implicit object MyFormat extends DefaultCSVFormat {
      override val delimiter = ';'
    }

    val reader = CSVReader.open(inputFile)
    val allRows = reader.all()
    reader.close()

    // 2. Separate Header (the first 10 rows) and Data
    val headerRows = allRows.take(10)
    val dataRows = allRows.drop(10)

    // 3. Define numeric column indices (based on your structure, columns 2-7)
    val numericIndices = Seq(2, 3, 4, 5, 6, 7)

    // Helper to sanitize: replace comma decimal with dot
    def toDouble(s: String): Option[Double] =
      Try(s.replace(",", ".").toDouble).toOption

    // 4. Compute means
    val stats = numericIndices.map { idx =>
      val values = dataRows.map(row => row.lift(idx).getOrElse(""))
      val validNumbers = values.flatMap(toDouble)
      val mean = if (validNumbers.nonEmpty) validNumbers.sum / validNumbers.size else 0.0
      (idx, mean)
    }.toMap

    // 5. Replace missing values and format back to original style
    val cleanedData = dataRows.map { row =>
      numericIndices.foldLeft(row) { (accRow, idx) =>
        val value = accRow.lift(idx).getOrElse("")
        if (toDouble(value).isEmpty) {
          accRow.updated(idx, f"${stats(idx)}%.2f".replace(".", ","))
        } else accRow
      }
    }

    // 6. Save results
    val outputFile = new File("sales_cleaned.csv")
    val writer = CSVWriter.open(outputFile)
    headerRows.foreach(writer.writeRow) // Write original headers
    cleanedData.foreach(writer.writeRow)
    writer.close()

    println("Cleaned data successfully saved to sales_cleaned.csv")

    // 7. Automatically open the file
    try {
      val fileToOpen = new File("sales_cleaned.csv")
      if (fileToOpen.exists()) {
        java.awt.Desktop.getDesktop.open(fileToOpen)
      }
    } catch {
      case e: Exception => println("Could not automatically open the file: " + e.getMessage)
    }
  }
}