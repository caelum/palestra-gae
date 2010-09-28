package br.com.caelum.palestragae.blog.controllers;

import java.util.List;

import br.com.caelum.palestragae.blog.dao.ArtigoDao;
import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {
	
	private final ArtigoDao artigoDao;

	public IndexController(ArtigoDao artigoDao) {
		this.artigoDao = artigoDao;
	}
	
	@Get @Path("/")
	public List<Artigo> index() {
		return artigoDao.todos();
	}

}
