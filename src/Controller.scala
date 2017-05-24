import javax.swing._
import scala.collection.mutable.ListBuffer

/**
  * Created by Giacomo on 22/05/2017.
  */
class Controller(val label: JLabel, val startButton: JButton) {

  private var myArray = new ListBuffer[String]();

  if (!new Utils().checkIfDbExists) {
    label.setText("<html><center>Ready?<br> <b>Please configure questionDB</b>:<br><br> Menu -> Question set</center></html>")
    startButton.setEnabled(false)
  }
  else {
    startButton.setEnabled(true)
    new Utils().loadDB(myArray)
  }

  def ask(i: Int): String = myArray(i)
  def getDB: ListBuffer[_] = myArray
}

object Controller {
  def apply(label: JLabel,startButton: JButton): Unit = {

  }
}