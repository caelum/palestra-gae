package br.com.caelum.palestragae.blog.dao;

import java.util.List;

import br.com.caelum.palestragae.blog.model.Post;
import br.com.caelum.vraptor.ioc.Component;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Component
public class PostDao {
	
	private final Objectify objectify;
	
	static {
		ObjectifyService.register(Post.class);
	}

	public PostDao(Objectify objectify) {
		this.objectify = objectify;
	}
	
	public List<Post> todos() {
		return objectify.query(Post.class).list();
	}
	
	public void salva(Post post) {
		objectify.put(post);
	}

}
