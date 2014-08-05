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
import javax.servlet.http.HttpSession;
import negocio.Usuario;
import vo.JogoVO;
import vo.UsuarioVO;
import controle.crud_usuario.UsuarioControle;

@ManagedBean
@SessionScoped
public class JogoBean {

	/*HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(true);
	UsuarioControle c = (UsuarioControle) session
			.getAttribute("usuarioControle");
	UsuarioVO voUsuario = c.getVo();*/
	Usuario usuarioDB = new Usuario();
	UsuarioVO usuarioVO = usuarioDB.getById(1);
	List<JogoVO> listaJogo = usuarioVO.getJogos();
	private JogoVO vo = new JogoVO();
	private DataModel<JogoVO> jogos;

	public DataModel<JogoVO> getJogos() {
		jogos = new ListDataModel<JogoVO>(listaJogo);

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
			listaJogo.add(vo);
			usuarioDB.update(usuarioVO);
			vo = new JogoVO();
			FacesContext.getCurrentInstance().addMessage("frmEdicaoJogo",
					new FacesMessage("Jogo Salvo com Sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("frmEdicaoJogo",
					new FacesMessage(e.getMessage()));
		}
	}

	public String excluirJogo() {
		listaJogo.remove(jogos.getRowData());
		System.out.println("TESTE" + vo.getNome());
		System.out.println("TESTEo2" + usuarioVO.getNome());
		// jogo.delete(vo);
		
		return null;
	}

}
