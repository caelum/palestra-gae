package br.com.caelum.palestragae.blog.controllers;

import java.util.List;

import com.googlecode.objectify.Key;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.dao.ComentarioDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.palestragae.blog.model.Comentario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PagesController {
	
	private final Result result;
	private final ArtigoDao artigoDao;
	private final ComentarioDao comentarioDao;

	public PagesController(Result result, ArtigoDao artigoDao, ComentarioDao comentarioDao) {
		this.result = result;
		this.artigoDao = artigoDao;
		this.comentarioDao = comentarioDao;
	}
	
	@Get @Path("/")
	public List<Artigo> index() {
		return artigoDao.todos();
	}
	
	@Get @Path("/{id}")
	public void show(Long id) {
		Artigo artigo = artigoDao.procurar(id);
		result.include("artigo", artigo);
		result.include("comentarioList", comentarioDao.doArtigo(artigo));
	}
	
	@Post @Path("/{id}")
	public void comentar(Long id, Comentario comentario) {
		comentario.setArtigo(new Key<Artigo>(Artigo.class, id));
		comentarioDao.salvar(comentario);
		result.forwardTo(PagesController.class).show(id);
	}

}
