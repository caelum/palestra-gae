package br.com.caelum.palestragae.blog.dao;

import java.util.List;

import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.vraptor.ioc.Component;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Component
public class ArtigoDao {
	
	private final Objectify objectify;
	
	static {
		ObjectifyService.register(Artigo.class);
	}

	public ArtigoDao(Objectify objectify) {
		this.objectify = objectify;
	}

	public List<Artigo> todos() {
		return objectify.query(Artigo.class).list();
	}

	public void salva(Artigo artigo) {
		objectify.put(artigo);
	}

}
