/**
  * Created by Giacomo on 23/05/2017.
  *//**
  * Created by Giacomo on 23/05/2017.
  */

import java.awt._
import java.io._
import javax.swing._
import java.awt.event._

object QuestionView {
  private val DB_NAME = "questionDB.question"
  private val TOP_LABEL = "Each question for row"
  private val READING_EXCPETION = "Error while reading the DB!"

}

class QuestionView(width: Int, height: Int, var previousView: View) extends JPanel {

  private var frame: JFrame = _
  private var textarea: JTextArea = _
  private var saveButton: JButton = _
  private var label: JLabel = _
  private val myUtil: Utils = new Utils
  private var outFile: BufferedWriter = _

  previousView.setEnabled(false)
  initFrame(width, height)

  textarea = new JTextArea(width, height)
  saveButton = new JButton("Save")
  label = new JLabel(QuestionView.TOP_LABEL, SwingConstants.CENTER)
  setPreferredSize(new Dimension(681, 420))
  setLayout(new BorderLayout(0, 0))
  add(textarea, BorderLayout.CENTER)
  add(saveButton, BorderLayout.SOUTH)
  add(label, BorderLayout.NORTH)
  if (myUtil.checkIfDbExists) myUtil.loadDB(textarea)
  createListeners()

  private def initFrame(width: Int, height: Int) = {
    frame = new JFrame("DB configuration")
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.getContentPane.add(this)
    frame.pack()
    frame.setSize(width, height)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
  }

  private def createListeners() = saveButton.addActionListener((e: ActionEvent) => {
    def foo(e: ActionEvent) = {
      val fileName = new File(QuestionView.DB_NAME)

      try {
        outFile = new BufferedWriter(new FileWriter(fileName))
        textarea.write(outFile)
        this.setVisible(false)
        frame.setVisible(false)
        previousView.setEnabled(true)
        previousView.refreshData()
      } catch {
        case ex: IOException =>
          ex.printStackTrace()
      } finally if (outFile != null) try outFile.close()
      catch {
        case ex: IOException =>
          System.out.println(QuestionView.READING_EXCPETION)
      }
    }

    foo(e)
  })
}