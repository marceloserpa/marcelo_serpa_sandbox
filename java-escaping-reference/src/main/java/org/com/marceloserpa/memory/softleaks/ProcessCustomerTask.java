package org.com.marceloserpa.memory.softleaks;

import java.util.Optional;
import java.util.UUID;

public class ProcessCustomerTask implements Runnable {

	
private CustomerManager cm;
	
	public ProcessCustomerTask(CustomerManager cm) {
		this.cm = cm;
	}
	
	@Override
	public void run() {
		while (true) {		

			Optional<Customer> customer = cm.getNextCustomer();
			if (!customer.isPresent()) {
				//no customers in queue so pause for half a second
				try {
					Thread.sleep((50));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				//Processing takes place here
			}
		}

	}

}
