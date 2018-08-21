package br.com.restcient.restclient.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.restcient.restclient.model.Contato;
import br.com.restcient.restclient.repository.ContatoRepositoryImpl;

@Controller
@RequestMapping("contatos")
public class ContatoController {
	private ContatoRepositoryImpl cri;

	public ContatoController() {
		this.cri = new ContatoRepositoryImpl();
	}
	
	@GetMapping
	public ModelAndView getAll(){
		ModelAndView mv = new ModelAndView("/Contatos/Listar");
		mv.addObject("contatos", cri.getAll());		
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("/Contatos/Novo");
		mv.addObject("contato", new Contato());		
		return mv;
	}
	
	@PostMapping
	public String save(Contato contato){
		cri.save(contato);		
		return "redirect:/contatos";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		cri.delete(id);		
		return "redirect:/contatos";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id){
		ModelAndView mv = new ModelAndView("/Contatos/Editar");
		mv.addObject("contato", cri.getById(id));		
		return mv;
	}
	
	@PostMapping("/update")
	public String update(@RequestParam("id") Integer id, Contato contato){
			cri.update(id, contato);
		return "redirect:/contatos";
	}

}
