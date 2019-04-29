package br.com.callink.balancer.toolbar.solid.service;

import org.apache.log4j.Logger;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

/**
 * Metodologia - SOLID -  Principio da Responsabilidade Unica (SRP)
 */
public class ProcessaBallancerAsceptServiceImpl extends LoggerBalancer implements ProcessaBallancerService {
	
	private ServerServiceImpl serverservice;
	
	private static final Logger logger = Logger.getLogger(ProcessaBallancerAsceptServiceImpl.class);

	@Override
	public Integer processa(PropertiesDTO propertiesDTO) {
		serverservice = new ServerServiceImpl();
		setLoggerBalancer();
		return serverservice.getSumUser(propertiesDTO);
	}

	@Override
	public void setLoggerBalancer() {
		logger.warn("Sample ISP - Aspect");
	}

}
