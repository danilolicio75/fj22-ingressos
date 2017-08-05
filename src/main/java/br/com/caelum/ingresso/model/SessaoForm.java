package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;

public class SessaoForm {
	
	private Integer id;
	
	@NotNull
	private Integer salaId;
	
	@NotNull
	private Integer filmeId;  
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	@NotNull          
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;
	                 
    public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
    	Sala sala = salaDao.findOne(salaId);
    	Filme filme = filmeDao.findOne(filmeId);
    	
    	Sessao sessao = new Sessao(horario, filme, sala);
    	sessao.setId(id);
    	return sessao;
    }

	public Integer getSalaId() {
		return salaId;
	}
	
	public void setSalaId(Integer salaid) {
		this.salaId = salaid;
	}
} 
