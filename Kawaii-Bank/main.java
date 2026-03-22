
/**
 * Creates and manages accounts
 *
 * @author Kanya Farley
 * @version 2 20/03
 */
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class main
{
    Scanner kb = new Scanner(System.in);
    AccountArchive accounts = new AccountArchive();
    /**
     * Constructor for objects of class main
     */
    public main()
    {
        System.out.println("Welcome to Kawaii-Bank account management! What would you like to do? (Enter number for option)");

        System.out.println("1 : Create a new account");
        System.out.println("2 : Close an account");
        System.out.println("3 : Check account balance");
        System.out.println("4 : Deposit into account");
        System.out.println("5 : Withdraw from account");
        String userAction = kb.nextLine();

        if (userAction.equals("1")) {
            createAccount();
        } else if (userAction.equals("2")) {
            closeAccount();
        } else if (userAction.equals("3")) {
            checkAccount();
        } else {
            String regex = ("1|2|3|4|5");
            while (!userAction.matches(regex) && userAction.length() > 1) { // ensures user hasn't entered an option and can matche no more than 1 character
                System.out.println("Please enter '1', '2', '3', '4', or '5' in accordance with program options.");
                userAction = kb.nextLine();
            }
        }
    }

    /**
     * Asks for account information (customer name, address, account type) and provides an account number
     */

    public void createAccount () {
        System.out.print("Enter customer name for new account: ");
        String customerName = kb.nextLine();
        System.out.println();

        System.out.print("Enter customer address: ");
        String customerAddress = kb.nextLine();
        System.out.println();

        System.out.print("Enter account type: ");
        String accountType = kb.nextLine();
        System.out.println();

        // defines account number based on type
        accountType = accountType.toLowerCase();
        String accountNumberType = " ";
        if (accountType.equals("everyday")) {
            accountNumberType = "-02";
        } else if (accountType.equals("savings") || accountType.equals("current")) {
            accountNumberType = "-00";
        } else {
            while (accountNumberType.equals(" ")) {
                System.out.println("Input invalid. Try entering 'Everyday', 'Savings', or 'Current'");
                accountType = kb.nextLine();
            }
        }

        // generates random 6 digit number for account
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomAccountNumber = random.nextInt((max - min) + 1) + min;

        String accountNumber = "08-0101-0" + randomAccountNumber + accountNumberType;

        System.out.print("Enter amount to deposit: $");
        double currentBalance = kb.nextDouble();

        Account newAccount = new Account(customerName, accountNumber, customerAddress, accountType, currentBalance);
        System.out.println(customerName + ", " + accountNumber + ", " + customerAddress + ", " + accountType + ", " + "$" + currentBalance);
        accounts.toString();
        accounts.addAccount(newAccount);
        System.out.println("Account added to list: ");
        accounts.displayAll();
        accounts.saveToFile(" ");
    }

    public void closeAccount() {
        System.out.println("Current accounts: ");
        accounts.displayAll();
        System.out.println("Enter account number you wish to close (*Note, enter '-' characters with it: ");
        String accountNumber = kb.nextLine();
        /*for (String currentAccounts : accounts.toString()) { // not sure what to put or if
        if (accountNumber == accounts.getAccountNumber()) {

        }
        }*/
    }

    public void checkAccount() {
        System.out.println("Current accounts: ");
        accounts.displayAll();
    }

    public void depositToAccount() {

    }

}
