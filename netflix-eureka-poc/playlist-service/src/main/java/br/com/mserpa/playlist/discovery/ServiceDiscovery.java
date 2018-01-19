package br.com.mserpa.playlist.discovery;

import java.util.List;

public interface ServiceDiscovery {

	void register(String ip, String port, String appName);
	
	List<String> getHosts(String name);
	
	void renew(String ip, String port, String appName);
	
}
