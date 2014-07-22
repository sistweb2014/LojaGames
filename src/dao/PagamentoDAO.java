package dao;

import vo.JogoVO;
import vo.PagamentoVO;

public class PagamentoDAO extends DAO<PagamentoVO> {

	private static PagamentoDAO dao;
	
	private PagamentoDAO(Class classe) {
		super(classe);
	}
	// Singleton design pattern applied
	public static PagamentoDAO getInstance() {
		if (dao == null)
			dao = new PagamentoDAO(PagamentoVO.class);
		return dao;
	}
	
	/*public static void main(String[] args) {
		JogoDAO vo = JogoDAO.getInstance().getById(50);
		System.out.println(vo.getNome());
	}*/
}
