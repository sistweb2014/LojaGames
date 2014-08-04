package vo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(initialValue = 1, name = "seq_usuario", sequenceName = "seq_usuario")
public class UsuarioVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
	private Long idUsuario;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "usuarios_jogos")
	private List<JogoVO> jogos;
	
	@OneToMany(cascade={CascadeType.ALL,CascadeType.REMOVE})
	@JoinColumn(name="usuario_id")
	private List<PedidoVO> pedidos;
	
	@ManyToMany(cascade={CascadeType.ALL,CascadeType.REMOVE})
	@JoinTable(name="usuario_seguidores",joinColumns={@JoinColumn(name="idUsuario",referencedColumnName="idUsuario")},
	inverseJoinColumns={@JoinColumn(name="idSeguidor",referencedColumnName="idUsuario")})
	private List<UsuarioVO> amigos;
	
	private String nome;
	private String login;
	private String senha;
	private String email;
	private Boolean estadoLogado;
	private Double credito;
	
	public boolean validarEmail(String email) {
		Pattern p = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);

		return m.find();
	}

	public List<UsuarioVO> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<UsuarioVO> amigos) {
		this.amigos = amigos;
	}

	public List<PedidoVO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoVO> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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

	public List<JogoVO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoVO> jogos) {
		this.jogos = jogos;
	}
	
	public void addJogos(List<JogoVO> jogos){
		for (JogoVO jogo : jogos) {
			this.jogos.add(jogo);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}