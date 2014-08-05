package controle.crud_jogo;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import negocio.Usuario;
import vo.JogoVO;
import vo.UsuarioVO;

@ManagedBean
@SessionScoped
public class JogoBeanTeste {
	
	private JogoVO[] selectedJogo;
	
	Usuario usuarioDB = new Usuario();
	UsuarioVO usuarioVO = usuarioDB.getById(1);
	List<JogoVO> listaJogo = usuarioVO.getJogos();
	
	public void processar() {
		StringBuilder sb = new StringBuilder("Despesas selecionadas: ");
		for (JogoVO j : listaJogo) {
			sb.append(j.getNome()).append(", ");
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jogo '" + sb.toString(), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onEdit(RowEditEvent event) {
		JogoVO j = (JogoVO) event.getObject();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jogo " + j.getIdJogo() + " alterado", null);
		FacesContext.getCurrentInstance().addMessage(null,  msg);
	}
	
	public void onCancel(RowEditEvent event) {
		JogoVO j = (JogoVO) event.getObject();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alteração na despesa " + j.getIdJogo() + " cancelada", null);
		FacesContext.getCurrentInstance().addMessage(null,  msg);
	}

	public JogoVO[] getSelectedJogo() {
		return selectedJogo;
	}

	public void setSelectedJogo(JogoVO[] selectedJogo) {
		this.selectedJogo = selectedJogo;
	}

	public Usuario getUsuarioDB() {
		return usuarioDB;
	}

	public void setUsuarioDB(Usuario usuarioDB) {
		this.usuarioDB = usuarioDB;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public List<JogoVO> getListaJogo() {
		return listaJogo;
	}

	public void setListaJogo(List<JogoVO> listaJogo) {
		this.listaJogo = listaJogo;
	}

}
