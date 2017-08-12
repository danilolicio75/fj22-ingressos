package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.caelum.ingresso.model.Carrinho;

@Controller
public class CarrinhoController {
	
	@Autowired
	private Carrinho carrinho;
	
	

}
