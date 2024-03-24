import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleBanking extends JFrame implements ActionListener {
    private JTextField amountField;
    private JButton depositButton, withdrawButton, checkBalanceButton;
    private JLabel balanceLabel;
    private double balance = 0.0;

    public SimpleBanking() {
        setTitle("Simple Banking Application");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Center the GUI on the screen
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5)); // Added gap between components
        panel.setBackground(new Color(240, 240, 240)); // Light Gray background

        JLabel amountLabel = new JLabel("Enter Amount:");
        panel.add(amountLabel);

        amountField = new JTextField(10); // Set preferred size for the text field
        panel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        panel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        panel.add(withdrawButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        panel.add(checkBalanceButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            double amount = getAmount();
            if (amount > 0) {
                deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Amount");
            }
        } else if (e.getSource() == withdrawButton) {
            double amount = getAmount();
            if (amount > 0 && amount <= balance) {
                withdraw(amount);
                JOptionPane.showMessageDialog(this, "Withdrawal Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Amount or Insufficient Balance");
            }
        } else if (e.getSource() == checkBalanceButton) {
            checkBalance();
        }
    }

    private double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            return -1; // Return -1 for invalid input
        }
    }

    private void deposit(double amount) {
        balance += amount;
        updateBalanceLabel();
    }

    private void withdraw(double amount) {
        balance -= amount;
        updateBalanceLabel();
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Your Balance is: \u20B9" + balance); // Rupee symbol
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: \u20B9" + balance); // Rupee symbol
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleBanking();
            }
        });
    }
}
