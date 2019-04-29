/**
 * 
 */
package br.com.callink.balancer.toolbar.solid;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;
import br.com.callink.balancer.toolbar.solid.service.PropertiesServiceImpl;

/**
 * @author malaquias
 *
 */
public class LoadBalancerApp {
	
	private static final Logger logger = Logger.getLogger(LoadBalancerApp.class);
	
	private PropertiesServiceImpl propertiesService;
	private File folder;
	private Properties properties;
	private ArrayList<PropertiesDTO> listProperteisDTO = new ArrayList<PropertiesDTO>();
	
	/**
	 * @param propertiesService
	 */
	public LoadBalancerApp(PropertiesServiceImpl propertiesService) {
		this.propertiesService = propertiesService;
	}


	/**
	 * Read directory of properties
	 * @return File[]
	 */
	public  String  processa(String directory) {
		String directoryClient = "";
		try {
			logger.info("Method processa: " + directory.toString());
			//Read properties
			folder = new File(directory);
			File[] listOfFiles = folder.listFiles();
			
			if (listOfFiles.length > 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						properties = propertiesService.read(listOfFiles[i].getName());
						listProperteisDTO.add(propertiesService.getValue(properties));
					}
				}
			}
			
			directoryClient = getSeverSmallerUser(listProperteisDTO);
			
		} catch (Exception e) {
			logger.error("Error ao processar arquivo ..." + directory.toString(), e);
		}
		
		return directoryClient;
	}


	private String getSeverSmallerUser(ArrayList<PropertiesDTO> listProperteisDTO) {
		
		String locationToolbarServer = "";
		Integer logged = 0;
		Integer sum = 0;
		            
        for(PropertiesDTO dto: listProperteisDTO) { 	
        	sum = sum + dto.getSumServer();
       
        	logger.info("URL: " + dto.getServer() + " - " + dto.getSumServer());
        	
        	if(logged == 0) {
        		logged = dto.getSumServer();
        		locationToolbarServer = dto.getClientInstall();
        		continue;
        	}
        	
        	if(dto.getSumServer() < logged) {
        		logged = dto.getSumServer();
        		locationToolbarServer = dto.getClientInstall();
        	}	
        }
        
		return locationToolbarServer;
	}
		
		

}
