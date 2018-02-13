package br.com.mserpa.playlist.discovery;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
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

	private final static String EUREKA_HOST = "http://localhost:8080/eureka/v2/";

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

		try {
			URI url = new URI(EUREKA_HOST + "apps/" + appName);
			restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
		} catch (URISyntaxException | HttpClientErrorException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> getHosts(String name) {		
 		String address = EUREKA_HOST + "apps/"+name+"/";
		System.out.println(address);
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
	    HttpEntity<?> request = new HttpEntity(headers);
	    HttpEntity<String> response = restTemplate.exchange(address, HttpMethod.GET, request, String.class);
	    String jsonString = response.getBody();
		return extractHosts(jsonString);
	}
	
	private List<String> extractHosts(String jsonString){
	    JSONObject jsonResult1 = new JSONObject(jsonString);                
        JSONObject application = jsonResult1.getJSONObject("application");
        JSONArray instances = application.getJSONArray("instance");
        List<String> hosts = new ArrayList<>();
		instances.forEach(instance -> {		
			JSONObject json = (JSONObject) instance;
			hosts.add(json.get("ipAddr") + ":" + json.getJSONObject("port").get("$"));
		});	
		return hosts;
	}

	@Override
	public void renew(String ip, String port, String appName) {
		String instanceID = buildInstanceID(ip, port, appName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			String url  = EUREKA_HOST + "apps/" + appName + "/" + instanceID;
			System.out.println("renew -> " + url);
			restTemplate.exchange(new URI(url), HttpMethod.PUT, null, Void.class);
		} catch (URISyntaxException | HttpClientErrorException e) {
			e.printStackTrace();
		}
	}

	private String buildInstanceID(String ip, String port, String appName) {
		return String.format("%s_%s_%s", appName, ip, port);
	}
}
