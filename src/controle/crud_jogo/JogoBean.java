package controle.crud_jogo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import negocio.Usuario;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
	UsuarioVO usuarioVO = c.getVo();*/
	Usuario usuarioDB = new Usuario();
	UsuarioVO usuarioVO = usuarioDB.getById(1);
	List<JogoVO> listaJogo = usuarioVO.getJogos();
	private JogoVO vo = new JogoVO();
	private DataModel<JogoVO> jogos;
	private UploadedFile file;
	
	public void onUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		String path = "C:/imagens";
		System.out.println("TESTE - " + FacesContext.getCurrentInstance().getExternalContext().getRealPath("..//.."));
		copyFileToDir(uploadedFile, path);
		String name = uploadedFile.getFileName();
		long size = uploadedFile.getSize();
		
		FacesMessage msg = new FacesMessage("O arquivo " + name + " foi enviado. Tamanho " + size + " bytes.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private static void copyFileToDir(UploadedFile file, String dir) {
		if (file == null) {
			return;
		}
		
		OutputStream out = null;
		
		try {
			try {
				InputStream in = file.getInputstream();
				
				File outputFile = new File(dir, file.getFileName());	
				out = new FileOutputStream(outputFile);
				
				byte[] buffer = new byte[1024];
				
				while (true) {
					int bytes = in.read(buffer);
					if (bytes < 0) {
						break;
					}
					
					out.write(buffer, 0, bytes);
				}
			} finally {
				if (out != null) {
					out.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

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
			if(!listaJogo.contains(vo))
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
		JogoVO j = jogos.getRowData();
		listaJogo.remove(j);
		usuarioDB.update(usuarioVO);
		
		return null;
	}

}
