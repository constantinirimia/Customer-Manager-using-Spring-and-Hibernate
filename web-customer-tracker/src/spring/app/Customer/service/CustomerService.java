package spring.app.Customer.service;

import java.util.List;

import spring.app.Customer.entity.Customer;

//Interface that will hold all methods of our Service layer
public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
	public List<Customer> searchCustomers(String theSearchName);
	
}
