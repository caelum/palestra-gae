package br.com.caelum.palestragae.blog.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MailController {
	
	private final HttpServletRequest request;
	private final Result result;
	private final ArtigoDao artigoDao;

	public MailController(HttpServletRequest request, Result result, ArtigoDao artigoDao) {
		this.request = request;
		this.result = result;
		this.artigoDao = artigoDao;
	}
	
	@Path("/_ah/mail/novopost@{*}")
	@Post
	public void recebe() throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session, request.getInputStream());
		
		Artigo artigo = new Artigo();
		
		artigo.setTitulo(message.getSubject());
		Multipart multipart = (Multipart) message.getContent();
		artigo.setTexto(multipart.getBodyPart(0).getContent().toString());
		
		artigoDao.salva(artigo);
		
		result.use(status()).ok();
	}

}
