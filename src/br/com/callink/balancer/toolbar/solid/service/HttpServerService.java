/**
 * 
 */
package br.com.callink.balancer.toolbar.solid.service;

import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

/**
 * @author malaquias
 *
 */
public interface HttpServerService {
	
	public String post(PropertiesDTO propertiesDTO);
	
	public void sendData(HttpURLConnection connection, String data);
	
	public String read(InputStream is);
	
	public void closeQuietly (Closeable closeable);

}
