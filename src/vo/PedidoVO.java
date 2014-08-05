package vo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@SequenceGenerator(initialValue = 1, name = "seq_pedido", sequenceName = "seq_pedido")
public class PedidoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pedido")
	private Long idPedido;

	@ManyToOne(cascade={CascadeType.ALL,CascadeType.REMOVE})
	@JoinColumn(name = "usuario_id")
	private UsuarioVO usuario;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "pedido_jogo")
	private List<JogoVO> jogos;

	@OneToOne(cascade={CascadeType.ALL,CascadeType.REMOVE})
	private PagamentoVO pagamento;

	@Enumerated(EnumType.STRING)
	private TipoPedido tipoPedido;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido;
	private Double valorTotal;

	public PagamentoVO getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoVO pagamento) {
		this.pagamento = pagamento;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPedido == null) ? 0 : idPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoVO other = (PedidoVO) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

}
