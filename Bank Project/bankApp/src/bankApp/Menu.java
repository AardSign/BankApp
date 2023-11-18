package bankApp;

import java.util.Scanner;

public class Menu {

	Scanner scan = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu.runMenu();
	}
	
	public void runMenu() {
		printHeader();
		while(!exit) {
			printMenu();
			int choice = getInput();
			peformAction(choice);
		}
	}

	private void printMenu() {
		System.out.println("Qual operação deseja realizar?");
		System.out.println("1. Para Criar nova conta");
		System.out.println("2. Realizar um depósito");
		System.out.println("3. Realizar um Saque");
		System.out.println("4. Listar saldo da conta");
		System.out.println("0. Sair");	
	}

	private void printHeader() {		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$ Bem vindo ao Banco Vivaldi! $");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}
	
	private int getInput(){
		int choice = -1;
		System.out.println("Selecione opção: ");
		do{
			try {
				choice = Integer.parseInt(scan.nextLine());
			}
			catch(NumberFormatException e){
				System.out.println("Opção Inválida!");
				System.out.println("Digitar apenas números!!");
			}
			if(choice < 0 || choice > 4) {
				System.out.println("Escolher opção entre 0 e 4!");
			}
		}while(choice < 0 || choice > 4);
		return choice;
	}
	
	
	private void peformAction(int choice) {
		switch(choice) {
			case 0:
				System.out.println("Obrigado por usar nosso aplicativo!");
				System.exit(0);
				break;
			case 1:
				createAccount();
				break;
			case 2:
				//makeADeposit();
			case 3:
				//makeAWithdraw();
				break;
			case 4:
				//listBalances();
				break;
				
			default:
				System.out.println("Erro desconhecido!");
		}
	}

	private void createAccount() {
		String accountType = "", firstName , lastName , cpf;
		double initialDeposit = 0;
		boolean valid = false;
		
		while(!valid) {
			System.out.println("Qual tipo de conta deseja criar? (Corrente/Poupança)");
			accountType = scan.nextLine();
			
			if(accountType.equalsIgnoreCase("Corrente") || accountType.equalsIgnoreCase("Poupança")){
				valid = true;
			}
			else {
				System.out.println("Escolher entre os dois tipos disponiveis!");
			}
		}
		System.out.print("Inserir nome: ");
		firstName = scan.nextLine();
		System.out.print("Inserir sobrenome: ");
		lastName = scan.nextLine();
		System.out.print("Inserir seu CPF: ");
		cpf = scan.nextLine();
		
		valid = false;			
		while(!valid) {
				System.out.println("Qual será seu depósito inicial?");
				try {
					initialDeposit = Double.parseDouble(scan.nextLine());
				}
				catch(NumberFormatException e) {
					System.out.println("Depósito tem quer ser em números!");
				}
				if(accountType.equalsIgnoreCase("Corrente")) {
					if(initialDeposit < 100) {			
						System.out.println("Para uma conta Corrente o depósito inicial tem que ser superior a 100R$");	
						
						}else {
							valid =  true;
						}	
					}	
				else if(accountType.equalsIgnoreCase("Poupança")) {
					if(initialDeposit < 50) {			
						System.out.println("Para uma conta poupança o depósito inicial tem que ser superior a 50R$");	
						
						}else {
							valid =  true;
						}	
					}
			}
		// Agora posso usar poliformismo para criar uma conta.
		Account account;
		if(accountType.equalsIgnoreCase("Corrente")) {
			account = new Checking(initialDeposit);
		}
		if(accountType.equalsIgnoreCase("Poupança")){
			account = new Savings(initialDeposit);
		}
	}
	
	
	
}
