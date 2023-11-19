package bankApp;

import java.util.ArrayList;

public class Bank {

	ArrayList<Costumer> customers = new ArrayList<Costumer>();
	
	public void AddCustomer(Costumer customer) {
		customers.add(customer);
	}

	Costumer getCustomer(int account) {
		return customers.get(account);
	}
	
	ArrayList<Costumer> getCustomers(){
		return customers;
	}
}
