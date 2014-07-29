package controle.carrinho;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import negocio.Pedido;
import negocio.Usuario;
import vo.JogoVO;
import vo.PedidoVO;
import vo.UsuarioVO;
import vo.enumerado.TipoPedido;

@ManagedBean
@SessionScoped
public class PedidoBean {

	private PedidoVO pedidoVO = new PedidoVO();
	
	private List<JogoVO> jogos = new ArrayList<JogoVO>();
	
	private UsuarioVO usuarioLogado = new UsuarioVO();
	
	private UsuarioVO usuarioPresenteado = new UsuarioVO();
	
	private String tipoPedido;
	
	public void listaJogos(JogoVO jogo) {
		jogos.add(jogo);
	}
	
	public String salvarPedido() {
		Pedido pedido = new Pedido();
		pedido.save(pedidoVO);
		return "carrinho";
	}
	
	public void finalizarPedido() {
		Usuario usuario = new Usuario();
		boolean isPossivel = false;
		double total = 0;
		for(JogoVO jogo: jogos){
			total = jogo.getPreco();
		}

		if(usuarioLogado.getCredito() > total)
			isPossivel = true;
		else
			isPossivel = false;
		
		if(tipoPedido.equals(TipoPedido.COMPRA)) {
			if (isPossivel){
				usuarioLogado.setCredito(usuarioLogado.getCredito() - total);
				usuarioLogado.setJogos(jogos);
				usuario.save(usuarioLogado);
			}else{
				
			}
		} else if(tipoPedido.equals(TipoPedido.PRESENTE)) {
			if (isPossivel){
				usuarioLogado.setCredito(usuarioLogado.getCredito() - total);
				for (JogoVO jogo : jogos) {
					usuarioPresenteado.getJogos().add(jogo);
				}
				usuario.save(usuarioPresenteado);
				usuario.save(usuarioLogado);
			}else{
				
			}
		}
	}
	
	public UsuarioVO getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioVO usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public PedidoVO getPedido() {
		return pedidoVO;
	}

	public void setPedido(PedidoVO pedidoVO) {
		this.pedidoVO = pedidoVO;
	}

	public List<JogoVO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoVO> jogos) {
		this.jogos = jogos;
	}

	public UsuarioVO getUsuario() {
		return usuarioPresenteado;
	}

	public void setUsuario(UsuarioVO usuarioPresenteado) {
		this.usuarioPresenteado = usuarioPresenteado;
	}
}