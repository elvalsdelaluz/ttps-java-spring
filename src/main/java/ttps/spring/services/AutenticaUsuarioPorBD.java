package ttps.spring.services;

import ttps.spring.models.Usuario;

public class AutenticaUsuarioPorBD implements Autenticable{

	@Override
	public boolean autenticar(Usuario usuario) {
		// TODO Auto-generated method stub
		System.out.println("Se esta autenticado por BD");
		return false;
	}

}
