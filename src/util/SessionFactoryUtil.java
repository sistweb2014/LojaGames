package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vo.UsuarioVO;

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
		UsuarioVO user = new UsuarioVO();
		user.setNome("Usuario1");
		
		UsuarioVO user2 = new UsuarioVO();
		user2.setNome("Usuario2");

		UsuarioVO user3 = new UsuarioVO();
		user3.setNome("Usuario3");
		
		List<UsuarioVO> amigos = new ArrayList<UsuarioVO>();
		amigos.add(user);
		amigos.add(user2);
		amigos.add(user3);
		
		UsuarioVO user4 = new UsuarioVO();
		user4.setNome("Usuario4");
		user4.setAmigos(amigos);
		
		
		Transaction t = s.beginTransaction();
		s.save(user);
		s.save(user2);
		s.save(user3);
		s.save(user4);
		
		t.commit();
	}
}