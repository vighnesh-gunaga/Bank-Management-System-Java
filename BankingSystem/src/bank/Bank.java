package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	ArrayList<BankAccount> bank = new ArrayList<>();
	ArrayList<Transaction> transactions = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	public void saveAccountToFile()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"));
			for(BankAccount b : bank)
			{
				writer.write(b.getAccountNumber()+","+b.getAccountHolderName()+","+b.getPhoneNumber()+","+b.getBalance());
				writer.newLine();
			}
			writer.close();
			System.out.println("Account details saved in text File");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void loadAccountsFromFile()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
			String line;
			while((line = reader.readLine())!=null)
			{
				String[] data = line.split(",");
				
				String accountNumber = data[0];
				String accountHolderName = data[1];
				String phno = data[2];
				double balance = Double.parseDouble(data[3]);
				
				bank.add(new BankAccount(accountNumber,accountHolderName,phno,balance));
			}
			reader.close();
		}
		catch(Exception e)
		{
			System.out.println("No previous data found");
		}
		
	}
	public void createAccount()
	{
		String accountNumber = generateBankAccNo();
		System.out.println("AccountNumber :"+ accountNumber);
		boolean accountNumberexists = false;
		for(BankAccount b : bank)
		{
			if(b.getAccountNumber().equalsIgnoreCase(accountNumber))
			{
				accountNumberexists = true;
				break;
			}
		}
		if(accountNumberexists)
		{
			System.out.println("Account Number Already Exists. Try Again.");
	        return;
		}
		System.out.println("Enter Account Holder Name:");
		String accountHolderName = sc.nextLine();
		String phonenumber;
		while(true)
		{
			System.out.println("Enter PhoneNumber :");
			phonenumber = sc.nextLine();
			if(isValidPhoneNumber(phonenumber))
			{
				break;
			}
			else
			{
				System.out.println("Invalid Phno format! Try Again.");
			}
		}
		System.out.println("Enter Initial-Balance:");
		double balance = sc.nextDouble();
		sc.nextLine();

        // Add to list
        bank.add(new BankAccount(accountNumber, accountHolderName, phonenumber, balance));
        System.out.println("Account Created Successfully!");
        System.out.println("Your Account Number is: " + accountNumber);
	}
	public void deposit()
	{
	    System.out.println("Enter Account Number:");
	    String accountNumber = sc.nextLine();

	    boolean found = false;

	    for(BankAccount b : bank)
	    {
	        if(b.getAccountNumber().equalsIgnoreCase(accountNumber))
	        {
	            found = true;

	            System.out.println("Enter Deposit Amount:");
	            double depoAmount = sc.nextDouble();
	            sc.nextLine();
	            if(depoAmount <= 0)
	            {
	                System.out.println("Invalid amount");
	                return;
	            }
	            double newBalance = b.getBalance() + depoAmount;
	            b.setBalance(newBalance);

	            System.out.println("INR " + depoAmount + " credited successfully.");
	            System.out.println("Available Balance: INR " + newBalance);

	            transactions.add(new Transaction("Deposit",depoAmount,"Deposited to Account " +accountNumber));
	            break;
	        }
	    }

	    if(!found)
	    {
	        System.out.println("Account Number Not Found.");
	    }
	}
	public void withdraw()
	{
		System.out.println("Enter AccountNumber:");
		String accountNumber = sc.nextLine();
		
		boolean found = false;
		for(BankAccount b : bank)
		{
			if(b.getAccountNumber().equalsIgnoreCase(accountNumber))
			{
				found = true;
				System.out.println("Enter Withdraw Amount:");
				double withdrawAmt = sc.nextDouble();
				sc.nextLine();
				if(withdrawAmt<=0)
				{
					System.out.println("Invalid Amount");
					return;
				}
				
				if(b.getBalance()>=withdrawAmt)
				{
					double newBalance = b.getBalance()-withdrawAmt;
					b.setBalance(newBalance);
					
					System.out.println("INR "+ withdrawAmt + " Debited successfully");
					System.out.println("Available Balance : INR "+newBalance);
					transactions.add(new Transaction("WithDraw ",withdrawAmt," WithDraw From Account "+accountNumber));
					break;
				}
				else
				{
					System.out.println("Insufficient balance");
				}
			}
			
		}
		if(!found)
		{
			System.out.println("Account number not found!");
		}
	}
	public void transfer()
	{
		System.out.println("Enter AccountNumber:");
		String senderAccount = sc.nextLine();
		
		boolean found = false;
		for(BankAccount b : bank)
		{
			if(b.getAccountNumber().equalsIgnoreCase(senderAccount))
			{
				found= true;
				System.out.println("Enter AccountNumber to TransferMoney:");
				String receiverAccount = sc.nextLine();
				boolean found1 = false;
				for(BankAccount b1 : bank)
				{
					if(b1.getAccountNumber().equalsIgnoreCase(receiverAccount))
					{
						found1=true;
						System.out.println("Enter Amount To transfer");
						double transferMoney = sc.nextDouble();
						sc.nextLine();
						
						if(transferMoney<=0)
						{
							System.out.println("Invalid Amount.");
							return;
						}
						
						if(b.getBalance()>=transferMoney)
						{
							b.setBalance(b.getBalance()-transferMoney);
							b1.setBalance(b1.getBalance()+transferMoney);
							System.out.println("Money Transferred Successfully");
							transactions.add(new Transaction(
							        "Transfer",
							        transferMoney,
							        "From " + senderAccount + " to " + receiverAccount
							));
						}
						else
						{
							System.out.println("Insufficient Balance");
						}
						break;
					}
				}
				if(!found1)
				{
					System.out.println("Receiver Account Not Found");
				}
			}
		}
		if(!found)
		{
			System.out.println("Sender Account Not Found");
		}
	}
	public void checkBalance()
	{
		System.out.println("Enter AccountNumber");
		String accountNumber = sc.nextLine();
		boolean found = false;
		for(BankAccount b : bank)
		{
			if(b.getAccountNumber().equalsIgnoreCase(accountNumber))
			{
				found = true;
				System.out.println("Balance Check Successfull.");
				System.out.println("Available balance\n"+b.getBalance());
				break;
			}
		}
		if(!found)
		{
			System.out.println("Account Not Found");
		}
	}
	public void transaction()
	{
		if(transactions.isEmpty())
		{
			System.out.println("No Transaction Available");
			return;
		}
		System.out.println("=====Transaction History=====");
		for(Transaction t : transactions)
		{
			t.displayTransaction();
		}
	}
	public String generateBankAccNo()
	{
		return "ACC" + (1000 + bank.size());
	}
    public static boolean isValidPhoneNumber(String phonenumber) {
		
		String phoneRegex = "^[6-9]\\d{9}$";
		return phonenumber.matches(phoneRegex);
	}

	
}
