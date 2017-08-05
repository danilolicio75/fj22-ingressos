package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Sessao {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Filme filme;
	
	@ManyToOne
	private Sala sala;
	
	private LocalTime horario;	
	
	public Filme getFilme() {
		return filme;
	}

	public Sessao() {
	
	}
	
	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario = horario;
		this.setFilme(filme);
		this.sala = sala;
	}

	public Integer getId() {
		return id;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public void setId(Integer id) {
		this.id = id;
		
	}

}
