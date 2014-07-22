package vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import vo.enumerado.TipoPedido;

@Entity
public class PedidoVO {

	@Id
	@SequenceGenerator(initialValue = 1, name = "seq_pedido", sequenceName = "seq_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
	private Long idPedido;
	
	@Enumerated(EnumType.STRING)
	private TipoPedido tipoPedido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataPedido", nullable = false)
	private Date dataPedido;	
	
	private  Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioVO usuario;
	
	@ManyToMany
	@JoinTable(name = "PEDIDO_JOGO", 
	joinColumns = @JoinColumn(name = "id_pedido"),
	inverseJoinColumns = @JoinColumn(name = "id_jogo"))
	private List<JogoVO> jogos = new ArrayList<JogoVO>();
	
	@OneToOne
	@JoinColumn(name= "pagamento_id")
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

	public PagamentoVO getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoVO pagamento) {
		this.pagamento = pagamento;
	}

    
	
	
}
