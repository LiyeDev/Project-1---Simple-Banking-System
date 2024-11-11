import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    // Constructor for BankAccount
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0; // Initial balance is 0
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }

    // Method to get account number
    public String getAccountNumber() {
        return accountNumber;
    }
}

class Customer {
    private String name;
    private BankAccount bankAccount;

    // Constructor for Customer
    public Customer(String name) {
        this.name = name;
    }

    // Method to create a BankAccount
    public void createAccount(String accountNumber) {
        bankAccount = new BankAccount(accountNumber);
        System.out.println("Bank account created for " + name + " with account number: " + accountNumber);
    }

    // Method to get bank account
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    // Method to get customer name
    public String getName() {
        return name;
    }
}

public class BankingSystem {
    private static Map<String, Customer> customers = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String customerName;
        
        System.out.print("Enter your name: ");
        customerName = scanner.nextLine();

        // Create a new customer
        Customer customer = new Customer(customerName);
        customers.put(customerName, customer);

        String choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1: Create Account");
            System.out.println("2: Deposit");
            System.out.println("3: Withdraw");
            System.out.println("4: Check Balance");
            System.out.println("5: Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    customer.createAccount(accountNumber);
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (customer.getBankAccount() != null) {
                        customer.getBankAccount().deposit(depositAmount);
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    if (customer.getBankAccount() != null) {
                        customer.getBankAccount().withdraw(withdrawAmount);
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;
                case "4":
                    if (customer.getBankAccount() != null) {
                        System.out.println("Current balance: $" + customer.getBankAccount().getBalance());
                    } else {
                        System.out.println("You need to create an account first!");
                    }
                    break;
                case "5":
                    System.out.println("Exiting the banking system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (!choice.equals("5"));
        
        scanner.close();
    }
}
