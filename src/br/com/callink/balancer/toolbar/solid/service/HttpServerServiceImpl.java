package br.com.callink.balancer.toolbar.solid.service;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import br.com.callink.balancer.toolbar.solid.dto.PropertiesDTO;

/*
 * Metodologia - SOLID - Princípio da Inversão de Dependência (DIP)
 * Utilização da Interface HttpServerService
 * */
public class HttpServerServiceImpl implements HttpServerService {
	
	private static final Logger logger = Logger.getLogger(HttpServerServiceImpl.class);

	@Override
	public String post(PropertiesDTO propertiesDTO) {
		
		String returnData = "";
		try {
			URL url = new URL(propertiesDTO.getServer());

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			con.setRequestMethod("POST");
			con.setConnectTimeout(2000);

			con.setDoOutput(true);

			this.sendData(con, propertiesDTO.getServerData());

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				returnData = this.read(con.getInputStream());
			}
		} catch (Exception e) {
			logger.error("Error menthod post: "+ propertiesDTO.toString(), e);
		}

		return returnData;

	}

	@Override
	public void sendData(HttpURLConnection connection, String data) {
		DataOutputStream wr = null;
		try {
			wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
		} catch (Exception e) {
			logger.error("Error menthod sendData: "+ data.toString(), e);
		} finally {
			this.closeQuietly(wr);
		}
	}

	@Override
	public String read(InputStream is) {
		
		BufferedReader in = null;
        String inputLine;
        StringBuilder body = new StringBuilder();
        
		try {
			in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

		} catch (Exception e) {
			logger.error("Error menthod read: ", e);
		} finally {
			this.closeQuietly(in);
		}
		return body.toString();
	}

	@Override
	public void closeQuietly(Closeable closeable) {
		try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {
        	logger.error("Error menthod closeQuietly: ", ex);
        }
	}

}
