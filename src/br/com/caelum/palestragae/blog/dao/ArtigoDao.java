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

	public void salvar(Artigo artigo) {
		objectify.put(artigo);
	}

	public Artigo procurar(Long id) {
		return objectify.get(Artigo.class, id);
	}

}
