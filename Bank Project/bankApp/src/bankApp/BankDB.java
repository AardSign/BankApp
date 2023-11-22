package bankApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDB {
	String url = "jdbc:mysql://localhost:3306/bankdb";
	String user = "bank";
	String password = "912736";
	
	
	private Connection connect() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(url , user , password);
		} catch (SQLException e) {
			connection = null;
		}
		return connection;
	}



//Create(Adicionar conta)

	int addAccount(String firstName, String lastName, String cpf , AccountType accountType, Double balance) {
		int UserID = -1;
		int AccountID = -1;
		Connection connection = connect();
		try {
			connection.setAutoCommit(false);
			// add usuário.
			String addUserSql = "insert into users(FirstName, LastName, CPF) values(? , ? , ?)";
			PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
			addUser.setString(1, firstName);
			addUser.setString(2, lastName);
			addUser.setString(3, cpf);
			addUser.executeUpdate();	
			ResultSet addUserResults = addUser.getGeneratedKeys();
			if(addUserResults.next()) {
				UserID=addUserResults.getInt(1);
			}
			// add conta.
			String addAccountSql = "insert into accounts(Type, Balance) values ( ? , ? )";
			PreparedStatement addAccount = connection.prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
			addAccount.setString(1, accountType.name());
			addAccount.setDouble(2, balance);
			addAccount.executeUpdate();
			ResultSet addAccountResults = addAccount.getGeneratedKeys();
			if(addUserResults.next()) {
				AccountID=addUserResults.getInt(1);
			}
			//ligar usuario a conta.
			if(UserID > 0 && AccountID > 0) {
				String linkAccountSql = "insert into mappings(UserID, AccountID) values (?,?)";
				PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql);
				linkAccount.setInt(1, UserID);
				linkAccount.setInt(2, AccountID);
				linkAccount.executeUpdate();
				connection.commit();
			}
			else {
				connection.rollback();
			}
			connection.close();
			} catch (SQLException e) {
			System.err.println("An error has occured: " + e.getMessage());
		}
		return AccountID;
	}
	



//Read
	
	Costumer getAccount(int AccountID) {
		Costumer customer = null;
		Connection connection = connect();
		try{
			String findUserSql = "select firstName , lastName , cpf , type , balance "
							 + "from Users a join Mappings b on a.ID = b.UserID"
							 + "join accounts c on c.ID = b.AccountID "
								 + "where c.ID" ;
			PreparedStatement findUser = connection.prepareStatement(findUserSql);
			findUser.setInt(1 , AccountID);
			ResultSet findUserResults = findUser.executeQuery();
			if(findUserResults.next()) {
				String firstName = findUserResults.getString("firstName");
				String lastName = findUserResults.getString("lastName");
				String cpf = findUserResults.getString("cpf");
				AccountType type = AccountType.valueOf(findUserResults.getString("type"));
				double balance = findUserResults.getDouble("balance");
			
			Account account;
			if(type == AccountType.checking) {
				account = new Checking(AccountID , balance);
			}else if(type == AccountType.saving) {
				account = new Savings(AccountID , balance);
			}else {
				throw new Exception("Tipo de conta desconhecido");
			}
			customer = new Costumer(firstName , lastName , cpf , account);
			
			
		}else {
				System.err.println("Nenhum usuário foi encontrado para o ID " + AccountID);
		}
			}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}		
				return customer;
				}
	
	


//Update
	boolean UpdateAccount(int accountID , double balance) {
		boolean success = false;
		Connection connection = connect();
		
		try {
			String updateSql = "UPDATE Accounts SET Balance = ? WHERE ID = ?";
			PreparedStatement updateBalance = connection.prepareStatement(updateSql);
			updateBalance.setDouble(1 , balance);
			updateBalance.setInt(2 , accountID);
			updateBalance.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		
	}return success;
		}
	
	
//Delete

	boolean DeleteAccount(int accountID) {
		boolean success = false;
		Connection connection = connect();
		
		try {
			String deleteSql = "DELETE Users , Accounts "
					+ "from Users a join Mappings b on a.ID = b.UserID"
					+ "	join accounts c on c.ID = b.AccountID"
					+ "where c.ID\" ;";
			PreparedStatement deleteAccount = connection.prepareStatement(deleteSql);
			deleteAccount.setInt(1 , accountID);
			deleteAccount.executeUpdate();
			success = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		
	}return success;
		}

	



}