package controle.crud_usuario;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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

	public void login(ActionEvent event) {
		try {
			vo = new UsuarioVO();
			vo = usuario.getByLoginSenha(login, senha);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			if (vo != null && !vo.getEstadoLogado()) {
				vo.setEstadoLogado(true);
				usuario.update(vo);

				ec.redirect("../modulo2/perfil_m2.jsf");
			} else {
				vo = null;
				FacesContext.getCurrentInstance().addMessage("formLogin",
						new FacesMessage("Usuário Logado no sistema"));
			}
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(e.getMessage()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage("Erro no redirecionamento aperte F5"));
		}

	}

	public void cadastrarUsuario(ActionEvent event) {
		try {
			vo.setEstadoLogado(true);
			vo.setCredito(0.0d);
			usuario.save(vo);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			ec.redirect("../modulo2/perfil_m2.jsf");
		} catch (UsuarioVOException e) {
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage(e.getMessage()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage("Erro no redirecionamento aperte F5"));
		}
	}

	public void deslogar(ActionEvent event) {
		vo.setEstadoLogado(false);
		usuario.update(vo);

		vo = new UsuarioVO();

		this.login = "";
		this.senha = "";
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		try {
			ec.redirect("../modulo1/crud_usuario.jsf");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("formOut",
					new FacesMessage("Erro no redirecionamento aperte F5"));
		}
	}

	public void excluirUsuario() {
		usuario.delete(vo);
	}

}
