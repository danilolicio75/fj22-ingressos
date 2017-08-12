package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Sessao {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Filme filme;
	
	@ManyToOne
	private Sala sala;
	
	@OneToMany(mappedBy="sessao",fetch=FetchType.EAGER)
	private Set<Ingresso> ingressos;
	
	private LocalTime horario;	
	
	private BigDecimal preco;
	
	

	public Sessao() {
	
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario = horario;
		this.setFilme(filme);
		this.sala = sala;
		System.out.println(filme);
		System.out.println(sala);
		this.preco = sala.getPreco().add(filme.getPreco());
	}

	public Integer getId() {
		return id;
	}
	
	public Filme getFilme() {
		return filme;
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
	
	public LocalTime getHorarioTermino() {
		return this.horario.plus(filme.getDuracao().toMinutes(), ChronoUnit.MINUTES);
	}
	
	public Map<String,List<Lugar>> getMapaDeLugares() {
		return sala.getMapaDeLugares();	
	}
	
	public  boolean isDisponivel(Lugar lugar) {
				
		return ingressos.stream().map(Ingresso::getLugar).noneMatch(l -> l.equals(lugar));
	}

}
