public class AccountHolder{
    private double annualInterestRate;
    private double balance;

    public void AccountHolder(double balance){
        if(balance<0){
            System.out.print("Balance cannot be initiated to negative number");
        }
        else{
            this.balance=balance;
        }
        
    }
    public void deposit(double x){

    }
    public void withdrawal(double x){

    }
    public void montlyInterest(){

    }

}