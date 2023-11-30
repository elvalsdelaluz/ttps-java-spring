package ttps.spring.setting;
import ttps.spring.models.Usuario;
import ttps.spring.services.AutenticaUsuarioPorBD;
import ttps.spring.services.AutenticaUsuarioService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("app-ctx.xml");
		 AutenticaUsuarioService autUsrService=context.getBean("autUsuarioService", AutenticaUsuarioService.class);
		 Usuario usuario = new Usuario("user01", "passUser01");
		 System.out.println("La autenticacion fue: " + autUsrService.autenticarUsuario(usuario));
		 }


}
