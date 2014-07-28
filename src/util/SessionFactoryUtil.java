<<<<<<< HEAD
package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import negocio.Pedido;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vo.JogoVO;
import vo.PagamentoVO;
import vo.PedidoVO;
import vo.UsuarioVO;
import vo.enumerado.TipoPagamento;
import vo.enumerado.TipoPedido;

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
		UsuarioVO usurio01 = new UsuarioVO();
		usurio01.setNome("Nome01");
		
		UsuarioVO usurio02 = new UsuarioVO();
		usurio02.setNome("Nome02");
		
		List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		usuarios.add(usurio02);
		
		usurio01.setAmigos(usuarios);
		
		s.save(usurio01);
		s.save(usurio02);
		Transaction t = s.beginTransaction();
		t.commit();

	}
=======
package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
	
>>>>>>> 4dab53a5a55cde02ee6e2ec5b5863a31caa61f03
}