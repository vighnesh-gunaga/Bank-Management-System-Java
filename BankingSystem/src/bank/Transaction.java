package bank;

import java.time.LocalDateTime;

public class Transaction {
	private String type;
	private double amount;
	private String details;
	private LocalDateTime date;
	
	
	
	public Transaction(String type, double amount, String details) {
		super();
		this.type = type;
		this.amount = amount;
		this.details = details;
		this.date = LocalDateTime.now();
	}



	public void displayTransaction()
	{
		System.out.println("Type : "+type);
		System.out.println("Amount : "+amount);
		System.out.println("Details : "+details);
		System.out.println("Date : "+date);
		System.out.println("----------------------------");
	}
}
