package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vo.JogoVO;
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
		
		UsuarioVO usuario1 = new UsuarioVO();
		usuario1.setLogin("root");
		usuario1.setNome("Leonardo");
		usuario1.setSenha("123");
		usuario1.setCredito(2000.0);
		
		UsuarioVO usuario2 = new UsuarioVO();
		usuario2.setLogin("usuario");
		usuario2.setNome("Cherubini");
		usuario2.setSenha("123");
		usuario2.setCredito(1000.0);
		
		JogoVO jogo1 = new JogoVO();
		jogo1.setImagem("clos2.jpg");
		jogo1.setNome("Castlevania 2");
		
		JogoVO jogo2 = new JogoVO();
		jogo2.setImagem("dmc.jpg");
		jogo2.setNome("Devil May Cry");
		
		JogoVO jogo3 = new JogoVO();
		jogo3.setImagem("need.jpg");
		jogo3.setNome("Need for Speed");
		
		JogoVO jogo4 = new JogoVO();
		jogo4.setImagem("sonic.jpg");
		jogo4.setNome("Sonic Generation");
		
		JogoVO jogo5 = new JogoVO();
		jogo5.setImagem("titanfall.jpg");
		jogo5.setNome("Titanfall");
		
		JogoVO jogo6 = new JogoVO();
		jogo6.setImagem("lenda_heroi.jpg");
		jogo6.setNome("A lenda do heroi");
		
		JogoVO jogo8 = new JogoVO();
		jogo8.setImagem("skyrim.jpg");
		jogo8.setNome("The Elder Scrolls - Skyrim");
		
		JogoVO jogo7 = new JogoVO();
		jogo7.setImagem("fifa.jpg");
		jogo7.setNome("Fifa 14");
		
		s.save(jogo1);
		s.save(jogo2);
		s.save(jogo3);
		s.save(jogo4);
		s.save(jogo5);
		s.save(jogo6);
		s.save(jogo7);
		s.save(jogo8);
		
		Transaction t = s.beginTransaction();
		
		t.commit();
	}
}