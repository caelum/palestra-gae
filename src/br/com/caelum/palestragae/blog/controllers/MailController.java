package br.com.caelum.palestragae.blog.controllers;

import static br.com.caelum.vraptor.view.Results.status;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.dao.ComentarioDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.palestragae.blog.model.Comentario;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MailController {

	private final HttpServletRequest request;
	private final Result result;
	private final ArtigoDao artigoDao;
	private final ComentarioDao comentarioDao;

	public MailController(HttpServletRequest request, Result result,
			ArtigoDao artigoDao, ComentarioDao comentarioDao) {
		this.request = request;
		this.result = result;
		this.artigoDao = artigoDao;
		this.comentarioDao = comentarioDao;
	}

	@Post
	@Path("/_ah/mail/novopost@{*}")
	public void recebe() throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session, request.getInputStream());

		Artigo artigo = new Artigo();

		artigo.setTitulo(message.getSubject());
		artigo.setEmailAutor(message.getFrom()[0].toString());
		Multipart multipart = (Multipart) message.getContent();
		artigo.setTexto(multipart.getBodyPart(0).getContent().toString());

		artigoDao.salvar(artigo);

		result.use(status()).ok();
	}

	@Post
	@Path("/sendmail/{artigoid}/{comentarioid}")
	public void enviarEmail(Long artigoid, Long comentarioid)
			throws MessagingException, AddressException {
		
		Artigo artigo = artigoDao.procurar(artigoid);
		Comentario comentario = comentarioDao.procurar(comentarioid);
		
		MimeMessage msg = new MimeMessage(Session
				.getDefaultInstance(new Properties()));
		msg.setFrom(new InternetAddress("pmatiello@gmail.com"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(artigo
				.getEmailAutor()));
		msg.setSubject("Comentario adicionado no artigo " + artigo.getTitulo());
		msg.setText(comentario.getTexto());
		Transport.send(msg);
		
		System.out.println("Enviado email para " + artigo.getEmailAutor());
		
		result.use(status()).ok();
	}

}
