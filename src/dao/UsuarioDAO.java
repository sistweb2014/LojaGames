package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.EncripitarSenha;
import util.SessionFactoryUtil;
import vo.UsuarioVO;

public class UsuarioDAO extends DAO<UsuarioVO> {
	private static UsuarioDAO dao;

	private UsuarioDAO(Class classe) {
		super(classe);
	}

	public static UsuarioDAO getInstance() {
		if (dao == null)
			dao = new UsuarioDAO(UsuarioVO.class);
		return dao;
	}

	public UsuarioVO getByLoginSenha(String login, String senha) {
		Criteria c = SessionFactoryUtil.getInstance().openSession()
				.createCriteria(UsuarioVO.class);

		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("senha", EncripitarSenha.encriptar(senha)));
		
		return (UsuarioVO) c.uniqueResult();
	}

	public UsuarioVO getEmailExists(UsuarioVO usuario) {
		Criteria c = SessionFactoryUtil.getInstance().openSession()
				.createCriteria(UsuarioVO.class);

		c.add(Restrictions.eq("email", usuario.getEmail()));
		
		return (UsuarioVO) c.uniqueResult();
	}

	public UsuarioVO getLoginExists(UsuarioVO usuario) {
		Criteria c = SessionFactoryUtil.getInstance().openSession()
				.createCriteria(UsuarioVO.class);

		c.add(Restrictions.eq("login", usuario.getNome()));
		
		return (UsuarioVO) c.uniqueResult();
	}

}
