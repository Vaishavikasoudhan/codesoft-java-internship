import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// STUDENT GRADE CALCULATOR BY VAISHNAVI KASOUDHAN
public class GradeCalculator extends JFrame implements ActionListener {
    JLabel[] labels;
    JTextField[] textFields;
    JButton calculateButton;

    public GradeCalculator() {
        String[] subjects = {"Java", "Python", "JavaScript", "C++", "Swift"};

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        labels = new JLabel[5];
        textFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            textFields[i] = new JTextField(10);
            inputPanel.add(labels[i]);
            inputPanel.add(textFields[i]);
        }

        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel instructionLabel = new JLabel("Instructions: Enter your marks for each subject and click the Calculate button to get your total marks, average percentage, and grade.");
        infoPanel.add(instructionLabel);
        JLabel developerLabel = new JLabel("Developed by: VAISHNAVI KASOUDHAN");
        infoPanel.add(developerLabel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        getContentPane().add(mainPanel);
        
        setTitle("Student Grade Calculator");
        pack();  // Adjust frame size based on components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = 0;

            for (int i = 0; i < 5; i++) {
                String marksText = textFields[i].getText();
                if (!marksText.isEmpty()) {
                    int marks = Integer.parseInt(marksText);
                    totalMarks += marks;
                    numSubjects++;
                }
            }

            if (numSubjects > 0) {
                double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
                String grade = calculateGrade(averagePercentage);

                JOptionPane.showMessageDialog(this, "Total Marks: " + totalMarks +
                        "\nAverage Percentage: " + String.format("%.2f", averagePercentage) + "%" +
                        "\nGrade: " + grade);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter marks for at least one subject.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 95) {
            return "O";
        } else if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeCalculator();
            }
        });
    }
}
