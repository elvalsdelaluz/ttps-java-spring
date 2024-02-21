package ttps.spring.dto;

public class GastoDTO {
    private double monto;
    private double montoPrestado;
    private Long idUsuario;
    private Long formaPago;
    private Long categoria;
    
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Long formaPago) {
		this.formaPago = formaPago;
	}
	public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	public double getMontoPrestado() {
		return montoPrestado;
	}
	public void setMontoPrestado(double montoPrestado) {
		this.montoPrestado = montoPrestado;
	}
    
	
    
}
