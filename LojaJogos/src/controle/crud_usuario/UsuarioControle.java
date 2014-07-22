package controle.crud_usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import negocio.Usuario;
import vo.UsuarioVO;

@ManagedBean
@SessionScoped
public class UsuarioControle {
	
	private UsuarioVO vo = new UsuarioVO();
	private Usuario usuario = new Usuario();
	
	public void novo(ActionEvent event) {
		vo = new UsuarioVO();
	}

	public UsuarioVO getVo() {
		return vo;
	}

	public void setVo(UsuarioVO vo) {
		this.vo = vo;
	}
	
	public void cadastarUsuario(ActionEvent event) {
		usuario.save(vo);
		vo = new UsuarioVO();
	}
	
}
