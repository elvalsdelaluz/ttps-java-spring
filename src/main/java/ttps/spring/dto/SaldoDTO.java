package ttps.spring.dto;

import java.util.List;

public class SaldoDTO {
	private String email;
	private List<DeudaDTO> deudas;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<DeudaDTO> getDeudas() {
		return deudas;
	}
	public void setDeudas(List<DeudaDTO> deudas) {
		this.deudas = deudas;
	}
	
    
}
