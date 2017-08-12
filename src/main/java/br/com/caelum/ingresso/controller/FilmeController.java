package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.rest.ImdbClient;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {
	
	@Autowired
	private FilmeDao filmedao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private ImdbClient client;
	
	private Logger logger = Logger.getLogger(ImdbClient.class);
	
	public Optional<DetalhesDoFilme>  request(Filme filme) {
		
		RestTemplate client = new RestTemplate();
		
		String titulo = filme.getNome().replace(" ", "+");
		
		String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);
		
		try {
			DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
					return Optional.of(detalhesDoFilme);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return Optional.empty();
		}
		
		
	}

    @Autowired
    private FilmeDao filmeDao;

    @GetMapping("/filme/em-cartaz")
    public ModelAndView emCartaz() {
    	ModelAndView modelAndView = new ModelAndView("/filme/em-cartaz");

    	modelAndView.addObject("filmes",filmeDao.findAll());
    	
    	return modelAndView;
    }
    
    
    @GetMapping("/filme/{id}/detalhe")
    public ModelAndView detalhes(@PathVariable("id") Integer id) {
    	ModelAndView modelAndView = new ModelAndView("/filme/detalhe");
    	
    	Filme filme = filmeDao.findOne(id);
    	
    	List<Sessao> sessoes = sessaoDao.buscaSessoesDoFilme(filme);
    	
    	Optional<DetalhesDoFilme> detalhesDoFilme = client.request(filme, DetalhesDoFilme.class);
    	
    	
    	modelAndView.addObject("sessoes",sessoes);
    	modelAndView.addObject("detalhes",detalhesDoFilme.orElse(new DetalhesDoFilme()));
    	
    	return modelAndView;
    }

    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

}
