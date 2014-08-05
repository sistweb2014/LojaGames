package vo.excecao;

public class JogoException extends Exception{

	public static final String NOME_OBRIGATORIO = "Nome do produto é de preenchimento obrigatório.";
	public static final String NOME_TAMANHO = "Nome do produto deve possuir entre 5 e 60 caracteres.";
	public static final String QTDE_NEGATIVA = "Quantidade do produto não pode ser menor do que zero (0).";
	public static final String QTDE_POSITIVA = "Produtos em estoque não podem ser excluídos.";
	public static final String VALOR_UNITARIO = "Valor do produto não pode ser negativo";
	public static final String TIPO_OBRIGATORIO = "Tipo do produto é de preenchimento obrigatório.";
	
	public JogoException(String msg) {
		super(msg);
	}
	
}
