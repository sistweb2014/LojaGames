package negocio;

import java.util.List;

import vo.PagamentoVO;
import dao.PagamentoDAO;

public class Pagamento {

	public void save(PagamentoVO vo) {
		PagamentoDAO.getInstance().save(vo);
	}
	
	public void delete(PagamentoVO vo) {
		PagamentoDAO.getInstance().delete(vo);
	}
	
	public PagamentoVO getById(long id) {
		PagamentoVO vo = PagamentoDAO.getInstance().getById(id);
		
		return vo;
	}
	
	public List<PagamentoVO> getAll() {
		return PagamentoDAO.getInstance().getAll();
	}
}
