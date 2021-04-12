package br.com.joaopereira.cadpessoas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/adicionarPessoa")
	public ModelAndView formAdicionarPessoa() {
		ModelAndView modelAndView = new ModelAndView("adicionarPessoa");
		modelAndView.addObject(new Pessoa());
		return modelAndView;
	}
	
	// localhost:8080/adicionarPessoa
	@PostMapping("/adicionarPessoa")
	public String adicionarPessoa(Pessoa aSalvar) {
		this.pessoaRepo.save(aSalvar);
		return "redirect:/listarPessoas";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView formEditarPessoa(@PathVariable("id") long id) {
		Pessoa aEditar = pessoaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
		ModelAndView modelAndView = new ModelAndView("editarPessoa");
		modelAndView.addObject(aEditar);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public ModelAndView editarPessoa(@PathVariable("id") long id, Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return new ModelAndView("redirect:/listarPessoas");
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView removerPessoa(@PathVariable("id") long id) {
		Pessoa aRemover = pessoaRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID Inválido " + id));
		pessoaRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarPessoas");
	}
}
