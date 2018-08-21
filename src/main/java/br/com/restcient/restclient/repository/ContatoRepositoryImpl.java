package br.com.restcient.restclient.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
		try {
			ResponseEntity<Contato[]> response = this.restTemplate.getForEntity(url, Contato[].class);
			if(response.getStatusCodeValue() == 200){
				return Arrays.stream(response.getBody()).collect(Collectors.toList());
			}
		}catch (HttpClientErrorException ex) {
			
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
		    }
		}
		return null;
	}

	@Override
	public Contato getById(Integer id) {	
		ResponseEntity<Contato> response = null ;
		
		try {
			response = this.restTemplate.getForEntity(url+"/"+id, Contato.class);
			if(response.getStatusCode().is2xxSuccessful()){
				return response.getBody();
			}
		} catch (HttpClientErrorException ex) {
			
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
		    }
		}
		return null;
		
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
