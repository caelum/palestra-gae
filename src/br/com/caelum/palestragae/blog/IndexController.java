/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package br.com.caelum.palestragae.blog;

import java.util.List;

import br.com.caelum.palestragae.blog.dao.PostDao;
import br.com.caelum.palestragae.blog.model.Post;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {

	private final PostDao postDao;

	public IndexController(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Path("/")
	public List<Post> index() {
		create();
		return postDao.todos();
	}
	
	public void create() {
		Post post = new Post();
		post.setTitulo("Titulo do Post");
		post.setTexto("Texto do Post");
		postDao.salva(post);
	}
	
}
