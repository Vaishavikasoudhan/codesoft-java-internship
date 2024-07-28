import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ATM GUI CREATED AND DEVELOPED BY VAISHNAVI KASOUDHAN

public class ATMGUI {
    private BankAccount bankAccount;
    private JFrame frame;
    private JLabel balanceLabel;
    private JTextField amountField;

    public ATMGUI(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("ATM Interface By VAISHNAVI KASOUDHAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background for the frame

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));
        menuPanel.setBackground(new Color(220, 220, 220)); // Lighter gray background for the menu panel

        JButton checkBalanceButton = new JButton("Check Balance");
        styleButton(checkBalanceButton, new Color(100, 150, 250)); // Light blue background for buttons
        checkBalanceButton.addActionListener(new CheckBalanceActionListener());
        menuPanel.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        styleButton(depositButton, new Color(46, 204, 113)); // Green background for buttons
        depositButton.addActionListener(new DepositActionListener());
        menuPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        styleButton(withdrawButton, new Color(231, 76, 60)); // Red background for buttons
        withdrawButton.addActionListener(new WithdrawActionListener());
        menuPanel.add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, new Color(149, 165, 166)); // Silver background for buttons
        exitButton.addActionListener(new ExitActionListener());
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.CENTER);

        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setForeground(new Color(50, 100, 200)); // Dark blue text color
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(balanceLabel, BorderLayout.NORTH);

        amountField = new JTextField();
        amountField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100))); // Dark gray border
        amountField.setBackground(new Color(255, 255, 255)); // White background
        amountField.setHorizontalAlignment(JTextField.CENTER);
        frame.add(amountField, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    // Method to style the buttons with specific background color
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove default focus appearance
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Regular font
    }

    private class CheckBalanceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            balanceLabel.setText("Balance: $" + String.format("%.2f", bankAccount.getBalance()));
        }
    }

    private class DepositActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                bankAccount.deposit(amount);
                balanceLabel.setText("Balance: $" + String.format("%.2f", bankAccount.getBalance()));
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount");
            }
        }
    }

    private class WithdrawActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                bankAccount.withdraw(amount);
                balanceLabel.setText("Balance: $" + String.format("%.2f", bankAccount.getBalance()));
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount");
            }
        }
    }

    private class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance
        ATMGUI atmGUI = new ATMGUI(account);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient balance");
        } else {
            balance -= amount;
        }
    }
}
