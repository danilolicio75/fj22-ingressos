package br.com.caelum.ingresso.model.form;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class CarrinhoForm {
	
	private Set<Ingresso> ingressos = new HashSet<Ingresso>();

	public Set<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	public Set<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		return this.ingressos.stream().map(ingresso -> {
			Sessao sessao = sessaoDao.findOne(ingresso.getSessao().getId());
			Lugar lugar = lugarDao.findOne(ingresso.getLugar().getId());
			TipoDeIngresso tipoDeIngresso = ingresso.getTipoDeIngresso();
		    }).collect(Collectors.toList());
			
			
	}

}
