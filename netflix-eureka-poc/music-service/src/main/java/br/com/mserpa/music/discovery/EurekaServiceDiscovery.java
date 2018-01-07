package br.com.mserpa.music.discovery;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.el.WebApplicationContextFacesELResolver;


public class EurekaServiceDiscovery implements ServiceDiscovery{
	
	private RestTemplate restTemplate;
	
	public EurekaServiceDiscovery(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void register(String ip, String port, String appName) {
		String instanceName = buildInstanceID(ip, port, appName);
		String request = "{\n" + 
				"	\"instance\": {\n" + 
				"		\"hostName\": \""+instanceName+"\",\n" + 
				"		\"app\": \""+appName+"\",\n" + 
				"		\"vipAddress\": \"br.com.mserpa.music\",\n" + 
				"		\"secureVipAddress\": \"br.com.mserpa.music\",\n" + 
				"		\"ipAddr\": \""+ip+"\",\n" + 
				"		\"status\": \"UP\",\n" + 
				"		\"port\": {\"$\": \""+port+"\", \"@enabled\": \"true\"},\n" + 
				"		\"securePort\": {\"$\": \"8443\", \"@enabled\": \"true\"},\n" + 
				"		\"healthCheckUrl\": \"http://"+ip+":"+port+"/healthcheck\",\n" + 
				"		\"statusPageUrl\": \"http://"+ip+":"+port+"/status\",\n" + 
				"		\"homePageUrl\": \"http://"+ip+":"+port+"\",\n" + 
				"		\"dataCenterInfo\": {\n" + 
				"			\"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\", \n" + 
				"			\"name\": \"MyOwn\"\n" + 
				"		}\n" + 
				"	}\n" + 
				"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(request,headers);
		URI url;
		try {
			url = new URI("http://localhost:8080/eureka-server-1.8.6/v2/apps/" + appName);
			ResponseEntity<Void> result = restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> getHosts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renew(String ip, String port, String appName) {
		String instanceID = buildInstanceID(ip, port, appName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			String url  = "http://localhost:8080/eureka-server-1.8.6/v2/apps/" + appName + "/" + instanceID;
			System.out.println("renew -> " + url);
			ResponseEntity<Void> result = restTemplate.exchange(new URI(url), HttpMethod.PUT, null, Void.class);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		
	}

	private String buildInstanceID(String ip, String port, String appName) {
		return String.format("%s_%s_%s", appName, ip, port);
	}
}
