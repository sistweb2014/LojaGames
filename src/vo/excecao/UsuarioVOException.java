package vo.excecao;

@SuppressWarnings("serial")
public class UsuarioVOException extends Exception {

	public static final String NOMEOBRIGATORIO = "Nome é de preenchimento obrigatório";
	public static final String LOGINOBRIGATORIO = "Login é de preenchimento obrigatório";
	public static final String EMAILOBRIGATORIO = "E-mail é de preenchimento obrigatório";
	public static final String EMAILINVALIDO = "E-mail inválido";
	public static final String SENHAOBRIGATORIO = "Senha é de preenchimento obrigatório";
	public static final String LOGINFAIL = "Login e senha são inválidos";
	public static final String LOGINEXISTENTE = "Login já cadastrado";
	public static final String EMAILEXISTENTE = "E-mail já cadastrado";
	
	public UsuarioVOException(String msg) {
		super(msg);
	}

}
