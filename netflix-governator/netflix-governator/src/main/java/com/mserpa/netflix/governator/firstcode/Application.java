package com.mserpa.netflix.governator.firstcode;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Modules;
import com.mserpa.netflix.governator.firstcode.endpoint.Endpoint;
import com.netflix.governator.InjectorBuilder;
import com.netflix.governator.ShutdownHookModule;
import com.netflix.governator.guice.jetty.DefaultJettyConfig;
import com.netflix.governator.guice.jetty.JettyConfig;
import com.netflix.governator.guice.jetty.JettyModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class Application {
	
	public static void main(String[] args) throws Exception {
		InjectorBuilder.fromModules(			
            new ShutdownHookModule(), 
            Modules.override(new JettyModule()).with(new AbstractModule() {
               @Override
               protected void configure() {}
               
               @Provides
               @Singleton 
               private JettyConfig getConfig() {
                   DefaultJettyConfig config = new DefaultJettyConfig();
                   config.setPort(9000);
                   return config;
               }
           }), 
           new JerseyServletModule() {
				@Override
				protected void configureServlets() {                	   
				    serve("/*").with(GuiceContainer.class);
				    bind(Endpoint.class).asEagerSingleton();
				}                  
           }
		).createInjector();
		
	}
}
