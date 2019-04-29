/**
 * 
 */
package br.com.callink.balancer.toolbar.solid.dto;

import br.com.callink.balancer.toolbar.solid.service.ProcessaBallancerAsceptServiceImpl;
import br.com.callink.balancer.toolbar.solid.service.ProcessaBallancerAvayaServiceImpl;
import br.com.callink.balancer.toolbar.solid.service.ProcessaBallancerService;

/**
 * @author malaquias
 *
 */
public enum Plataforma {
	
	ASPECT(new ProcessaBallancerAsceptServiceImpl()),
	AVAYA(new ProcessaBallancerAvayaServiceImpl());
	
	private ProcessaBallancerService processaBallancer;

	/**
	 * @param processaBallancer
	 */
	private Plataforma(ProcessaBallancerService processaBallancer) {
		this.processaBallancer = processaBallancer;
	}
	
	public ProcessaBallancerService getProcessa() {
		return processaBallancer;
	}

}
