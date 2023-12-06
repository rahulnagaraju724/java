/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab1
- FileName: AccountHolderTest.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas

- The program in this file will
1. Ask for the account holder's name
2. Request the account holder for their initial balance. 
3. Deposit the amount requested by the account holder.
4. Support the account holder in maintaining a minimum balance of $50.
5. Help the account holder with withdrawals.
6. Calculate the accumulated interest at the end of the month.
7. Extra credit - Calculate the accumulated interest for one year (month by month).
----------------------------------------------------------------------
*/

// Import all necessary libraries
// Date and Scanner

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

// Creating AccountHolderTest Class
public class AccountHolderTest{
    // Creating variable to hold Account Holder Name
    private static String NameOfAccountHolder;
    // Main function
    public static void main(String[] args)    {
        
        // Scanner object named sc to get necessary inputs from the end user
        Scanner sc=new Scanner(System.in);

        // To greet the user and ask name as an input to proceed
        System.out.println("We welcome you to the Bank of IIT!!");
        System.out.println("We kindly request you to enter your name:");
        NameOfAccountHolder = sc.nextLine();

        // Request user to enter the initial balance amount
        System.out.println("Hi "+NameOfAccountHolder+", Please enter your initial balance amount:");
        
        // Creating AccountHolder object by calling the constructor in the AccountHolder
        AccountHolder Account1=new AccountHolder(sc.nextDouble());
        
        // Request user to enter the deposit amount
        System.out.println(NameOfAccountHolder+", Please enter a deposit amount:");
        Account1.deposit(sc.nextDouble());
        
        // Request user to enter the withdrawal amount
        System.out.println(NameOfAccountHolder+", Please enter a withdrawal amount:");
        Account1.withdrawal(sc.nextDouble());

        // Closing the Scanner Object
        sc.close();

        // Update the balance to include interest by calling the method monthly interest
        Account1.monthlyInterest();

        // Fetch the updated balance and store in the balance variable
        double balance=Account1.getBalance();

        // Variable to format and round off balance to 2 decimal places 
        DecimalFormat deci = new DecimalFormat("##.00");

        System.out.println("Balance with interest applied = "+deci.format(balance));

        // Display balances for each month for the whole year
        //Account1.DisplayBalanceForYear();

        // Greetings after transactions
        System.out.println("Thanks for visiting us " + NameOfAccountHolder+"!!");

        // Display current timestamp and name of the owner who wrote the code
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("\nCur dt=" + timeStamp + "\nProgrammed by Rahul Nagaraju\n");
    }   
    
}