package br.com.joaopereira.cadpessoas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.joaopereira.cadpessoas.model.Pessoa;
import br.com.joaopereira.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepo;
	
	PessoaController(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}
	
	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/listarPessoas")
	public ModelAndView listarPessoas() {
		List<Pessoa> lista = pessoaRepo.findAll();
		ModelAndView modelAndView = new ModelAndView("listarPessoas");
		modelAndView.addObject("pessoas", lista);
		return modelAndView;
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView removerPessoa(@PathVariable("id") long id) {
		Pessoa aRemover = pessoaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));	
		pessoaRepo.delete(aRemover);
		return new ModelAndView("redirect:listarPessoas");
	}
}
