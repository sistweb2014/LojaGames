package negocio;

import java.util.List;

import util.EncripitarSenha;
import vo.UsuarioVO;
import vo.excecao.UsuarioVOException;
import dao.UsuarioDAO;

public class Usuario {

	public void save(UsuarioVO vo) throws UsuarioVOException {
		if (vo.getNome() == null && vo.getNome().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.NOMEOBRIGATORIO);
		if (vo.getLogin() == null && vo.getLogin().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.LOGINOBRIGATORIO);
		if (vo.getEmail() == null && vo.getEmail().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.EMAILOBRIGATORIO);
		if (vo.getSenha() == null && vo.getSenha().isEmpty())
			throw new UsuarioVOException(UsuarioVOException.SENHAOBRIGATORIO);
		if (!vo.validarEmail(vo.getEmail()))
			throw new UsuarioVOException(UsuarioVOException.EMAILINVALIDO);
		if (UsuarioDAO.getInstance().getLoginExists(vo) != null)
			throw new UsuarioVOException(UsuarioVOException.LOGINEXISTENTE);
		if (UsuarioDAO.getInstance().getEmailExists(vo) != null)
			throw new UsuarioVOException(UsuarioVOException.EMAILEXISTENTE);

		vo.setSenha(EncripitarSenha.encriptar(vo.getSenha()));

		UsuarioDAO.getInstance().save(vo);
	}

	public UsuarioVO getByLoginSenha(String login, String senha)
			throws UsuarioVOException {
		if (login == null && login.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.LOGINOBRIGATORIO);
		if (senha == null && senha.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.SENHAOBRIGATORIO);

		UsuarioVO vo = UsuarioDAO.getInstance().getByLoginSenha(login, senha);

		if (vo == null)
			throw new UsuarioVOException(UsuarioVOException.LOGINFAIL);

		return vo;
	}

	public void update(UsuarioVO vo) {
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
