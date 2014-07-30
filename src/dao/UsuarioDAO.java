package dao;

import org.hibernate.Criteria;
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
		Criteria c = SessionFactoryUtil.getInstance().openSession().createCriteria(this.getClass());
		
		c.add(Restrictions.eq("login", login));
		c.add(Restrictions.eq("senha", EncripitarSenha.encriptar(senha)));
		
		return (UsuarioVO) c.uniqueResult();
	}
}
