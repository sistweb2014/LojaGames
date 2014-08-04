package controle.crud_jogo;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import negocio.Jogo;
import vo.JogoVO;
import vo.UsuarioVO;
import vo.excecao.JogoException;

@ManagedBean 
@SessionScoped
public class JogoBean {
	
	/*@ManagedProperty("usuarioControle.vo")
	private UsuarioVO usuarioVO;*/
	
	private JogoVO vo = new JogoVO();
	private Jogo jogo = new Jogo();
	private DataModel<JogoVO> jogos;

	public DataModel<JogoVO> getJogos() {
		jogos = new ListDataModel<JogoVO>(jogo.getAll());
		return jogos;
	}

	public void novo(ActionEvent evt) {
		vo = new JogoVO();
	}

	public void setJogos(DataModel<JogoVO> jogos) {
		this.jogos = jogos;
	}

	public JogoVO getVo() {
		return vo;
	}

	public void setVo(JogoVO vo) {
		this.vo = vo;
	}

	public void prepararAlterarJogo() {
		vo = (JogoVO) jogos.getRowData();
	}

	public void adicionaJogo() {
		vo = new JogoVO();
	}

	public void salvaJogo(ActionEvent evt) {
		try {
			jogo.save(vo);
			vo = new JogoVO();
			FacesContext.getCurrentInstance().addMessage("frmEdicaoJogo",
					new FacesMessage("Jogo Salvo com Sucesso!"));
		} catch (JogoException e) {
			FacesContext.getCurrentInstance().addMessage("frmEdicaoJogo",
					new FacesMessage(e.getMessage()));
		}
	}

	public String excluirJogo() {
		//usuarioVO
		/*List<JogoVO> listaJogos = usuarioVO.getJogos();
		listaJogos.remove(jogo);*/
		
		vo = jogos.getRowData();
		System.out.println("TESTE" + vo.getNome());
			//jogo.delete(vo);
		return null;
	}

}
