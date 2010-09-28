package br.com.caelum.palestragae.blog.model;

import javax.persistence.Id;

public class Post {
	
	@Id
	private Long id;
	
	private String titulo;
	private String texto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
