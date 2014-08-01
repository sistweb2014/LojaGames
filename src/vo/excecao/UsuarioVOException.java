package vo.excecao;

@SuppressWarnings("serial")
public class UsuarioVOException extends Exception {

	public static final String NOMEOBRIGATORIO = "Nome � de preenchimento obrigat�rio";
	public static final String LOGINOBRIGATORIO = "Login � de preenchimento obrigat�rio";
	public static final String EMAILOBRIGATORIO = "E-mail � de preenchimento obrigat�rio";
	public static final String EMAILINVALIDO = "E-mail inv�lido";
	public static final String SENHAOBRIGATORIO = "Senha � de preenchimento obrigat�rio";
	public static final String LOGINFAIL = "Login e senha s�o inv�lidos";

	public UsuarioVOException(String msg) {
		super(msg);
	}

}
