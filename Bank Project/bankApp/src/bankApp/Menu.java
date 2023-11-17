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
				//createAccount();
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
	
	
	
}
