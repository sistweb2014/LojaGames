package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.SessionFactoryUtil;

public class DAO<VO> {
	private Class classe;

	public DAO(Class classe) {
		this.classe = classe;
	}
	
	public void save(VO vo) {
		Session s = SessionFactoryUtil.getInstance().openSession();
		Transaction t = s.beginTransaction();
		s.merge(vo);
		t.commit();

	}
	
	public void delete(VO vo) {
		Session s = SessionFactoryUtil.getInstance().openSession();
		Transaction t = s.beginTransaction();
		s.delete(vo);
		t.commit();
	}
	
	public VO getById(long id) {
		return (VO) SessionFactoryUtil.getInstance()
				.openSession().get(classe, id);
	}
	
	public List<VO> getAll() {
		return SessionFactoryUtil.getInstance()
				.openSession().createCriteria(classe).list();
	}
	
}
