package com.sf.it.hackathon.breakingbot;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ApplicationPath("breakingbot")
public class JerseyConfig extends ResourceConfig {

	@Autowired
	  public JerseyConfig(ObjectMapper objectMapper) {
	    // register endpoints
	    packages("com.sf.it.hackathon.breakingbot");
	    // register jackson for json 
	    register(new ObjectMapperContextResolver(objectMapper));
	  }
	 
	  @Provider
	  public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
	 
	    private final ObjectMapper mapper;
	 
	    public ObjectMapperContextResolver(ObjectMapper mapper) {
	      this.mapper = mapper;
	    }
	 
	    @Override
	    public ObjectMapper getContext(Class<?> type) {
	      return mapper;
	    }
	  }
	  
	  @Bean
	    public EmbeddedServletContainerFactory servletContainer() {
	        TomcatEmbeddedServletContainerFactory factory = 
	                      new TomcatEmbeddedServletContainerFactory();
	        return factory;
	     }
}
