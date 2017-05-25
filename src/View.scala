
import javax.swing._
import java.awt._
import java.util.Random

/**
  * Created by Giacomo on 22/05/2017.
  */
object View {
  private val RIGHT_TO_LEFT = false
  private val WIDTH = 300
  private val HEIGHT = 100
}

class View() extends JFrame {
  this.init()
  myController = new Controller(questionLabel, startButton)
   var startButton: JButton = _
   var questionLabel: JLabel = _
   var myController: Controller = _
   var menubar: JMenuBar = _
   var item: JMenuItem = _
   var item2: JMenuItem = _
   var item3: JMenuItem = _
   var file:JMenu = _

  private def init() = {
    this.setTitle("Night before the exam")
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    addComponentsToPane(this.getContentPane)
    this.pack()
    this.setLocationRelativeTo(null)
    this.setVisible(true)
  }

  private def addComponentsToPane(pane: Container) = {
    if (!pane.getLayout.isInstanceOf[BorderLayout]) {
      pane.add(new JLabel("Container doesn't use BorderLayout!"))
    }
    if (View.RIGHT_TO_LEFT) pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT)
    startButton = new JButton("Ask")
    pane.add(startButton, BorderLayout.PAGE_END)
    questionLabel = new JLabel("Ready?", SwingConstants.CENTER)
    questionLabel.setPreferredSize(new Dimension(View.WIDTH, View.HEIGHT))
    pane.add(questionLabel, BorderLayout.CENTER)
    createMenuBar()
    pane.add(menubar, BorderLayout.BEFORE_FIRST_LINE)
    createListeners()
  }

  private def createMenuBar() = {
    menubar = new JMenuBar
    file = new JMenu("Menu")
    menubar.add(file)
    item = new JMenuItem("Question set")
    file.add(item)
    item2 = new JMenuItem("Export DB")
    file.add(item2)
    item3 = new JMenuItem("Quit")
    file.add(item3)
  }

  private def createListeners() = {

    import java.awt.event._

    startButton.addActionListener((e: ActionEvent) => {
      def foo(e: ActionEvent) = {
        val question = myController.ask(generateRandomNumber)
        questionLabel.setText(question)
      }

      foo(e)
    })
    item.addActionListener((e: ActionEvent) => {
      def foo(e: ActionEvent) =
        new QuestionView(600, 400, this)

      foo(e)
    })
    item2.addActionListener((e: ActionEvent) => {
      def foo(e: ActionEvent) =
        System.out.println("item2 pressed")

      foo(e)
    })
    item3.addActionListener((e: ActionEvent) => {
      def foo(e: ActionEvent) =
        System.exit(-1)

      foo(e)
    })
  }

  private def generateRandomNumber = Math.abs(new Random().nextInt(myController.getDB.size - 1))

  def refreshData(): Unit = {
    questionLabel.setText("Ready?")
    myController = new Controller(questionLabel, startButton)
  }
}