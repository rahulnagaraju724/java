/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab1
- FileName: AccountHolder.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
----------------------------------------------------------------------
*/

// Import necessary libraries
import java.text.DecimalFormat;

// Creating AccountHolder Class
public class AccountHolder{
    
    // Declaration of variables
    private double annualInterestRate=(4.0/100.0);
    private double balance;

    // Constructor to initiate balance
    public  AccountHolder(double balance){
        try{
        if(balance<0){
            System.out.println("Balance cannot be initiated to a negative number");
        }
        else{
            this.balance=balance;
        } 
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to deposit the amount and update the balance in the account
    public void deposit(double amount){
        balance+=amount;
    }

    // Method to withdraw the amount and update the balance in the account
    public void withdrawal(double amount){
        // Making sure the balance is 50 before allowing for withdrawal
        try{
        if(balance-amount<50){
            throw new Exception ("You can't withdraw as your minimum balance should be 50");
        }else{
            balance-=amount; 
        }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to calculate the interest and update the balance in the account   
    public void monthlyInterest(){
        balance += balance * (annualInterestRate / 12.0);
    }

    // Getter method to fetch and return the balance
    public double getBalance(){
        return balance;
    }

    // Method to print balances for each month for one year
    public void DisplayBalanceForYear(){
        System.out.println("");
        System.out.println("Monthly balances for one year at 0.04 interest is as follows:");
        System.out.println("Account Balance with Interest:");

        // Variable to format and round off balance to 2 decimal places 
        DecimalFormat deci = new DecimalFormat("##.00");

        for(int i=1;i<=12;i++){
            monthlyInterest();
            System.out.println("Month "+i+":\t$"+deci.format(balance));
        }
        System.out.println("");
    }
}