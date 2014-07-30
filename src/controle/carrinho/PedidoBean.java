package controle.carrinho;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import negocio.Jogo;
import negocio.Pedido;
import negocio.Usuario;
import vo.JogoVO;
import vo.PedidoVO;
import vo.UsuarioVO;
import vo.enumerado.TipoPedido;
import vo.excecao.UsuarioVOException;

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
		
		if(tipoPedido.equals(TipoPedido.COMPRA)) {
			usuarioLogado.setJogos(jogos);
			try {
				usuario.save(usuarioLogado);
			} catch (UsuarioVOException e) {
				e.printStackTrace();
			}
			
		} else if(tipoPedido.equals(TipoPedido.PRESENTE)) {
			usuarioPresenteado.setJogos(jogos);
			try {
				usuario.save(usuarioPresenteado);
				usuario.save(usuarioLogado);
			} catch (UsuarioVOException e) {
				e.printStackTrace();
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
