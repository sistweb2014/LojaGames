package vo;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import vo.enumerado.TipoPedido;

@Entity
@SequenceGenerator(initialValue = 1, name = "seq_pedido", sequenceName = "seq_pedido")
public class PedidoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
	private Long idPedido;
	
	private TipoPedido tipoPedido;
	private Date dataPedido;
	private  Double valorTotal;
	
	@OneToMany
	@JoinColumn(name = "idUsuario")
	private UsuarioVO usuario;
	
	@ManyToMany
	@JoinTable(name = "pedido_jogo")
	private List<JogoVO> jogos;
	
	@OneToOne
	private PagamentoVO pagamento;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public TipoPedido getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(TipoPedido tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public List<JogoVO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoVO> jogos) {
		this.jogos = jogos;
	} 
	
}
