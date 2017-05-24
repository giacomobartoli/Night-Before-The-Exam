import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Giacomo on 22/05/2017.
 */
public class View extends JFrame{

    private static boolean RIGHT_TO_LEFT = false;
    private static int WIDTH = 300;
    private static int HEIGHT = 100;

    private JButton startButton;
    private JLabel questionLabel;
    private Controller myController;
    private JMenuBar menubar;
    private JMenuItem item;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenu file;

    public View() {
        this.init();
        myController=new Controller(questionLabel,startButton);
    }

    private void init() {
        this.setTitle("Night before the exam");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(this.getContentPane());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {

        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        startButton = new JButton("Ask");
        pane.add(startButton, BorderLayout.PAGE_END);
        questionLabel = new JLabel("Ready?",SwingConstants.CENTER);
        questionLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pane.add(questionLabel, BorderLayout.CENTER);
        createMenuBar();
        pane.add(menubar,BorderLayout.BEFORE_FIRST_LINE);
        createListeners();

    }

    private void createMenuBar(){
        menubar = new JMenuBar();
        file = new JMenu("Menu");
        menubar.add(file);
        item = new JMenuItem("Question set");
        file.add(item);
        item2 = new JMenuItem("Export DB");
        file.add(item2);
        item3 = new JMenuItem("Quit");
        file.add(item3);
    }

    private void createListeners(){
        startButton.addActionListener(e ->  {
                String question = myController.ask(generateRandomNumber());
                questionLabel.setText(question);
        });
        item.addActionListener(e ->{
            new QuestionView(600,400,this);
        });
        item2.addActionListener(e ->{
            System.out.println("item2 pressed");
        });
        item3.addActionListener(e ->{
            System.exit(-1);
        });
    }

    private int generateRandomNumber(){
        return Math.abs(new Random().nextInt(myController.getDB().size()-1));
    }

    public void refreshData(){
        questionLabel.setText("Ready?");
        myController=new Controller(questionLabel,startButton);
    }
}
