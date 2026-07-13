import com.github.tototoshi.csv._
import java.io.File

object practical_10 {
  def main(args: Array[String]): Unit = {
    // Open the salary dataset
    val reader = CSVReader.open(new File("Salary_Dataset_DataScienceLovers.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Define threshold for Salary
    val threshold = 10

    // Filter rows where "Salary" > threshold
    val filteredRows = data.filter { row =>
      row.get("Salary").exists(value => value.toIntOption.exists(_ > threshold))
    }

    println(s"\nTotal Rows with Salary > $threshold: ${filteredRows.length}\n")

    // Print each filtered row
    filteredRows.foreach { row =>
      println(row.values.mkString(", "))
    }
  }
}
