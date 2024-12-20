package org.com.marceloserpa.memory.softleaks;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class CustomerManager {

	private List<Customer> customers = new ArrayList<Customer>();
	private int nextAvalailbleId = 0;

	private int lastProcessed = -1;

	public  void addCustomer(Customer customer) {
		synchronized (this) {
			customer.setId(nextAvalailbleId);
			synchronized(customers) {
				customers.add(customer);
			}
			nextAvalailbleId++;
		}

	}

	public Optional<Customer> getNextCustomer() {
		/**
		 * memory leak
		 if(lastProcessed + 1 > nextAvalailbleId) {
			 lastProcessed++;
			 return Optional.of(customers.get(lastProcessed));
		 }
		 */
		synchronized(customers) {
				if (customers.size() > 0) {
					return Optional.of(customers.remove(0));
				}
		}
		return Optional.empty();
	}	

	public void howManyCustomers() {
		int size = 0;
		size = customers.size();
		System.out.println("" + new Date() + " Customers in queue : " + size + " of " + nextAvalailbleId);
	}

}
