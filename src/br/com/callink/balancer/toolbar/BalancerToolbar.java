/**
 * 
 */
package br.com.callink.balancer.toolbar;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
			
			String locationToolbarServer = "";
			Integer logged = 0;
		
            Integer sum2   = hce.getSumUserServer("http://10.33.4.110:7641/api/provider/usersLogged", 
            		"{\"idTypeMessage\":59,\"message\":\"{\\\"ipAddress\\\":\\\"\\\",\\\"port\\\":0,\\\"lineActive\\\":1,\\\"onlineProcessing\\\":true}\"}",
            		"10.33.4.110:9496");
             
            Integer sum3   = hce.getSumUserServer("http://10.33.4.110:7642/api/provider/usersLogged", 
            		"{\"idTypeMessage\":59,\"message\":\"{\\\"ipAddress\\\":\\\"\\\",\\\"port\\\":0,\\\"lineActive\\\":1,\\\"onlineProcessing\\\":true}\"}",
            		"10.33.4.110:9500");
            
            Integer sum4   = hce.getSumUserServer("http://10.33.4.110:7643/api/provider/usersLogged", 
            		"{\"idTypeMessage\":59,\"message\":\"{\\\"ipAddress\\\":\\\"\\\",\\\"port\\\":0,\\\"lineActive\\\":1,\\\"onlineProcessing\\\":true}\"}",
            		"10.33.4.110:9780");
            
            Integer sum5   = hce.getSumUserServer("http://10.33.4.110:7644/api/provider/usersLogged", 
            		"{\"idTypeMessage\":59,\"message\":\"{\\\"ipAddress\\\":\\\"\\\",\\\"port\\\":0,\\\"lineActive\\\":1,\\\"onlineProcessing\\\":true}\"}",
            		"10.33.4.110:9782");
            

            balancerDTO = new BalancerDTO(2, sum2, "C:\\Callink\\TOOLBAR_PROD\\TOOLBAR_NET_02");
            list.add(balancerDTO);
            
            balancerDTO = new BalancerDTO(3, sum3, "C:\\Callink\\TOOLBAR_PROD\\TOOLBAR_NET_03");
            list.add(balancerDTO);
            
            balancerDTO = new BalancerDTO(4, sum4, "C:\\Callink\\TOOLBAR_PROD\\TOOLBAR_NET_04");
            list.add(balancerDTO);
            
            balancerDTO = new BalancerDTO(5, sum5, "C:\\Callink\\TOOLBAR_PROD\\TOOLBAR_NET_05");
            list.add(balancerDTO);
            
            for(BalancerDTO dto: list) {
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
            
            System.out.println("Server 2: " + sum2);
            System.out.println("Server 3: " + sum3);
            System.out.println("Server 4: " + sum4);
            System.out.println("Server 5: " + sum5);
//            
            System.out.println("Total: " + (sum2 + sum3 + sum4 + sum5));
            System.out.println("Redirecionar -> " + locationToolbarServer);
            
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	private Integer getSumUserServer(String postUrl, String data, String toolbalServer) throws IOException, JSONException {
		
        String body = this.post(postUrl, data);
        
        System.out.println("postUrl: " + postUrl + "\n body: " + body);
        
        JSONObject jsonBody = new JSONObject(body);
        JSONObject jsonMessage = new JSONObject(jsonBody.getString("message"));
        JSONObject jsonServerOnline = new JSONObject(jsonMessage.getString("ipPortUserId"));
        List<String> list = new ArrayList<String>();
        JSONArray jsonArray = jsonServerOnline.getJSONArray(toolbalServer);
		
		return jsonArray.length();
	}
	
	 public String post(String postUrl, String data) throws IOException {
	        URL url = new URL(postUrl);
	        System.out.println("URL" + postUrl);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
	        con.setRequestMethod("POST");

	        con.setDoOutput(true);

	        this.sendData(con, data);

	        return this.read(con.getInputStream());
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
