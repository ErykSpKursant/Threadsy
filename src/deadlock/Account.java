package deadlock;

public class Account {

    private String number;
    private double balance;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public void increaseBalance(double amount){
        balance += amount;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }
}
