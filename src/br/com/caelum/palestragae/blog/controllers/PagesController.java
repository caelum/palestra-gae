package br.com.caelum.palestragae.blog.controllers;

import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.dao.ComentarioDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.palestragae.blog.model.Comentario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions.Builder;
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

	@Path("/")
	public List<Artigo> index() {
//		return Arrays.asList(
//				new Artigo("Meu post", "pmatiello@gmail", "Post legal"),
//				new Artigo("Meu post", "pmatiello@gmail", "Post legal"),
//				new Artigo("Meu post", "pmatiello@gmail", "Post legal")
//		);
		return artigoDao.todos();
	}

	@Get
	@Path("/{id}")
	public void mostrar(Long id) {
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

		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(Builder.url("/tasks/sendmail/")
				  .param("artigoid", artigo.getId().toString())
				  .param("comentarioid", comentario.getId().toString()));

		result.forwardTo(PagesController.class).mostrar(id);
	}


}
