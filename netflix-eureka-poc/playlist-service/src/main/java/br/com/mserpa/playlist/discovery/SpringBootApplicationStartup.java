package br.com.mserpa.playlist.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class SpringBootApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private EurekaServiceDiscovery eureka;
	
	@Autowired
	private EmbeddedWebApplicationContext embeddedWebApplicationContext;
	
	private static final String SERVICE_NAME = "playlist-service";
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		Integer port = embeddedWebApplicationContext.getEmbeddedServletContainer().getPort();
		eureka.register("localhost", port.toString(), SERVICE_NAME);
	}
 
} 