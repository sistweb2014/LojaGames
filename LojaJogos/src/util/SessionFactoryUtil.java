package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vo.JogoVO;
import vo.enumerado.TipoClassificacao;


public class SessionFactoryUtil {

	private static org.hibernate.SessionFactory sessionFactory;

	private SessionFactoryUtil() {
	}

	static {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getInstance() {
		return sessionFactory;
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void close() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		sessionFactory = null;
	}

      public static void main(String[] args) {
    	Session s = SessionFactoryUtil.getInstance().openSession();
  		JogoVO vo = new JogoVO();
  		vo.setNome("Obludia");
  		vo.setGenero("Ação");
  		vo.setImagem("img!!");
  		vo.setPreco(40.0);
  		vo.setClassificacao(TipoClassificacao.DUAS_ESTRELAS);
  		Transaction t = s.beginTransaction();
  		s.save(vo);
  		t.commit();
	}
}
