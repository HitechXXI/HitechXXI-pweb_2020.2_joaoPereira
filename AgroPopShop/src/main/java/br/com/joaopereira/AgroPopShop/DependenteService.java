package br.com.joaopereira.AgroPopShop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependenteService {
	
	@Autowired
	private DependenteRepository repo; 
	
	public List<Dependente> listAll() {
		return repo.findAll();
	}

	public void save(Dependente dependente) {
		repo.save(dependente);
	}
	
	public Dependente get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
