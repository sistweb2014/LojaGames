package negocio;

import java.util.List;
import dao.UsuarioDAO;
import vo.UsuarioVO;

public class Usuario {

	public void save(UsuarioVO vo) {
		UsuarioDAO.getInstance().save(vo);
	}
	
	public void delete(UsuarioVO vo) {
		UsuarioDAO.getInstance().delete(vo);
	}
	
	public UsuarioVO getById(long id) {
		UsuarioVO vo = UsuarioDAO.getInstance().getById(id);
		
		return vo;
	}
	
	public List<UsuarioVO> getAll() {
		return UsuarioDAO.getInstance().getAll();
	}
}
