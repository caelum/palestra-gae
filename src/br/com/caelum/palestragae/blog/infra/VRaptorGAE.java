package br.com.caelum.palestragae.blog.infra;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.VRaptor;

public class VRaptorGAE extends VRaptor {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		
		String uri = request.getRequestURI();

		if (uri.startsWith("/_ah") && !uri.startsWith("/_ah/mail/")) {
			chain.doFilter(req, res);
		} else {
			super.doFilter(req, res, chain);
		}
	}
	
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		super.init(cfg);
	}
}