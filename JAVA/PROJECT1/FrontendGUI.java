import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrontendGUI extends JFrame implements ActionListener {

    int[][] arr = {
            {2,4,6,8,10},
            {1,2,4,8,16},
            {5,10,15,20,25},
            {3,6,12,24,48},
            {7,14,21,28,35}
    };

    int index = 0;
    int score = 0;

    JLabel questionLabel;
    JTextField answerField;
    JButton submitButton;
    JLabel resultLabel;
    JLabel scoreLabel;

    public FrontendGUI() {
        setTitle("Number Series Quiz");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        answerField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("Score: 0");

        submitButton.addActionListener(this);

        add(questionLabel);
        add(answerField);
        add(submitButton);
        add(resultLabel);
        add(scoreLabel);

        loadQuestion();

        setVisible(true);
    }

    public void loadQuestion() {
        if(index < arr.length){
            StringBuilder q = new StringBuilder();
            for(int i = 0; i < arr[index].length - 1; i++){
                q.append(arr[index][i]).append(" ");
            }
            q.append("?");

            questionLabel.setText("Q" + (index + 1) + ": " + q.toString());
            answerField.setText("");
            resultLabel.setText("");
        } else {
            questionLabel.setText("Quiz Completed!");
            resultLabel.setText("Final Score: " + score);
            submitButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int userAnswer = Integer.parseInt(answerField.getText());

            // Using your existing backend method
            if(project.Ravi(arr[index], userAnswer)){
                resultLabel.setText("Correct!");
                score++;
            } else {
                int correct = arr[index][arr[index].length - 1];
                resultLabel.setText("Wrong! Ans: " + correct);
            }

            scoreLabel.setText("Score: " + score);
            index++;

            loadQuestion();

        } catch (Exception ex) {
            resultLabel.setText("Enter valid number!");
        }
    }

    public static void main(String[] args) {
        new FrontendGUI();
    }
}