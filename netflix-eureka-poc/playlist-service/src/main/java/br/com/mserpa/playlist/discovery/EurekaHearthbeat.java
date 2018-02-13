package br.com.mserpa.playlist.discovery;

import org.springframework.scheduling.annotation.Scheduled;

public class EurekaHearthbeat {
	
	private ApplicationConfiguration applicationConfiguration;
	private ServiceDiscovery serviceDiscovery;
	
	public EurekaHearthbeat(ApplicationConfiguration applicationConfiguration, ServiceDiscovery serviceDiscovery) {
		this.applicationConfiguration = applicationConfiguration;
		this.serviceDiscovery = serviceDiscovery;
	}

	@Scheduled(fixedRate = 30000)
	public void hearthbeat() {
		System.out.println("renew app");
		String port = applicationConfiguration.getPort();
		if(portIsDefined(port)) serviceDiscovery.renew(applicationConfiguration.getHost(), port, applicationConfiguration.getServiceName());
	}

	private boolean portIsDefined(String port) {
		return !port.equals("0") && !port.equals("-1");
	}
	
}
