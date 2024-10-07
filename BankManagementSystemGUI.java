import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Base class representing a generic bank account
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposited " + amount + " successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdraw " + amount + " successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount or insufficient balance. Withdrawal failed.");
        }
    }

    public void displayAccount() {
        JOptionPane.showMessageDialog(null, "Account Number: " + accountNumber +
                "\nAccount Holder: " + accountHolder + "\nBalance: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class BankManagementSystem {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void openAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            JOptionPane.showMessageDialog(null, "Account already exists.");
        } else {
            BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
            accounts.put(accountNumber, account);
            JOptionPane.showMessageDialog(null, "Account created successfully.");
        }
    }

    public void depositMoney(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).deposit(amount);
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
        }
    }

    public void withdrawMoney(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).withdraw(amount);
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
        }
    }

    public void displayAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).displayAccount();
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
        }
    }
}

public class BankManagementSystemGUI {
    private JFrame frame;
    private JTextField accountNumberField;
    private JTextField accountHolderField;
    private JTextField initialBalanceField;
    private JTextField amountField;
    private BankManagementSystem bankSystem;

    public BankManagementSystemGUI() {
        bankSystem = new BankManagementSystem();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Bank Management System");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(10, 10, 120, 25);
        frame.getContentPane().add(lblAccountNumber);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(140, 10, 150, 25);
        frame.getContentPane().add(accountNumberField);
        accountNumberField.setColumns(10);

        JLabel lblAccountHolder = new JLabel("Account Holder:");
        lblAccountHolder.setBounds(10, 45, 120, 25);
        frame.getContentPane().add(lblAccountHolder);

        accountHolderField = new JTextField();
        accountHolderField.setBounds(140, 45, 150, 25);
        frame.getContentPane().add(accountHolderField);
        accountHolderField.setColumns(10);

        JLabel lblInitialBalance = new JLabel("Initial Balance:");
        lblInitialBalance.setBounds(10, 80, 120, 25);
        frame.getContentPane().add(lblInitialBalance);

        initialBalanceField = new JTextField();
        initialBalanceField.setBounds(140, 80, 150, 25);
        frame.getContentPane().add(initialBalanceField);
        initialBalanceField.setColumns(10);

        JButton btnOpenAccount = new JButton("Open Account");
        btnOpenAccount.setBounds(10, 115, 280, 25);
        frame.getContentPane().add(btnOpenAccount);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(10, 150, 120, 25);
        frame.getContentPane().add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(140, 150, 150, 25);
        frame.getContentPane().add(amountField);
        amountField.setColumns(10);

        JButton btnDeposit = new JButton("Deposit Money");
        btnDeposit.setBounds(10, 185, 280, 25);
        frame.getContentPane().add(btnDeposit);

        JButton btnWithdraw = new JButton("Withdraw Money");
        btnWithdraw.setBounds(10, 220, 280, 25);
        frame.getContentPane().add(btnWithdraw);

        JButton btnDisplayAccount = new JButton("Display Account");
        btnDisplayAccount.setBounds(10, 255, 280, 25);
        frame.getContentPane().add(btnDisplayAccount);

        btnOpenAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String accountHolder = accountHolderField.getText();
                double initialBalance = Double.parseDouble(initialBalanceField.getText());
                bankSystem.openAccount(accountNumber, accountHolder, initialBalance);
            }
        });

        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(amountField.getText());
                bankSystem.depositMoney(accountNumber, amount);
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(amountField.getText());
                bankSystem.withdrawMoney(accountNumber, amount);
            }
        });

        btnDisplayAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                bankSystem.displayAccount(accountNumber);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BankManagementSystemGUI window = new BankManagementSystemGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}