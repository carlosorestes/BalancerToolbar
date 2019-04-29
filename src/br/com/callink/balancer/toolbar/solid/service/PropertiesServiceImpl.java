package br.com.callink.balancer.toolbar.solid.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.callink.balancer.toolbar.solid.dto.Plataforma;
import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

public class PropertiesServiceImpl implements PropertiesService {
	
	private static final Logger logger = Logger.getLogger(PropertiesServiceImpl.class);

	
	private static String DIRECTORY = "toolbarProperties//";
	private Properties properties;
	private PropertiesDTO propertiesDTO;

	@Override
	public Integer processa(String body) {
		return null;
	}

	@Override
	public Properties read(String fileName) throws IOException {
		logger.info("Method: read " + fileName.toString());
		properties = new Properties();
		FileInputStream file = new FileInputStream(DIRECTORY + fileName);
		properties.load(file);
		
		return properties;
	}

	@Override
	public PropertiesDTO getValue(Properties properties) {
		propertiesDTO = new PropertiesDTO();
		
		propertiesDTO.setServer(properties.getProperty("prop.toolbar.server"));
		propertiesDTO.setServerData(properties.getProperty("prop.toolbar.server.data"));
		propertiesDTO.setClient(properties.getProperty("prop.toolbar.client"));
		propertiesDTO.setClientInstall(properties.getProperty("prop.toolbar.client.install"));
		
		getEnumPropertiesProcessa(properties.getProperty("prop.toolbar.client.type"), propertiesDTO);
		
		return propertiesDTO;
	}

	private void getEnumPropertiesProcessa(String property, PropertiesDTO propertiesDTO) {
		Integer sum = 0;
		if(!property.isEmpty() && property != null) {
			if(property.equals("Aspect")) {
				/**
				 * Metodologia - SOLID -  Principio da Responsabilidade Unica (SRP)
				 */
				sum = Plataforma.ASPECT.getProcessa().processa(propertiesDTO);
			} else if (property.equals("Avaya")) {
				/**
				 * Metodologia - SOLID - Principio da Responsabilidade Unica (SRP) 
				 */
				sum = Plataforma.AVAYA.getProcessa().processa(propertiesDTO);
			}
		}
		propertiesDTO.setSumServer(sum);
	}

}
