import java.util.Scanner;

public class BankATM {

    static Scanner sc = new Scanner(System.in);

    static double balance = 10000;
    static int pin = 1234;
    static String accountHolder = "Chetan Chauhan";
    static String accountNumber = "1234567890";
    static String lastTransaction = "No Transaction";

    public static void main(String[] args) {

        welcomeScreen();

    }

    static void welcomeScreen() {

        System.out.println();
        System.out.println("****************************************************");
        System.out.println("*                                                  *");
        System.out.println("*              ABC BANK ATM SYSTEM                 *");
        System.out.println("*                                                  *");
        System.out.println("****************************************************");

        System.out.println();
        System.out.print("Enter Account Number : ");
        String acc = sc.next();

        System.out.print("Enter 4-Digit PIN : ");
        int enteredPin = sc.nextInt();

        if (!acc.equals(accountNumber)) {

            System.out.println("\nInvalid Account Number.");
            return;

        }

        if (enteredPin != pin) {

            System.out.println("\nIncorrect PIN.");
            return;

        }

        loading();

        menu();

    }

    static void loading() {

        System.out.print("\nVerifying");

        try {

            for (int i = 0; i < 6; i++) {

                Thread.sleep(300);
                System.out.print(".");

            }

        } catch (Exception e) {

        }

        System.out.println("\nLogin Successful!");

    }

    static void menu() {

        while (true) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("             ABC BANK ATM");
            System.out.println("======================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Fast Cash");
            System.out.println("5. Change PIN");
            System.out.println("6. Mini Statement");
            System.out.println("7. Exit");
            System.out.println("======================================");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {
                                case 1:

                    System.out.println("\n======================================");
                    System.out.println("          ACCOUNT DETAILS");
                    System.out.println("======================================");
                    System.out.println("Account Holder : " + accountHolder);
                    System.out.println("Account Number : " + accountNumber);
                    System.out.println("Available Balance : ₹" + balance);
                    System.out.println("======================================");
                    break;

                case 2:

                    System.out.println("\n========== CASH DEPOSIT ==========");

                    System.out.print("Enter Amount : ₹");
                    double deposit = sc.nextDouble();

                    if (deposit > 0) {

                        balance += deposit;
                        lastTransaction = "Deposited ₹" + deposit;

                        receipt("Cash Deposit", deposit);

                        System.out.println("Deposit Successful!");
                        System.out.println("Current Balance : ₹" + balance);

                    } else {

                        System.out.println("Invalid Amount!");

                    }

                    break;

                case 3:

                    System.out.println("\n========== CASH WITHDRAW ==========");

                    System.out.print("Enter Amount : ₹");
                    double withdraw = sc.nextDouble();

                    if (withdraw <= 0) {

                        System.out.println("Invalid Amount!");

                    } else if (withdraw > balance) {

                        System.out.println("Insufficient Balance!");

                    } else {

                        balance -= withdraw;
                        lastTransaction = "Withdraw ₹" + withdraw;

                        receipt("Cash Withdraw", withdraw);

                        System.out.println("Please Collect Your Cash.");
                        System.out.println("Remaining Balance : ₹" + balance);

                    }

                    break;

                                    case 4:

                    System.out.println("\n========== FAST CASH ==========");
                    System.out.println("1. ₹500");
                    System.out.println("2. ₹1000");
                    System.out.println("3. ₹2000");
                    System.out.println("4. ₹5000");

                    System.out.print("Choose Option : ");

                    int fast = sc.nextInt();
                    int amount = 0;

                    switch (fast) {

                        case 1:
                            amount = 500;
                            break;

                        case 2:
                            amount = 1000;
                            break;

                        case 3:
                            amount = 2000;
                            break;

                        case 4:
                            amount = 5000;
                            break;

                        default:
                            System.out.println("Invalid Choice");
                    }

                    if (amount > 0) {

                        if (balance >= amount) {

                            balance -= amount;
                            lastTransaction = "Fast Cash ₹" + amount;

                            receipt("Fast Cash", amount);

                            System.out.println("Please Collect ₹" + amount);
                            System.out.println("Available Balance : ₹" + balance);

                        } else {

                            System.out.println("Insufficient Balance!");

                        }

                    }

                    break;

                case 5:

                    System.out.println("\n========== CHANGE PIN ==========");

                    System.out.print("Enter Current PIN : ");
                    int oldPin = sc.nextInt();

                    if (oldPin == pin) {

                        System.out.print("Enter New PIN : ");
                        int newPin = sc.nextInt();

                        System.out.print("Confirm New PIN : ");
                        int confirmPin = sc.nextInt();

                        if (newPin == confirmPin) {

                            pin = newPin;
                            lastTransaction = "PIN Changed";

                            System.out.println("PIN Changed Successfully.");

                        } else {

                            System.out.println("PIN Does Not Match!");

                        }

                    } else {

                        System.out.println("Wrong Current PIN!");

                    }

                    break;

                case 6:

                    System.out.println("\n======================================");
                    System.out.println("           MINI STATEMENT");
                    System.out.println("======================================");
                    System.out.println("Account Holder : " + accountHolder);
                    System.out.println("Account Number : " + accountNumber);
                    System.out.println("Balance        : ₹" + balance);
                    System.out.println("Last Transaction : " + lastTransaction);
                    System.out.println("======================================");

                    break;

                                    case 7:

                    System.out.println();
                    System.out.println("****************************************");
                    System.out.println("*                                      *");
                    System.out.println("*   THANK YOU FOR USING ABC BANK ATM   *");
                    System.out.println("*         VISIT AGAIN 😊               *");
                    System.out.println("*                                      *");
                    System.out.println("****************************************");

                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice! Please Try Again.");

            } // End of switch

        } // End of while

    } // End of menu()

    static void receipt(String type, double amount) {

        System.out.println();
        System.out.println("======================================");
        System.out.println("         TRANSACTION RECEIPT");
        System.out.println("======================================");
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Transaction    : " + type);
        System.out.println("Amount         : ₹" + amount);
        System.out.println("Available Bal. : ₹" + balance);
        System.out.println("======================================");

    }

} 

            