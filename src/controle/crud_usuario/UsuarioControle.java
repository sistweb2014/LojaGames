package controle.crud_usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import negocio.Usuario;
import vo.UsuarioVO;
import vo.excecao.UsuarioVOException;

@ManagedBean
@SessionScoped
public class UsuarioControle {
	private Usuario usuario;
	private UsuarioVO vo;
	private String login;
	private String senha;

	public UsuarioControle() {
		usuario = new Usuario();
		vo = new UsuarioVO();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioVO getVo() {
		return vo;
	}

	public void setVo(UsuarioVO vo) {
		this.vo = vo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String login(ActionEvent event) {
		String url = "";
		try {
			vo = new UsuarioVO();
			vo = usuario.getByLoginSenha(login, senha);
			
			if (vo != null) {
				vo.setEstadoLogado(true);
				usuario.update(vo);
			}

			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(vo.getNome() + " Logado com sucesso!"));
			
			return "/modulo2/perfil_m2.xhtml?faces-redirect=true";
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(e.getMessage()));
			return "/modulo1/crud_usuario.xhtml?faces-redirect=true";
		}
		
	}

	public void cadastrarUsuario(ActionEvent event) {
		try {
			vo.setEstadoLogado(true);
			vo.setCredito(0.0d);
			usuario.save(vo);

			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage("Usuário Cadstrado com sucesso!"));
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage(e.getMessage()));
		}
	}
	
	public void deslogar(ActionEvent event) {
		vo.setEstadoLogado(false);
		
		usuario.update(vo);
		vo = new UsuarioVO();
		
		vo.setLogin("");
	}
	
	public void excluirUsuario() {
		usuario.delete(vo);
	}

}
