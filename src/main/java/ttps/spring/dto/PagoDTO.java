package ttps.spring.dto;

public class PagoDTO {
	private Long id_deudor;
	private Long id_acreedor;
	private double monto;
	
	
	
	public Long getId_deudor() {
		return id_deudor;
	}
	public void setId_deudor(Long id_deudor) {
		this.id_deudor = id_deudor;
	}
	public Long getId_acreedor() {
		return id_acreedor;
	}
	public void setId_acreedor(Long id_acreedor) {
		this.id_acreedor = id_acreedor;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	

}
