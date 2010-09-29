package br.com.caelum.palestragae.blog.model;

import javax.persistence.Id;

import com.googlecode.objectify.Key;

public class Comentario {
	
	@Id
	private Long id;
	private Key<Artigo> artigo;
	private String texto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Key<Artigo> getArtigo() {
		return artigo;
	}
	public void setArtigo(Key<Artigo> artigo) {
		this.artigo = artigo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
