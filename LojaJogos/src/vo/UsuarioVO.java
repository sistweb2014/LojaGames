package vo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import vo.excecao.UsuarioVOException;

@Entity
@SequenceGenerator(initialValue = 1, name = "seq_usuario", sequenceName = "seq_usuario")
public class UsuarioVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long idUsuario;

	private String nome;
	private String login;
	private String senha;
	private String email;
	private Boolean estadoLogado;
	private Double credito;

	@OneToMany(mappedBy = "usuario" ,cascade = CascadeType.ALL)
	private List<PedidoVO> pedidos;

	@ManyToMany
	@JoinTable(name = "USUARIO_JOGO", joinColumns = @JoinColumn(name = "id_usuario"),
	inverseJoinColumns = @JoinColumn(name = "id_jogo"))
	private List<JogoVO> jogos = new ArrayList<JogoVO>();
    
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws UsuarioVOException {
		if(nome.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.NOMEOBRIGATORIO);
		
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws UsuarioVOException {
		if(login.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.LOGINOBRIGATORIO);
		
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws UsuarioVOException {
		if(senha.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.SENHAOBRIGATORIO);
		
		if(senha.contains(" "))
			throw new UsuarioVOException(UsuarioVOException.SENHAINVALIDA);
		
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UsuarioVOException {
		if(email.isEmpty())
			throw new UsuarioVOException(UsuarioVOException.EMAILOBRIGATORIO);
		
		if(validarEmail(email))
			throw new UsuarioVOException(UsuarioVOException.EMAILINVALIDA);		
		
		this.email = email;
	}

	public Boolean getEstadoLogado() {
		return estadoLogado;
	}

	public void setEstadoLogado(Boolean estadoLogado) {
		this.estadoLogado = estadoLogado;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public List<PedidoVO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoVO> pedidos) {
		this.pedidos = pedidos;
	}
	
	public boolean validarEmail(String email) {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);

		return m.find();
	}
	
}
