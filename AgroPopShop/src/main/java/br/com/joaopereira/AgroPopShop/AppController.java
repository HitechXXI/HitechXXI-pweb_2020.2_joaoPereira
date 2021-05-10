package br.com.joaopereira.AgroPopShop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

	@Autowired
	private ClienteService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
	
		return "index";
	}
	
	@RequestMapping("/listCliente")
	public String viewCliente(Model model) {
		List<Cliente> listClientes = service.listAll();
		model.addAttribute("listClientes", listClientes);
		
		return "list_cliente";
	}
	
	@RequestMapping("/newCliente")
	public String showNewClienteForm(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		
		return "new_cliente";
	}
	
	@RequestMapping(value = "/saveCliente", method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		service.save(cliente);
		
		return "redirect:/listCliente";
		
	}

	@RequestMapping("/editCliente/{id}")
	public ModelAndView showEditClienteForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_cliente");
		
		Cliente cliente = service.get(id);
		mav.addObject("cliente", cliente);
		
		return mav;
	}
	
	@RequestMapping("/deleteCliente/{id}")
	public String deleteCliente(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/listCliente";
	}
	
	/* --------- CONTROLLER PRODUCT --------- */
	
	@Autowired
	private ProdutoService serviceProduto;
	
	@RequestMapping("/listProduto")
	public String viewProduto(Model model) {
		List<Produto> listProdutos = serviceProduto.listAll();
		model.addAttribute("listProdutos", listProdutos);
		
		return "list_produto";
	}
	
	@RequestMapping("/newProduto")
	public String showNewProdutoForm(Model model) {
		Produto produto = new Produto();
		model.addAttribute("produto", produto);
		
		return "new_produto";
	}
	
	@RequestMapping(value = "/saveProduto", method = RequestMethod.POST)
	public String saveProduto(@ModelAttribute("produto") Produto produto) {
		serviceProduto.save(produto);
		
		return "redirect:/listProduto";
		
	}

	@RequestMapping("/editProduto/{id}")
	public ModelAndView showEditProdutoForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_produto");
		
		Produto produto = serviceProduto.get(id);
		mav.addObject("produto", produto);
		
		return mav;
	}

	@RequestMapping("/deleteProduto/{id}")
	public String deleteProduto(@PathVariable(name = "id") Long id) {
		serviceProduto.delete(id);
		
		return "redirect:/listProduto";
	}

}
