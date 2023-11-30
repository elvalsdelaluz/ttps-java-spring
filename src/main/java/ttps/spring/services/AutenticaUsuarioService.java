package ttps.spring.services;

import ttps.spring.models.Usuario;

public class AutenticaUsuarioService {
	private Autenticable autenticador;
	
	public boolean autenticarUsuario(Usuario usuario) {
		System.out.println("Comienza la autenticacion");
		Boolean aut = this.getAutenticador().autenticar(usuario);
		System.out.println("Finaliza el proceso de autenticación");
		return aut;
	}

	public Autenticable getAutenticador() {
		return autenticador;
	}

	public void setAutenticador(Autenticable autenticador) {
		this.autenticador = autenticador;
	}
	
	
	

}
