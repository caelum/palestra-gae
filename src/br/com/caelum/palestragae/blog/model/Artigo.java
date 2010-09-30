package br.com.caelum.palestragae.blog.model;

import javax.persistence.Id;

public class Artigo {
	
	@Id
	private Long id;

	private String emailAutor;
	private String titulo;
	private String texto;
	
	public Artigo() {
	}
	
	public Artigo(String titulo, String emailAutor, String texto) {
		this.titulo = titulo;
		this.emailAutor = emailAutor;
		this.texto = texto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailAutor() {
		return emailAutor;
	}
	public void setEmailAutor(String emailAutor) {
		this.emailAutor = emailAutor;
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
