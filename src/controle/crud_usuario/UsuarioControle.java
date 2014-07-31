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
	private boolean logou;

	public UsuarioControle() {
		usuario = new Usuario();
		vo = new UsuarioVO();
		logou = false;
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

	public boolean isLogou() {
		return logou;
	}

	public void setLogou(boolean logou) {
		this.logou = logou;
	}

	public String login(ActionEvent event) {
		try {
			vo = new UsuarioVO();
			vo = usuario.getByLoginSenha(login, senha);
			
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(vo.getNome() + " Logado com sucesso!"));
			
			logou = true;
			vo.setEstadoLogado(true);
			
			usuario.deslogar(vo);
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(e.getMessage()));
		}
		return "/modulo2/perfil_m2";
	}

	public String cadastrarUsuario(ActionEvent event) {
		try {
			vo = new UsuarioVO();
				
			vo.setEstadoLogado(true);
			vo.setCredito(0.0d);
			usuario.save(vo);
			
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage("Usuário Cadstrado com sucesso!"));
			
			logou = true;
			vo.setEstadoLogado(true);
			
			usuario.deslogar(vo);
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage(e.getMessage()));
		}
		return "/modulo2/perfil_m2";
	}

	public String deslogar() {
		logou = false;
		vo.setEstadoLogado(false);
		usuario.deslogar(vo);
		
		return "/modulo1/crud_usuario";
	}
	
	public void excluirUsuario() {
		usuario.delete(vo);
	}

}
