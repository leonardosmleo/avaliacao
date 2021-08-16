package br.com.cast.avaliacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.dto.CategoriaDto;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.service.CategoriaService;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaController {
	
	CategoriaService categoriaService;
	
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoriaDto>> findAll(){
		return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDto> find(@PathVariable Long id) {
		return new ResponseEntity<>(categoriaService.findById(id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
		categoriaService.deleteById(id);
		return new ResponseEntity<>("Registro removido", HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto categoriaDto){
		categoriaDto = categoriaService.save(categoriaDto);
		return new ResponseEntity<>(categoriaDto, HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<CategoriaDto> update(@RequestBody CategoriaDto categoriaDto){
		categoriaDto = categoriaService.update(categoriaDto);
		return new ResponseEntity<>(categoriaDto, HttpStatus.OK);
	}
}
