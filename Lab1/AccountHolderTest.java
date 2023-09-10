import java.util.Scanner;

public class AccountHolderTest{

    public static void main(String[] args)    {
        System.out.println("Please enter your initial balance");
        Scanner sc=new Scanner(System.in);
        AccountHolder Account1=new AccountHolder(sc.nextDouble());
        
        System.out.println("Please enter a deposit");
        Account1.deposit(sc.nextDouble());
        System.out.println("Please enter a withdraw");
        Account1.withdrawal(sc.nextDouble());
        Account1.montlyInterest();
        double balance=Account1.getBalance();
        System.out.println("Balance with interest applied = "+balance);
    }   
    
}