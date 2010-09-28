package br.com.caelum.palestragae.blog.infra;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@ApplicationScoped
@Component
public class ObjectifyFactory implements ComponentFactory<Objectify> {
	
	public Objectify getInstance() {
		return ObjectifyService.begin();
	}

}
