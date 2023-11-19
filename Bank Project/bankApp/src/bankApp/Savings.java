package bankApp;

public class Savings extends Account{
	
	private static String accountType = "Poupança";
	
	
	
	Savings(double initialDeposit){
		super();
		this.setBalance(initialDeposit);
		this.checkInterest(0);	
	}
	
	@Override
	public String toString() {
		return "Tipo de conta: " + "Conta " + accountType + "\n" +
				"Numero da conta: " + this.getAccountNumber() + "\n" +
				"Saldo: " + this.getBalance() + "\n" +
				"Taxa de Juros: " + this.getInterest() + "%\n";
	}
}
