public class AccountHolder{
    private double annualInterestRate=4;
    private double balance;

    public  AccountHolder(double balance){
        if(balance<0){
            System.out.print("Balance cannot be initiated to negative number");
        }
        else{
            this.balance=balance;
        }
        
    }
    public void deposit(double amount){
        balance+=amount;
    }
    public void withdrawal(double amount){
        if(balance-amount<50){
            System.out.println("You can't withdraw as your minimum balance should be 50");
    
        }else{
            balance-=amount; 
        }
    }
    public void montlyInterest(){
        balance += balance * (annualInterestRate / 12.0);
    }

    public double getBalance(){
        return balance;
    }
}