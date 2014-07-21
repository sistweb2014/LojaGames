package vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import vo.enumerado.TipoPagamento;

@Entity
@SequenceGenerator(initialValue = 1, name = "seq_pagamento", sequenceName = "seq_pagamento")
public class PagamentoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagamento")
	private Long idPagamento;

	private TipoPagamento tipoPagamento;
	private Date datapagamento;

	@OneToOne(mappedBy="endereco")
	private PedidoVO pedido;

	public Long getIdPagamento() {
		return idPagamento;
	}

	public Date getDatapagamento() {
		return datapagamento;
	}

	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}

	public PedidoVO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
