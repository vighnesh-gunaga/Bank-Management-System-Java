package bank;

public class BankAccount {
	
	private String accountNumber;
	private String accountHolderName;
	private String phoneNumber;
	private double balance;
	
	
	public BankAccount(String accountNumber, String accountHolderName, String phoneNumber, double balance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}
