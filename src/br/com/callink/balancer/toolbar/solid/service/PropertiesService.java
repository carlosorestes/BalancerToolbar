package br.com.callink.balancer.toolbar.solid.service;

import java.io.IOException;
import java.util.Properties;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

public interface PropertiesService {
	
	public Properties read(String fileName) throws IOException;
	
	public Integer processa (String body);
	
	public PropertiesDTO getValue(Properties properties);

}
