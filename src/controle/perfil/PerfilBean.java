package controle.perfil;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import vo.UsuarioVO;
import negocio.Usuario;

@ManagedBean
@SessionScoped
public class PerfilBean {

	private Usuario usuarioBE = new Usuario();
	private List<UsuarioVO> listaUsuario = usuarioBE.getAll();

	public List<UsuarioVO> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioVO> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

}
