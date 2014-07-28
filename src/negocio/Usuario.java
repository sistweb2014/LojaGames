package negocio;

import java.util.List;

import dao.UsuarioDAO;
import vo.UsuarioVO;
import vo.excecao.UsuarioVOException;

public class Usuario {

	public void save(UsuarioVO vo) throws UsuarioVOException {
		if(vo.getNome() == null && vo.getNome().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.NOMEOBRIGATORIO);
		if(vo.getNome() == null && vo.getLogin().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.LOGINOBRIGATORIO);
		if(vo.getEmail() == null && vo.getEmail().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.EMAILOBRIGATORIO);
		if(vo.getSenha() == null && vo.getSenha().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.SENHAOBRIGATORIO);
		if(vo.validarEmail(vo.getEmail()))
			throw new UsuarioVOException(UsuarioVOException.EMAILINVALIDO);
		
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
