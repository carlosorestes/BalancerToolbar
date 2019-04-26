package br.com.callink.balancer.toolbar;

public class PropertiesDTO {

	private String server;
	private String serverData;
	private String client;
	private String clientInstall;
	/**
	 * @param server
	 * @param serverData
	 * @param client
	 * @param clientInstall
	 */
	public PropertiesDTO(String server, String serverData, String client, String clientInstall) {
		super();
		this.server = server;
		this.serverData = serverData;
		this.client = client;
		this.clientInstall = clientInstall;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PropertiesDTO [server=" + server + ", serverData=" + serverData + ", client=" + client
				+ ", clientInstall=" + clientInstall + "]";
	}
	
}
