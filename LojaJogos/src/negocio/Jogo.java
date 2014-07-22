package negocio;

import java.util.List;
import vo.JogoVO;
import dao.JogoDAO;

public class Jogo {

	public void save(JogoVO vo) {
		JogoDAO.getInstance().save(vo);
	}
	
	public void delete(JogoVO vo) {
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
