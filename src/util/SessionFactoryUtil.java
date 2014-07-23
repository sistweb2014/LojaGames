package util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import vo.JogoVO;
import vo.PedidoVO;
import vo.UsuarioVO;
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

		UsuarioVO vo = new UsuarioVO();
		vo.setNome("Produto X");

		JogoVO jogo = new JogoVO();
		jogo.setNome("Half-Life 2");
		List<JogoVO> lista = new ArrayList<JogoVO>();
		lista.add(jogo);

		PedidoVO pedido = new PedidoVO();
		pedido.setJogos(lista);
		pedido.setTipoPedido(TipoPedido.COMPRA);

		Transaction t = s.beginTransaction();
//		usuarios = (List<UsuarioVO>) vo.getChildren();
//		for (UsuarioVO jogoVO : usuarios) {
//			System.out.println(jogoVO.getNome());
//		}
		s.save(vo);
//		UsuarioVO vo = UsuarioDAO.getInstance().getById(50);
		pedido.setUsuario(vo);

		s.save(pedido);

		t.commit();
	}
}