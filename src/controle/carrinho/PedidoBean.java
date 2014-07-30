package controle.carrinho;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
	
	@ManagedProperty(value="#{usuarioControle.vo}")
	private UsuarioVO usuarioLogado;
	private UsuarioVO usuarioPresenteado;
	private TipoPedido tipoPedido;
	private double total;
	
	public PedidoBean() {
		total = 0;
	}
	
	public String adicionaJogo(JogoVO jogo) {
		System.out.println(usuarioLogado.getLogin()+"\n");
		System.out.println(usuarioLogado.getCredito()+"\n");
		if (usuarioLogado.getCredito() > (total+jogo.getPreco())) {
			total += jogo.getPreco();
			System.out.println(total+"\n");
			System.out.println(jogo.getNome());
			jogos.add(jogo);
		}else{
			FacesContext.getCurrentInstance().addMessage("frmCarrinho", 
					new FacesMessage("Crédito insuficiente para adicionar "+jogo.getNome()));
		}
		return "carrinho";
	}
	
	public void removeJogo(JogoVO jogo){
		jogos.remove(jogo);
		total -= jogo.getPreco();
	}
	
	public String salvarPedido() {
		Pedido pedido = new Pedido();
		pedidoVO.setUsuario(usuarioLogado);
		pedidoVO.setJogos(jogos);
		pedidoVO.setValorTotal(total);
		pedidoVO.setTipoPedido(tipoPedido);
		pedido.save(pedidoVO);
		return "carrinho";
	}
	
	public void definePresenteado(UsuarioVO usuario){
		if (usuarioLogado.getAmigos() != null && usuario != null) {
			usuarioPresenteado = usuario;
		}else{
			FacesContext.getCurrentInstance().addMessage("frmCarrinho", new FacesMessage("Você não tem amigos para presentear"));
		}
	}

	public void finalizarPedido() {
		Usuario usuario = new Usuario();
		try {
			if(tipoPedido.equals(TipoPedido.COMPRA)) {
				usuarioLogado.setCredito(usuarioLogado.getCredito() - total);
				usuarioLogado.addJogos(jogos);
				usuario.save(usuarioLogado);
			} else if(tipoPedido.equals(TipoPedido.PRESENTE)) {
				usuarioLogado.setCredito(usuarioLogado.getCredito() - total);
				usuarioPresenteado.addJogos(jogos);
				usuario.save(usuarioPresenteado);
				usuario.save(usuarioLogado);
			}
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
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

	public void setUsuario(UsuarioVO usuarioPresenteado) {
		this.usuarioPresenteado = usuarioPresenteado;
	}
	
	public UsuarioVO getUsuarioPresenteado() {
		return usuarioPresenteado;
	}
	
	public void setUsuarioPresenteado(UsuarioVO usuarioPresenteado) {
		this.usuarioPresenteado = usuarioPresenteado;
	}

	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public TipoPedido getTipoPedido() {
		return tipoPedido;
	}
	
	public void setTipoPedido(TipoPedido tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
}