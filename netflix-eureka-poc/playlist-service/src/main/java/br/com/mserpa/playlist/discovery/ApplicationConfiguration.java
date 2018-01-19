package br.com.mserpa.playlist.discovery;


import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;

public class ApplicationConfiguration {
	
	private EmbeddedWebApplicationContext context;
	private String serviceName;

	public ApplicationConfiguration(EmbeddedWebApplicationContext context, String serviceName) {
		this.context = context;
		this.serviceName=serviceName;
	}
	
	public String getPort() {
		return String.valueOf(context.getEmbeddedServletContainer().getPort());
	}
	
	public String getHost() {
		return "localhost";
	}
	
	public String getServiceName() {
		return serviceName;
	}

}
