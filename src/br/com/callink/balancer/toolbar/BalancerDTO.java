/**
 * 
 */
package br.com.callink.balancer.toolbar;

/**
 * @author 31316_carlos
 *
 */
public class BalancerDTO {
	
	private Integer idToolbar;
	private Integer sum;
	private String diretorioClientToolbar;
	
	
	public BalancerDTO(int idToolbar, Integer sum, String diretorioClientToolbar) {
		this.idToolbar = idToolbar;
		this.sum = sum;
		this.diretorioClientToolbar = diretorioClientToolbar;
	}
	public Integer getIdToolbar() {
		return idToolbar;
	}
	public void setIdToolbar(Integer idToolbar) {
		this.idToolbar = idToolbar;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public String getDiretorioClientToolbar() {
		return diretorioClientToolbar;
	}
	public void setDiretorioClientToolbar(String diretorioClientToolbar) {
		this.diretorioClientToolbar = diretorioClientToolbar;
	}
}
