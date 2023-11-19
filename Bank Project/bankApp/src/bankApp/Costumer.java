package bankApp;

public class Costumer {

	private final String firstName;
	private final String lastName;
	private final String cpf;
	private final Account account;

	Costumer(String firstName , String lastName , String cpf , Account account ){
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.account = account;
	}
	
	
	
	
	@Override
	public String toString(){
		return "\nDados do Cliente\n" + 
				"Nome: " + firstName + "\n" +
				"Sobrenome: " + lastName + "\n" +
				"CPF: " + cpf + "\n" +
				account;	
			}
	
	public String basicInfo(){
		return
				"Nome: " + firstName + "\n" +
				"Sobrenome: " + lastName + "\n" +
				"CPF: " + cpf + "\n" +
				"Número da conta: " + account.getAccountNumber();	
			}
	
	Account getAccount() {
			return account;
		}
}
