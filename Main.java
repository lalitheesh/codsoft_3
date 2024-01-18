import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        if (userAccount.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Invalid withdrawal amount or insufficient balance.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        if (userAccount.deposit(depositAmount)) {
                            System.out.println("Deposit successful.");
                        } else {
                            System.out.println("Invalid deposit amount.");
                        }
                        break;

                    case 3:
                        System.out.println("Your current balance is: " + userAccount.checkBalance());
                        break;

                    case 4:
                        System.out.println("Exiting. Thank you for using the ATM.");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your initial account balance: ");

        try {
            double initialBalance = Double.parseDouble(scanner.nextLine());

            BankAccount userAccount = new BankAccount(initialBalance);
            ATM atm = new ATM(userAccount);

            atm.run();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for the initial balance.");
        }
    }
}
