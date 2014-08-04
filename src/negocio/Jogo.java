package negocio;

import java.util.List;

import vo.JogoVO;
import vo.excecao.JogoException;
import dao.JogoDAO;

public class Jogo {

	public void save(JogoVO vo) throws JogoException {
		if (vo.getNome()==null || vo.getNome().trim().equals(""))
			throw new JogoException(JogoException.NOME_OBRIGATORIO);
		if (vo.getNome().trim().length()<5 || vo.getNome().trim().length()>60)
			throw new JogoException(JogoException.NOME_TAMANHO);
		if (vo.getPreco()<0)
			throw new JogoException(JogoException.QTDE_NEGATIVA);
		
		JogoDAO.getInstance().save(vo);
	}
	
	public void update(JogoVO vo) {
		JogoDAO.getInstance().save(vo);
	}
	
	public void delete(JogoVO vo) throws JogoException {
		if (vo.getPreco()>0)
			throw new JogoException(JogoException.QTDE_POSITIVA);
		JogoDAO.getInstance().delete(vo);
	}
	
	public JogoVO getById(long id) {
		JogoVO vo = JogoDAO.getInstance().getById(id);
		
		return vo;
	}
	
	public List<JogoVO> getAll() {
		return JogoDAO.getInstance().getAll();
	}
}
