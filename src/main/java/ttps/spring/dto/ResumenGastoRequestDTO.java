package ttps.spring.dto;

import java.util.List;

public class ResumenGastoRequestDTO {
    private double monto;
	private Long miembro;
	private String formapago;
	private List<ResumenRequestDTO> interests;
	 
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Long getMiembro() {
		return miembro;
	}
	public void setMiembro(Long miembro) {
		this.miembro = miembro;
	}
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public List<ResumenRequestDTO> getInterests() {
		return interests;
	}
	public void setInterests(List<ResumenRequestDTO> interests) {
		this.interests = interests;
	}
	 
	 
	 
	 

}
