package dao;

import vo.UsuarioVO;

public class UsuarioDAO extends DAO<UsuarioVO> {

	private static UsuarioDAO dao;
	
	private UsuarioDAO(Class classe) {
		super(classe);
	}
	// Singleton design pattern applied
	public static UsuarioDAO getInstance() {
		if (dao == null)
			dao = new UsuarioDAO(UsuarioVO.class);
		return dao;
	}
	
	/*public static void main(String[] args) {
		UsuarioVO vo = UsuarioDAO.getInstance().getById(50);
		System.out.println(vo.getNome());
	}*/
}
