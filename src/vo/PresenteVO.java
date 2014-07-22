package vo;

import java.util.List;

public class PresenteVO {
	private UsuarioVO comprador, presenteado;
	private JogoVO jogo;
	private List<JogoVO> jogos; 
	
	public PresenteVO(){}
	
	public PresenteVO(UsuarioVO comprador, UsuarioVO presenteado, JogoVO jogo){
		this.comprador = comprador;
		this.presenteado = presenteado;
		this.jogo = jogo;
	}

	public PresenteVO(UsuarioVO comprador, UsuarioVO presenteado, List<JogoVO> jogos){
		this.comprador = comprador;
		this.presenteado = presenteado;
		this.jogos = jogos;
	}
	
	public JogoVO getJogo() {
		return jogo;
	}

	public void setJogo(JogoVO jogo) {
		this.jogo = jogo;
	}

	public List<JogoVO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoVO> jogos) {
		this.jogos = jogos;
	}

	public UsuarioVO getComprador() {
		return comprador;
	}

	public void setComprador(UsuarioVO comprador) {
		this.comprador = comprador;
	}

	public UsuarioVO getPresenteado() {
		return presenteado;
	}

	public void setPresenteado(UsuarioVO presenteado) {
		this.presenteado = presenteado;
	}
	
}
