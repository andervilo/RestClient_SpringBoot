package br.com.restcient.restclient.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restcient.restclient.model.Contato;
import br.com.restcient.restclient.repository.ContatoRepositoryImpl;

//Aceitar requisições de qualquer host
@CrossOrigin(origins = "*")

//Iforma ao Spring Boot que este é um controller de resources
@RestController

//Setar o prefixo para os endpoints do resource
@RequestMapping(value="/api/v1")
public class ContatoResource {
	
	private ContatoRepositoryImpl cri;
	
	public ContatoResource() {
		this.cri = new ContatoRepositoryImpl();
	}
	
	//Retorna uma lista de Contatos
	@GetMapping("/contatos")
	public List<Contato> listaContatos(){
		return this.cri.getAll();
	}
	
	//Retorna um Contato unico
	@GetMapping("/contatos/{id}")
	public Contato listaContatoUnico(@PathVariable(value="id") Integer id){
		return this.cri.getById(id);
	}
	
	//Cria um Contato
	@PostMapping("/contatos")
	public Contato salvaContato(@RequestBody @Valid Contato contato) {
		return this.cri.save(contato);
	}
	
	//Deleta um Contato
	@DeleteMapping("/contatos/{id}")
	public void deletaContato(@PathVariable(value="id") Integer id) {
		this.cri.delete(id);
	}
	
	//Atualiza um Contato
	@PutMapping("/contatos/{id}")
	public void atualizaContato(@PathVariable(value="id") Integer id ,@RequestBody @Valid Contato contato) {
		this.cri.update(id, contato);
	}

}
