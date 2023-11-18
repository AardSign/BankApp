package bankApp;

public class Checking extends Account{
	private static String accountType = "Corrente";
	
	
	Checking(double initialDeposit){
		super();
		this.setBalance(initialDeposit);
		if(initialDeposit > 10000) {
			this.setInterest(5);
			}else {
				this.setInterest(2);
			}			
	}
	
	@Override
	public String toString() {
		return "Tipo de conta: " + "Conta " + accountType + "\n" +
				"Numero da conta: " + this.getAccountNumber() + "\n" +
				"Saldo: " + this.getBalance() + "\n" +
				"Taxa de Juros: " + this.getInterest() + "\n";
	}
}
