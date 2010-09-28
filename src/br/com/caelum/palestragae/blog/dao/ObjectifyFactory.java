package br.com.caelum.palestragae.blog.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@ApplicationScoped
@Component
public class ObjectifyFactory implements ComponentFactory<Objectify>{
	
	@Override
	public Objectify getInstance() {
		return ObjectifyService.begin();
	}

}
