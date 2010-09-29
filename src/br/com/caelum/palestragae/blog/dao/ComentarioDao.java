package br.com.caelum.palestragae.blog.dao;

import java.util.List;

import br.com.caelum.palestragae.blog.model.Artigo;
import br.com.caelum.palestragae.blog.model.Comentario;
import br.com.caelum.vraptor.ioc.Component;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Component
public class ComentarioDao {
	
	private final Objectify objectify;
	
	static {
		ObjectifyService.register(Comentario.class);
	}
	
	public ComentarioDao(Objectify objectify) {
		this.objectify = objectify;		
	}
	
	public List<Comentario> doArtigo(Artigo artigo) {
		return objectify.query(Comentario.class).filter("artigo", artigo).list();
	}

	public void salvar(Comentario comentario) {
		objectify.put(comentario);		
	}
	
	public Comentario procurar(Long id) {
		return objectify.find(Comentario.class, id);
	}
}
