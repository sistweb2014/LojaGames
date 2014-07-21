package negocio;

import java.util.List;
import vo.PedidoVO;
import dao.PedidoDAO;

public class Pedido {

	public void save(PedidoVO vo) {
		PedidoDAO.getInstance().save(vo);
	}
	
	public void delete(PedidoVO vo) {
		PedidoDAO.getInstance().delete(vo);
	}
	
	public PedidoVO getById(long id) {
		PedidoVO vo = PedidoDAO.getInstance().getById(id);
		
		return vo;
	}
	
	public List<PedidoVO> getAll() {
		return PedidoDAO.getInstance().getAll();
	}
}
