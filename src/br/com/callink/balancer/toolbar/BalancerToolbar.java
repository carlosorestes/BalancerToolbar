/**
 * 
 */
package br.com.callink.balancer.toolbar;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 31316_carlos
 *
 */
public class BalancerToolbar {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		BalancerDTO balancerDTO;	
		try {
			
			List<BalancerDTO> list = new ArrayList<BalancerDTO>();
			BalancerToolbar hce = new BalancerToolbar();
			
			String filename = "";
			File folder = new File("toolbarProperties");
			File[] listOfFiles = folder.listFiles();
			Properties props;
			String server;
			String serverData;
			String client;
			String clientInstall;

			if (listOfFiles.length > 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						
						props = new Properties();
						props = readProperties(listOfFiles[i].getName());
						server = props.getProperty("prop.toolbar.server");
						serverData = props.getProperty("prop.toolbar.server.data");
						client = props.getProperty("prop.toolbar.client");
						clientInstall = props.getProperty("prop.toolbar.client.install");
						
						Integer sum   = hce.getSumUserServer(server, 
								serverData,
								client);
						
						balancerDTO = new BalancerDTO(i, sum, clientInstall, server);
			            list.add(balancerDTO);
						
					}
				}
			}
			
			
			String locationToolbarServer = "";
			Integer logged = 0;
			Integer sum = 0;
			            
            for(BalancerDTO dto: list) {
            	
            	sum = sum + dto.getSum();
            	
//            	System.out.println("URL: " + dto.getServer() + " - " + dto.getSum());
            	
            	if(logged == 0) {
            		logged = dto.getSum();
            		locationToolbarServer = dto.getDiretorioClientToolbar();
            		continue;
            	}
            	
            	if(dto.getSum() < logged) {
            		logged = dto.getSum();
            		locationToolbarServer = dto.getDiretorioClientToolbar();
            	}
            	
            }
            

//            System.out.println("Total: " + sum.toString());
            System.out.println(locationToolbarServer);
            
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}	
	
	public static Properties readProperties(String fileName) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("toolbarProperties//" + fileName);
        props.load(file);
		return props;
	}
	
	public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "balancer.properties");
        props.load(file);
        return props;
    }
	
	public static String getValueProperties(String key) throws IOException {
		Properties prop = getProp();
		return prop.getProperty(key);
	}
	
	private Integer getSumUserServer(String postUrl, String data, String toolbalServer) throws IOException, JSONException {
		
		JSONArray jsonArray = new JSONArray();
        String body = this.post(postUrl, data);
        
        if(!body.isEmpty() || body != null) {
        	JSONObject jsonBody = new JSONObject(body);
            JSONObject jsonMessage = new JSONObject(jsonBody.getString("message"));
            JSONObject jsonServerOnline = new JSONObject(jsonMessage.getString("ipPortUserId"));
            jsonArray = jsonServerOnline.getJSONArray(toolbalServer);
        }
        	
		return jsonArray.length();
	}
	
	 public String post(String postUrl, String data) throws IOException {
	        URL url = new URL(postUrl);
	        String returnData = "";
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	        con.setRequestMethod("POST");
	        con.setConnectTimeout(2000);

	        con.setDoOutput(true);

	        this.sendData(con, data);
	        
	        if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	returnData = this.read(con.getInputStream());
	        }

	        return returnData;
	    }

	    protected void sendData(HttpURLConnection con, String data) throws IOException {
	        DataOutputStream wr = null;
	        try {
	            wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(data);
	            wr.flush();
	            wr.close();
	        } catch(IOException exception) {
	            throw exception;
	        } finally {
	            this.closeQuietly(wr);
	        }
	    }

	    private String read(InputStream is) throws IOException {
	        BufferedReader in = null;
	        String inputLine;
	        StringBuilder body;
	        try {
	            in = new BufferedReader(new InputStreamReader(is));

	            body = new StringBuilder();

	            while ((inputLine = in.readLine()) != null) {
	                body.append(inputLine);
	            }
	            in.close();

	            return body.toString();
	        } catch(IOException ioe) {
	            throw ioe;
	        } finally {
	            this.closeQuietly(in);
	        }
	    }

	    protected void closeQuietly(Closeable closeable) {
	        try {
	            if( closeable != null ) {
	                closeable.close();
	            }
	        } catch(IOException ex) {

	        }
	    }

}
