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
		try {
			httpServerService = new HttpServerServiceImpl();
			
			/**
			 * Metodologia - SOLID - Principio da Substituição de Liskov (LSP) 
			 * Uso da Interface - Fazendo uso somente o que é necessário.
			 * Metodo httpServerService.post
			 */
			String body = httpServerService.post(propertiesDTO);
			jsonArray = new JSONArray();
	        
	        if(!body.isEmpty() || body != null) {
	        	JSONObject jsonBody = new JSONObject(body);
	            JSONObject jsonMessage = new JSONObject(jsonBody.getString("message"));
	            JSONObject jsonServerOnline = new JSONObject(jsonMessage.getString("ipPortUserId"));
	            jsonArray = jsonServerOnline.getJSONArray(propertiesDTO.getClient());
	        }
		} catch (Exception e) {
			logger.error("Error method getSumUser : " + propertiesDTO.toString(), e);
		}
        	
		return jsonArray.length();
	}

}
