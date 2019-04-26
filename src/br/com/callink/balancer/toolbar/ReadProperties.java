package br.com.callink.balancer.toolbar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadProperties {

	private Properties props;
	private String server;
	private String serverData;
	private String client;
	private String clientInstall;
	
	private static final String DIRETORYPROPERTIES = "toolbarProperties//";
	private static final String DIRETORYPROPERTIES = "toolbarProperties//";
	private static final String DIRETORYPROPERTIES = "toolbarProperties//";
	private static final String DIRETORYPROPERTIES = "toolbarProperties//";
	
	private PropertiesDTO propertiesDTO;
	
	private static final Logger logger = Logger.getLogger(ReadProperties.class);
	
	/**
	 * @param props
	 */
	public ReadProperties(Properties props) {
		super();
		this.props = props;
	}
	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}
	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
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
	
	/**
	 * Metodo responsavel por realizar leitura do properties
	 * 
	 * @param fileName
	 * @return Properties
	 */
	public Properties read (String fileName) {
		this.props = new Properties();
		FileInputStream file;
		String fileNameInput = DIRETORYPROPERTIES + fileName;
		try {
			logger.info("Realizando arquivo .." + fileNameInput);
			file = new FileInputStream(fileNameInput);
			props.load(file);
		} catch (IOException e) {
			logger.error("Error ao carregar arquivo " + fileNameInput, new Throwable(e.getMessage()));
		}
		
		return props;
	}
	
	/**
	 * Metodo responsavel por pegar o varlor de item do properties
	 * 
	 * @param properties
	 * @return PropertiesDTO
	 */
	public PropertiesDTO getValueProperties(Properties properties) {
		server = props.getProperty("prop.toolbar.server");
		serverData = props.getProperty("prop.toolbar.server.data");
		client = props.getProperty("prop.toolbar.client");
		clientInstall = props.getProperty("prop.toolbar.client.install");
		return propertiesDTO;
	}
	
}
