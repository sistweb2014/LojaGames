package dao;

import vo.JogoVO;
import vo.PedidoVO;
import vo.UsuarioVO;

public class PedidoDAO extends DAO<PedidoVO> {

private static PedidoDAO dao;
	
	private PedidoDAO(Class classe) {
		super(classe);
	}
	// Singleton design pattern applied
	public static PedidoDAO getInstance() {
		if (dao == null)
			dao = new PedidoDAO(PedidoVO.class);
		return dao;
	}
	
	/*public static void main(String[] args) {
		JogoDAO vo = JogoDAO.getInstance().getById(50);
		System.out.println(vo.getNome());
	}*/
}
