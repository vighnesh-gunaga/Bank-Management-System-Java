package bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in)) {
			Bank bank = new Bank();
			bank.loadAccountsFromFile();
			int choice;
			while(true)
			{
				System.out.println("===== BANK MANAGEMENT SYSTEM =====");
				System.out.println("1.Create Account");
				System.out.println("2.Deposit Money");
				System.out.println("3.Withdraw Money");
				System.out.println("4.Transfer Money");
				System.out.println("5.Check Balance");
				System.out.println("6.View Transaction History");
				System.out.println("7.Exit");
				System.out.println("Enter Your Choice : ");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice)
				{
				case 1: System.out.println("Please provide the following information to create your bank account:");
						bank.createAccount();
						break;
				case 2: bank.deposit();
						break;
				case 3: bank.withdraw();
						break;
				case 4: bank.transfer();
						break;
				case 5: bank.checkBalance();
						break;
				case 6: bank.transaction();
						break;
				case 7: bank.saveAccountToFile();
						System.out.println("Exit");
						return;
				default: System.out.println("Invalid Choice Entered");
				
				}
				
			}
		}

	}

}
