package ttps.spring.services;

import ttps.spring.models.Usuario;

public interface Autenticable {
	public abstract boolean autenticar(Usuario usuario);

}
