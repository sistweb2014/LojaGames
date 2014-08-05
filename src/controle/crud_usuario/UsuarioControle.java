package controle.crud_usuario;

import java.io.IOException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import negocio.Usuario;
import vo.UsuarioVO;
import vo.excecao.UsuarioVOException;

@ManagedBean
@SessionScoped
public class UsuarioControle {
	protected static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	protected static final String SMTP_AUTH_USER = "sistweb2012@hotmail.com";
	protected static final String SMTP_AUTH_PWD = "12345678Si";

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

				addMensage("Usuário", vo.getNome() + " Logado com sucesso!");
				ec.redirect("../modulo2/perfil_m2.jsf");
			} else {
				vo = null;
				login = "";
				senha = "";
				FacesContext.getCurrentInstance().addMessage("formLogin",
						new FacesMessage("Usuário Logado no sistema"));
			}
		} catch (UsuarioVOException e) {
			vo = new UsuarioVO();
			login = "";
			senha = "";
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage(e.getMessage()));
		} catch (IOException e) {
			vo = new UsuarioVO();
			login = "";
			senha = "";
			FacesContext.getCurrentInstance().addMessage("formLogin",
					new FacesMessage("Erro no redirecionamento aperte F5"));
		}

	}

	public void cadastrarUsuario(ActionEvent event) {
		try {
			vo.setEstadoLogado(false);
			vo.setCredito(0.0d);

			String auxSenha = vo.getSenha() != null ? vo.getSenha() : "";

			usuario.save(vo);
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			addMensage("Usuário", "Usuário Cadastrado com sucesso!");

			String texto = vo.getNome()
					+ "foi cadastrado com sucesso - Login: " + vo.getLogin()
					+ " - Senha: " + auxSenha;

			sendEmail(vo.getEmail(), SMTP_AUTH_PWD, SMTP_AUTH_USER, "Purpose",
					texto);
			
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage("Usuário Cadastrado com sucesso, verificar o e-mail: " + vo.getEmail()));
			vo = new UsuarioVO();
			login = "";
			senha = "";
		} catch (UsuarioVOException e) {
			vo = new UsuarioVO();
			login = "";
			senha = "";
			FacesContext.getCurrentInstance().addMessage("formCadastro",
					new FacesMessage(e.getMessage()));
		}
	}

	public void deslogar(ActionEvent event) {
		vo.setEstadoLogado(false);
		this.login = "";
		this.senha = "";

		usuario.update(vo);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		vo = new UsuarioVO();

		try {
			addMensage("Usuário", "Usuário Desconectado com sucesso!");
			ec.redirect("../modulo1/crud_usuario.jsf");
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("formOut",
					new FacesMessage("Erro no redirecionamento aperte F5"));
		}
	}

	public void excluirUsuario() {
		usuario.delete(vo);
	}

	public void addMensage(String sumario, String detalhes) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				sumario, detalhes);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void sendEmail(String para, String senha, String host,
			String titulo, String texto) {
		Properties props = System.getProperties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session s = Session.getDefaultInstance(props, new SMTPAuthenticator() {
		});

		try {
			Message m = new MimeMessage(s);

			m.setFrom(new InternetAddress(host));
			m.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(para));
			m.setSubject(titulo);
			m.setText(texto);

			Transport.send(m);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
