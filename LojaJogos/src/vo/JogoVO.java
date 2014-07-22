package vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import vo.enumerado.TipoClassificacao;

@Entity
public class JogoVO {
	
	@Id
	@SequenceGenerator(initialValue = 1, name = "seq_jogo", sequenceName = "seq_jogo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jogo")
	private Long idJogo;
	
	private String nome;
	private String genero;
	private String imagem;
	private Double preco;
	private TipoClassificacao classificacao;
	
	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public TipoClassificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(TipoClassificacao classificacao) {
		this.classificacao = classificacao;
	}
		
		
}
