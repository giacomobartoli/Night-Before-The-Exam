import javax.swing._
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

import scala.collection.mutable.ListBuffer

/**
  * Created by Giacomo on 24/05/2017.
  */

object Utils {
  private val DB_QUESTION = "./questionDB.question"
  private val READING_EXCPETION = "Error while reading the source file!"
}

class Utils {

  val file = new File(Utils.DB_QUESTION)

  def loadDB(myArray: ListBuffer[String]): Unit = {
    try {
      var in = new BufferedReader(new FileReader(file))
      var line = ""
      while ( {
        line != null
      }) {
        line = in.readLine
        myArray+=line
      }
    } catch {
      case e: Exception =>
        System.out.println(Utils.READING_EXCPETION)
    }
  }

  def loadDB(textarea: JTextArea): Unit = {
    try {
      var in = new BufferedReader(new FileReader(file))
      var line = in.readLine
      while ( {
        line != null
      }) {
        textarea.append(line + "\n")
        line = in.readLine
      }
    } catch {
      case e: Exception =>
        System.out.println(Utils.READING_EXCPETION)
    }
  }

  def checkIfDbExists: Boolean = new File(Utils.DB_QUESTION).exists
}