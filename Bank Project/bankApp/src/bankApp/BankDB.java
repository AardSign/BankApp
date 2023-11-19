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
	

}

//Read
	
	Costumer getAccount(int AccountID) {
		Costumer customer = null;
		Connection connection = connect();
		String findUserSql = "";
		PreparedStatement findUser = connection.prepareStatement(findUserSql);
		
				
				return customer;
	}

//U
//D


