package asd;

public class Account {
	private double balance = 0;
	private double interest = 0.02;
	private int accountNumber;
	private static int numberOfAccount = 1000000;
	
	Account(){
		accountNumber = numberOfAccount++;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest() {
		return interest * 100;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	
	
	public void withdraw(double amount) {
		if(amount > balance) {
			System.out.println("Você não tem saldo suficiente para concluir a operação");
			return;
		}		
		balance -= amount;
		checkInterest(0);
		System.out.println("Você sacou "+ amount + "R$ da sua conta.");
		System.out.println("Seu novo saldo é de: "+ balance + "R$!");
	}
	
	
	public void deposit(double amount) {
		if(amount <= 0) {
			System.out.println("Você não pode depositar valores menores ou iguias a 0");
			return;
		}
		checkInterest(amount);
		amount = amount + amount * interest;
		balance += amount;
		System.out.println("Você depositou "+ amount + "R$ na sua conta, com uma taxa de juros de: " + (interest * 100) + "%");
		System.out.println("Seu novo saldo é de: " + balance + "R$!");
		
	}




	public void checkInterest(double amount) {
		if(balance + amount > 10000) {
			interest = 0.05;
		}else {
			interest = 0.02;
		}
		
		
	}
}
