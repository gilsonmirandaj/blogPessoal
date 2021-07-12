package org.generation.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.BlogPessoal.model.LoginUsuario;
import org.generation.BlogPessoal.model.Usuario;
import org.generation.BlogPessoal.repository.UsuarioRepository;
import org.generation.BlogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/logar")
	public ResponseEntity<LoginUsuario> Autentication(@RequestBody Optional<LoginUsuario> usuario){
		return usuarioService.Logar(usuario)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.usuarioService.CadastrarUsuario(usuario));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());				
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deleteById(@PathVariable long id) {
		repository.deleteById(id);
	}


	@PutMapping("/alterar")
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
		Optional<Usuario> user = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
