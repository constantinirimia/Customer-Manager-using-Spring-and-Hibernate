package spring.app.Customer.dao;

import java.util.List;

import spring.app.Customer.entity.Customer;

// Interface that contains all methods of DataAccessObject
public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
	public List<Customer> searchCustomers(String theSearchName);


	
}
