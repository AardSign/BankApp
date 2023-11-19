package bankApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

	Scanner scan = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	
	public static void main(String[] args) throws InvalidAccountTypeException {
		Menu menu = new Menu();
		
		menu.runMenu();
	}
	
	public void runMenu() throws InvalidAccountTypeException {
		printHeader();
		while(!exit) {
			printMenu();
			int choice = getInput();
			peformAction(choice);
		}
	}

	private void printMenu() {
		displayHeader("Selecione sua operação");
		
		System.out.println("1. Para Criar nova conta");
		System.out.println("2. Realizar um depósito");
		System.out.println("3. Realizar um Saque");
		System.out.println("4. Listar saldo da conta");
		System.out.println("0. Sair");	
	}

	private void printHeader() {		
		System.out.println("$-----------------------------$");
		System.out.println("|                             |");
		System.out.println("$ Bem vindo ao Banco Vivaldi! $");
		System.out.println("|                             |");
		System.out.println("$-----------------------------$");
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
		
	private void peformAction(int choice)  {
		switch(choice) {
			case 0:
				System.out.println("Obrigado por usar nosso aplicativo!");
				System.exit(0);
				break;
			case 1:
			try {
				createAccount();
			} catch (InvalidAccountTypeException e) {
				System.out.println("Falha ao criar conta. Tente novamente.");;
			}
				break;
			case 2:
				makeADeposit();
			case 3:
				makeAWithdraw();
				break;
			case 4:
				listBalances();
				break;
				
			default:
				System.out.println("Erro desconhecido!");
		}
	}
	
	private String askQuestion(String question , List<String> answers) {
		String response = "";
		Scanner put = new Scanner(System.in);
		boolean choice = ((answers == null) || answers.size() == 0) ? false : true;
		boolean firstRun = true;
		do{
			if(!firstRun) {
				System.out.println("Opção inválida!");
			}
			System.out.print(question);
			if(choice) {
				System.out.print("(");
				for(int i = 0 ; i < answers.size() - 1 ; i++) {
					System.out.print(answers.get(i) + "/");
				}
				System.out.print(answers.get(answers.size() - 1));
				System.out.print(") :");
			}
			response = put.nextLine();
			firstRun = false;
			if(!choice) {
				break;
			}
		}while(!answers.contains(response));
		
		return response;
	}
	
	private double getDeposit(String accountType) {
		boolean valid = false;	
		double initialDeposit = 0;
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
		return initialDeposit;
	}
	
	private void createAccount() throws InvalidAccountTypeException {
		// Receber informações da conta.
		displayHeader("Inserir informações");
		String accountType = askQuestion("Qual tipo de conta deseja criar?" , Arrays.asList("corrente" , "poupança"));			
		String firstName = askQuestion("Inserir nome: ", null);
		String lastName = askQuestion("Inserir sobrenome: ", null);
		String cpf = askQuestion("Inserir seu CPF: ", null);
		double initialDeposit = getDeposit(accountType);
		
		// Agora posso usar poliformismo para criar uma conta.
		Account account = null;
		if(accountType.equalsIgnoreCase("Corrente")) {
			account = new Checking(initialDeposit);
		}
		else if(accountType.equalsIgnoreCase("Poupança")){
			account = new Savings(initialDeposit);
		}
		else {
			throw new InvalidAccountTypeException();
		}
		Costumer customer = new Costumer(firstName , lastName , cpf , account);
		bank.AddCustomer(customer);
	}

	private int selectAccount() {
		ArrayList<Costumer> customers = bank.getCustomers();
		if(customers.size() <=0 ) {
			System.out.println("Nenhum cliente registrado.");
			return -1;
		}
		System.out.println("Selecione uma conta: ");
		for(int i = 0 ; i < customers.size() ; i++) {
			System.out.println((i + 1) + ") " + customers.get(i).basicInfo());
		}
		int account = 0;
		System.out.print("Qual conta deseja acessar?: ");
		try {
			account = Integer.parseInt(scan.nextLine()) -1;
		}
		catch(NumberFormatException e) {
			account = -1;
		}
		if(account < 0 || account > customers.size()) {
			System.out.println("Conta selecionada é inválida!");
			account = -1;
		}
		
		return account;
	}
	
	private double getAmount(String question) {
		System.out.println(question);
		double amount = 0;
		try{
			amount = Double.parseDouble(scan.nextLine());
		}catch(NumberFormatException e) {
			amount = 0;
		}
		return amount;
	}

	private void makeAWithdraw() {
		displayHeader("Faça seu saque");
		int account = selectAccount();
		if(account >= 0) {
			double amount = getAmount("Qual quantia deseja sacar?: ");
			bank.getCustomer(account).getAccount().withdraw(amount);
			}
		}
	
	private void makeADeposit() {
		displayHeader("Faça seu depósito");
		int account = selectAccount();
		if(account >= 0) {
			double amount = getAmount("Qual quantia deseja depositar?: ");
			bank.getCustomer(account).getAccount().deposit(amount);
	}
		}
	
	private void displayHeader(String message) {
		System.out.println();
		int width = message.length() - 6;
		StringBuilder sb = new StringBuilder();
		sb.append("$");
		for(int i = 0 ; i < width ; i++) {
			sb.append("--");
		}
		sb.append("$");
		System.out.println(sb.toString());
		System.out.println("      " + message + "   ");
		System.out.println(sb.toString());
	}
	
 	private void listBalances() {
 		displayHeader("Selecione conta para ver detalhes");
 		int account = selectAccount();
		if(account >= 0) {
			displayHeader("Informações do cliente");
			System.out.println(bank.getCustomer(account).getAccount()); 	
	}else {
		System.out.println("Conta selecionada é inválida!");
	}
		}
	}
