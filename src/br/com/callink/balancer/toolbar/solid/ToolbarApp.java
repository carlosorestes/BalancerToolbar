/**
 * 
 */
package br.com.callink.balancer.toolbar.solid;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.callink.balancer.toolbar.solid.service.PropertiesServiceImpl;

/**
 * @author malaquias
 *
 */
public class ToolbarApp {
	
	private static String DIRECTORY_PROPERTIES = "toolbarProperties";
	private static String LOG4J_PROPERTIES = "log4j.properties";
	
	private static final Logger logger = Logger.getLogger(ToolbarApp.class);

	/**
	 * Description Method App Init void main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		PropertyConfigurator.configure(LOG4J_PROPERTIES);
		
		logger.info("Start process of the Toolbar Balancer ...");
		
		/**
		 * Metodologia - SOLID - Principio Escopo Aberto/ Fechado (OCP) 
		 */
		LoadBalancerApp loadBalancerApp = new LoadBalancerApp(new PropertiesServiceImpl());
		
		System.out.println(loadBalancerApp.processa(DIRECTORY_PROPERTIES));
		
		logger.info("End process of the Toolbar Balancer ...");
	}

	
	

}
