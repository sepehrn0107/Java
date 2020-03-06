package stateandbehavior;

public class Account {
	
	double balance;
	
	double getBalance() {
		return balance;
	}
	
	void deposit(double amount) {
		if (amount>0) {
			balance = balance + amount;
		}
	}
	double interestRate;
	
	void addInterest() {
		this.balance+=(balance*interestRate)/100;
	}
	
	double getInterestRate() {
		return interestRate;
	}
	void setInterestRate(double amount) {
		interestRate=amount;
	}
	
	public String toString() {
		return "Balance" + balance;
	}
		

	
	public static void main(String[] args) {
		Account account = new Account();
		System.out.println(account.getBalance());
		account.deposit(100.00);
		System.out.println(account.getBalance());
		
		account.setInterestRate(5);
		account.addInterest();
		System.out.println(account.getBalance());

		
	}
}