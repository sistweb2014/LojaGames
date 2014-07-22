package dao;

import vo.JogoVO;
import vo.UsuarioVO;

public class JogoDAO extends DAO<JogoVO> {

private static JogoDAO dao;
	
	private JogoDAO(Class classe) {
		super(classe);
	}
	// Singleton design pattern applied
	public static JogoDAO getInstance() {
		if (dao == null)
			dao = new JogoDAO(UsuarioVO.class);
		return dao;
	}
	
	/*public static void main(String[] args) {
		JogoDAO vo = JogoDAO.getInstance().getById(50);
		System.out.println(vo.getNome());
	}*/
}
