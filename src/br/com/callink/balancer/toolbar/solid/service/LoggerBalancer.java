package br.com.callink.balancer.toolbar.solid.service;

/**
 * Metodologia - SOLID - Principio da Segregação de Interface (ISP) 
 */
public abstract class LoggerBalancer {
	
	private String messageLogger;

	/**
	 * @return the messageLogger
	 */
	public String getMessageLogger() {
		return messageLogger;
	}

	/**
	 * @param messageLogger the messageLogger to set
	 */
	public void setMessageLogger(String messageLogger) {
		this.messageLogger = messageLogger;
	}
	
	public abstract void setLoggerBalancer();
}
