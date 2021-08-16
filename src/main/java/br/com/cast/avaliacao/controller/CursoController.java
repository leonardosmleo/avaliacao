package br.com.cast.avaliacao.controller;

import java.util.List;

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

import br.com.cast.avaliacao.dto.CursoDto;
import br.com.cast.avaliacao.service.CursoService;

@RestController
@RequestMapping(value="/curso")
public class CursoController {
	
	CursoService cursoService;
	
	@Autowired
	public CursoController(CursoService categoriaService) {
		this.cursoService = categoriaService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<CursoDto>> findAll(){
		return new ResponseEntity<>(cursoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CursoDto> find(@PathVariable Long id) {
		return new ResponseEntity<>(cursoService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> save(@RequestBody CursoDto cursoDto){
		if(cursoService.validaData(cursoDto.getDtInicio(), cursoDto.getDtTermino(), cursoDto.getIdCurso())) {
			cursoDto = cursoService.save(cursoDto);
			return new ResponseEntity<>(cursoDto, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(cursoService.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody CursoDto cursoDto){
		if(cursoService.validaData(cursoDto.getDtInicio(), cursoDto.getDtTermino(), cursoDto.getIdCurso())) {
			cursoDto = cursoService.update(cursoDto);
			return new ResponseEntity<>(cursoDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(cursoService.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception {
		cursoService.deleteById(id);
		return new ResponseEntity<>("Registro removido", HttpStatus.OK);
	}
}
