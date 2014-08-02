package controle.inicial;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import vo.JogoVO;

@ManagedBean
@ApplicationScoped
public class InicialControle {
	private List<JogoVO> jogos = new ArrayList<JogoVO>();
	
	public InicialControle() {
		jogos.add(new JogoVO());
		jogos.get(0).setIdJogo(1l);
		jogos.get(0).setNome("Battlefield");
		jogos.get(0).setGenero("Ação");
		jogos.get(0).setPreco(54.20);
	}

	public List<JogoVO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoVO> jogos) {
		this.jogos = jogos;
	}
}