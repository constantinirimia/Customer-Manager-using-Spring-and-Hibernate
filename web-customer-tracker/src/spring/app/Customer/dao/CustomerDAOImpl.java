package spring.app.Customer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.app.Customer.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// lets inject the session factory in the field
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// Now lets get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// we can sort the names using HQL by simply using "order by"
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
				List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// Now lets get the current hibernate session		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// HQL makes use of save or update
		//save if a new customer is found)
		//update if a pre-existing customer is found
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// Now lets get the current hibernate session		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve the customer database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// Now lets get the current hibernate session		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}
	
	
	
	@Override
    public List<Customer> searchCustomers(String theSearchName) {

		// Now lets get the current hibernate session        
		Session currentSession = sessionFactory.getCurrentSession();
        
        Query q = null;
   
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName
            q =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            q.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else 
            q =currentSession.createQuery("from Customer", Customer.class);            
        
        List<Customer> customers = q.getResultList();
                
        return customers;
        
    }



}











