package util;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocio.Jogo;
import negocio.Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import controle.crud_usuario.UsuarioControle;
import vo.JogoVO;
import vo.UsuarioVO;
import vo.excecao.JogoException;
import vo.excecao.UsuarioVOException;

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
		
		JogoVO jogo7 = new JogoVO();
		jogo7.setImagem("fifa.jpg");
		jogo7.setNome("Fifa 14");
		
		Jogo jogo = new Jogo();
		Usuario usuario = new Usuario();
		

			jogo.update(jogo1);
			jogo.update(jogo2);
			jogo.update(jogo3);
			jogo.update(jogo4);
			jogo.update(jogo5);
			jogo.update(jogo6);
		
			usuario.update(usuario1);
			usuario.update(usuario2);
		
		
		/*UsuarioVO usuario1 = new UsuarioVO();
		usuario1.setNome("Teste01");
		
		UsuarioVO usuario2 = new UsuarioVO();
		usuario2.setNome("Teste02");
		
		List<JogoVO> lista01 = new ArrayList<JogoVO>();
		List<JogoVO> lista02 = new ArrayList<JogoVO>();
		
		JogoVO jogo = new JogoVO();
		jogo.setNome("Jogo001");
		
		lista01.add(jogo);
		lista02.add(jogo);
		
		usuario1.setJogos(lista01);
		usuario2.setJogos(lista02);
		
		s.save(jogo);
		s.save(usuario1);
		s.save(usuario2);*/

		/*Usuario usuarioDB = new Usuario();
		UsuarioVO voUsuario = usuarioDB.getById(13);
		
		List<JogoVO> jogos = voUsuario.getJogos();

		JogoVO jogoVO = new JogoVO();			
		jogoVO.setNome("Nome");
		
		jogos.add(jogoVO);
		
		usuarioDB.update(voUsuario);*/
	}
}