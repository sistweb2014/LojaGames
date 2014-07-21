package vo.enumerado;

public enum TipoClassificacao {

	UMA_ESTRELA(1.0), DUAS_ESTRELAS(2.0), TRES_ESTRELAS(3.0), 
	QUATRO_ESTRELAS(4.0), CINCO_ESTRELAS(5.0);
	
	private Double estrela;
	
	private TipoClassificacao(Double estrela) {
		this.setEstrela(estrela);
	}

	public Double getEstrela() {
		return estrela;
	}

	public void setEstrela(Double estrela) {
		this.estrela = estrela;
	}
	
}
