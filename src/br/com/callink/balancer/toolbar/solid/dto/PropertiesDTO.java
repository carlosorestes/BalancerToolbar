package br.com.callink.balancer.toolbar.solid.dto;

public class PropertiesDTO {

	private String server;
	private String serverData;
	private String client;
	private String clientInstall;
	private Plataforma plataforma;
	private Integer sumServer;
	
	
	public PropertiesDTO() {}
	
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the serverData
	 */
	public String getServerData() {
		return serverData;
	}
	/**
	 * @param serverData the serverData to set
	 */
	public void setServerData(String serverData) {
		this.serverData = serverData;
	}
	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * @return the clientInstall
	 */
	public String getClientInstall() {
		return clientInstall;
	}
	/**
	 * @param clientInstall the clientInstall to set
	 */
	public void setClientInstall(String clientInstall) {
		this.clientInstall = clientInstall;
	}

	/**
	 * @return the plataforma
	 */
	public Plataforma getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * @return the sumServer
	 */
	public Integer getSumServer() {
		return sumServer;
	}

	/**
	 * @param sumServer the sumServer to set
	 */
	public void setSumServer(Integer sumServer) {
		this.sumServer = sumServer;
	}
	
}
