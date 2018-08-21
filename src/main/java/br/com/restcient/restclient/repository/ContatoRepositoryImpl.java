package br.com.restcient.restclient.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.restcient.restclient.model.Contato;


public class ContatoRepositoryImpl implements ContatoRepository{
	
	private RestTemplate restTemplate;
	private String url= "http://localhost:3000/contatos";
	
	

	public ContatoRepositoryImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<Contato> getAll() {	
		return Arrays.stream(this.restTemplate .getForObject(url, Contato[].class)).collect(Collectors.toList());			
	}

	@Override
	public Contato getById(Integer id) {		
		return this.restTemplate.getForObject(url+"/"+id, Contato.class);
	}

	@Override
	public Contato save(Contato contato){
		return restTemplate.postForObject(url, contato, Contato.class);
	}

	@Override
	public void update(Integer id, Contato contato) {
		restTemplate.put(url+"/"+id, contato);
	}

	@Override
	public void delete(Integer id) {
		restTemplate.delete(url+"/"+id);
	}

}
