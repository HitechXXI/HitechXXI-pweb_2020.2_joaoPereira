package br.com.joaopereira.AgroPopShop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> listAll() {
		return repo.findAll();
	}
	
	public void save(Produto produto) {
		repo.save(produto);
	}
	
	public Produto get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
}
