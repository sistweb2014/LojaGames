package vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import vo.enumerado.TipoPagamento;

@Entity
@SequenceGenerator(initialValue = 1, name = "seq_pagamento", sequenceName = "seq_pagamento")
public class PagamentoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pagamento")
	private Long idPagamento;

	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datapagamento", nullable = false)
	private Date datapagamento;
	
	@OneToOne(mappedBy = "pagamento")
	private PedidoVO pedido;


	public Long getIdPagamento() {
		return idPagamento;
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

	
	

}
