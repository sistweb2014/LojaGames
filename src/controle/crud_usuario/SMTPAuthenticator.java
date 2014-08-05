package controle.crud_usuario;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	public PasswordAuthentication getPasswordAuthentication() {
		String username = UsuarioControle.SMTP_AUTH_USER;
		String password = UsuarioControle.SMTP_AUTH_PWD;

		return new PasswordAuthentication(username, password);
	}

}
