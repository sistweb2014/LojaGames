package util;

import java.util.ArrayList;
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
		UsuarioVO vo = new UsuarioVO();
		vo.setNome("Produto TESTE");
		
		JogoVO jogoVO = new JogoVO();
		jogoVO.setNome("TESTE");
		
		PedidoVO pedidoVO = new PedidoVO();
		pedidoVO.setTipoPedido(TipoPedido.COMPRA);
		
		PagamentoVO pagamentoVO = new PagamentoVO();
		pagamentoVO.setPedido(pedidoVO);

		List<JogoVO> listaJogo = new ArrayList<JogoVO>();
		listaJogo.add(jogoVO);
		
		List<UsuarioVO> listaUsuario= new ArrayList<UsuarioVO>();
		listaUsuario.add(vo);
		
		List<PedidoVO> listaPedido = new ArrayList<PedidoVO>();
		listaPedido.add(pedidoVO);
		
		vo.setJogos(listaJogo);
		vo.setPedidos(listaPedido);
		
		jogoVO.setUsuarios(listaUsuario);
		
		Transaction t = s.beginTransaction();
		s.save(vo);
		s.save(jogoVO);
		s.save(pedidoVO);
		s.save(pagamentoVO);
		t.commit();
		
		System.out.println(vo.getJogos().size());
		System.out.println(vo.getJogos().get(0).getNome());
	}
}