package br.com.callink.balancer.toolbar.solid.service;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

public class ServerServiceImpl implements ServerService {
	
	private HttpServerServiceImpl httpServerService;
	private JSONArray jsonArray;
	
	private static final Logger logger = Logger.getLogger(ServerServiceImpl.class);

	@Override
	public Integer getSumUser(PropertiesDTO propertiesDTO) {
		Integer sumUserLogged = 0;
		try {
			httpServerService = new HttpServerServiceImpl();
			
			/**
			 * Metodologia - SOLID - Principio da Substituição de Liskov (LSP) 
			 * Uso da Interface - Fazendo uso somente o que é necessário.
			 * Metodo httpServerService.post
			 */
			String body = httpServerService.post(propertiesDTO);
	        
	        if(!body.isEmpty() && body != null) {
	        	jsonArray = new JSONArray();
	        	JSONObject jsonBody = new JSONObject(body);
	            JSONObject jsonMessage = new JSONObject(jsonBody.getString("message"));
	            JSONObject jsonServerOnline = new JSONObject(jsonMessage.getString("ipPortUserId"));
	            jsonArray = jsonServerOnline.getJSONArray(propertiesDTO.getClient());
	            sumUserLogged = jsonArray.length();
	        } else {
	        	sumUserLogged = -1;
	        }
		} catch (Exception e) {
			logger.error("Error method getSumUser : " + propertiesDTO.toString(), e);
		}
        	
		return sumUserLogged;
	}

	@Override
	public String getUserLoged() {
		
		return System.getProperty("user.name");
	}

}
