package br.com.caelum.palestragae.blog.controllers;

import java.util.List;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class PagesController {
	
	private final ArtigoDao artigoDao;

	public PagesController(ArtigoDao artigoDao) {
		this.artigoDao = artigoDao;
	}
	
	@Get @Path("/")
	public List<Artigo> index() {
		return artigoDao.todos();
	}
	
	@Get @Path("/{id}")
	public Artigo show(Long id) {
		return artigoDao.find(id);
	}

}
