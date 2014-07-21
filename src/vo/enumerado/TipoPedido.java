package vo.enumerado;

public enum TipoPedido {

	COMPRA("compra"), PRESENTE("presente");
	
	private String tipo;
	
	private TipoPedido(String tipo) {
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
