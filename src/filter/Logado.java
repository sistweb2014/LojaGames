package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controle.crud_usuario.UsuarioControle;

@WebFilter("/modulo1/*")
public class Logado implements Filter {

	public Logado() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		UsuarioControle controle = (UsuarioControle) session.getAttribute("usuarioControle");

		if (verificarUsuario(controle))
			res.sendRedirect(req.getContextPath() + "/modulo2/perfil_m2.jsf");
		else
			chain.doFilter(req, res);

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	private boolean verificarUsuario(UsuarioControle controle) {
		if (controle != null && controle.getVo() != null
				&& controle.getVo().getNome() != null
				&& controle.getVo().getLogin() != null
				&& controle.getVo().getSenha() != null
				&& controle.getVo().getEstadoLogado() != null)
			if (controle.getVo().getEstadoLogado())
				return true;
			else
				return true;
		else
			return false;
	}
}
