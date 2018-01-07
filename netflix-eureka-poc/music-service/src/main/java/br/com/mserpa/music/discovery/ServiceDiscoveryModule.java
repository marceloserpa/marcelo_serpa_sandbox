package br.com.mserpa.music.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceDiscoveryModule {
	
	@Value("${application.name}")
	private String applicationName;

	@Bean
	public EurekaServiceDiscovery serviceDiscovery(RestTemplate restTemplate) {
		return new EurekaServiceDiscovery(restTemplate);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ApplicationConfiguration springUtils(EmbeddedWebApplicationContext context) {
		return new ApplicationConfiguration(context, applicationName);
	}
	
	@Bean
	public EurekaHearthbeat eurekaHearthbeat(ApplicationConfiguration springUtils, EurekaServiceDiscovery serviceDiscovery) {
		return new EurekaHearthbeat(springUtils, serviceDiscovery);
	}
	
}
