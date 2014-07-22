package negocio;

import dao.UsuarioDAO;
import vo.PresenteVO;

public class Compra {
	public void finalizar(boolean isPresente, PresenteVO presente){
		//Presente
		
		if (isPresente) {
			presente.getPresenteado().getJogos().add(presente.getJogo());
			
			UsuarioDAO.getInstance().save(presente.getPresenteado());
		}
	}
}