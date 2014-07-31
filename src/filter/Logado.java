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

@WebFilter("/modulo2/*")
public class Logado implements Filter {

	public Logado() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		UsuarioControle controle = (UsuarioControle) session
				.getAttribute("usuarioControle");

		try {
			if (!controle.getVo().getEstadoLogado())
				resp.sendRedirect("../modulo1/crud_usuario.jsf");
			else
				chain.doFilter(request, response);
		} catch (NullPointerException e) {
			resp.sendRedirect("../modulo1/crud_usuario.jsf");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
