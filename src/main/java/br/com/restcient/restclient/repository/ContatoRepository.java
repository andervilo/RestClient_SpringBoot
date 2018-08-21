package br.com.restcient.restclient.repository;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.restcient.restclient.model.Contato;

public interface ContatoRepository {
	List<Contato> getAll();

	Contato getById(Integer id);

	Contato save(Contato contato);

	void update(Integer id, Contato contato);

	void delete(Integer id);

}
