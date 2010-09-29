package br.com.caelum.palestragae.blog.controllers;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.dao.ComentarioDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.palestragae.blog.model.Comentario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.googlecode.objectify.Key;

@Resource
public class PagesController {

	private final Result result;
	private final ArtigoDao artigoDao;
	private final ComentarioDao comentarioDao;

	public PagesController(Result result, ArtigoDao artigoDao,
			ComentarioDao comentarioDao) {
		this.result = result;
		this.artigoDao = artigoDao;
		this.comentarioDao = comentarioDao;
	}

	@Get
	@Path("/")
	public List<Artigo> index() {
		return artigoDao.todos();
	}

	@Get
	@Path("/{id}")
	public void show(Long id) {
		Artigo artigo = artigoDao.procurar(id);
		result.include("artigo", artigo);
		result.include("comentarioList", comentarioDao.doArtigo(artigo));
	}

	@Post
	@Path("/{id}")
	public void comentar(Long id, Comentario comentario)
			throws AddressException, MessagingException {
		Artigo artigo = artigoDao.procurar(id);

		comentario.setArtigo(new Key<Artigo>(Artigo.class, id));
		comentarioDao.salvar(comentario);

		enviarEmail(comentario, artigo);

		result.forwardTo(PagesController.class).show(id);
	}

	private void enviarEmail(Comentario comentario, Artigo artigo)
			throws MessagingException, AddressException {
		MimeMessage msg = new MimeMessage(Session
				.getDefaultInstance(new Properties()));
		msg.setFrom(new InternetAddress(
				"pmatiello@gmail.com"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				artigo.getEmailAutor()));
		msg.setSubject("Comentario adicionado no artigo " + artigo.getTitulo());
		msg.setText(comentario.getTexto());
		Transport.send(msg);
	}

}
