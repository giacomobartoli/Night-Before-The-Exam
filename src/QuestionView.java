/**
 * Created by Giacomo on 23/05/2017.
 */

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class QuestionView extends JPanel {

    private static final String DB_NAME = "questionDB.question";

    private JFrame frame;
    private JTextArea textarea;
    private JButton saveButton;
    private JLabel label;
    private View previousView;
    private Utils myUtil = new Utils();

    public QuestionView(int width,int height, View view) {

        previousView=view;
        previousView.setEnabled(false);
        initFrame(width,height);

        textarea = new JTextArea(width,height);
        saveButton = new JButton ("Save");
        label = new JLabel ("Each question for row",SwingConstants.CENTER);

        setPreferredSize (new Dimension (681, 420));
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout (layout);

        add (textarea, BorderLayout.CENTER);
        add (saveButton, BorderLayout.SOUTH);
        add (label, BorderLayout.NORTH);

        if(myUtil.checkIfDbExists()){ myUtil.loadDB(textarea); }
        createListeners();

    }

    private void initFrame(int width,int height){
        frame = new JFrame ("DB configuration");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);
    }


    private void createListeners(){
        saveButton.addActionListener(e->{

            File fileName = new File(DB_NAME);
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                textarea.write(outFile);
                this.setVisible(false);
                frame.setVisible(false);
                previousView.setEnabled(true);
                previousView.refreshData();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (outFile != null) {
                    try {
                        outFile.close();
                    } catch (IOException ex) {
                        System.out.println("Error while writing the db file!");
                    }
                }
            }
        });
    }

}
