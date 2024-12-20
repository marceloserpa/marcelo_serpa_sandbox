package org.com.marceloserpa.memory.softleaks;
import java.util.UUID;


public class GenerateCustomerTask implements Runnable {

	private CustomerManager cm;

	public GenerateCustomerTask(CustomerManager cm) {
		this.cm = cm;
	}

	@Override
	public void run() {
		while (true) 
		{
			try {
				//This is just to slow things down so we can see what's going on!
				Thread.sleep(2);
			} catch (InterruptedException e) {
			}
			String name = UUID.randomUUID().toString();
			Customer c = new Customer(name);
			cm.addCustomer(c);
		}
	}
}



