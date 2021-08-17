package br.com.cast.avaliacao.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.avaliacao.dto.CursoDto;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.model.CursoEntity;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.repository.CursoRepository;

@Service
@Transactional
public class CursoService {
	@Autowired
	CursoRepository cursoRespository;
	
	@Autowired
	CategoriaRepository categoriaRespository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Integer contadorIntervalo = 0;
	
	private String message = "";
	
	public String getMessage() {
		return this.message;
	}
	
	public List<CursoDto> findAll(){
		List<CursoEntity> listaEntity = cursoRespository.findAll();
		List<CursoDto> listaDTO = new ArrayList<CursoDto>();
		if (listaEntity.size() > 0) {
			listaEntity.stream().forEach(x -> {
				listaDTO.add(modelMapper.map(x, CursoDto.class));
			});
		}
		return listaDTO;
	}
	
	public CursoDto findById(Long id) {
		return modelMapper.map(cursoRespository.getById(id), CursoDto.class);
	}

	public CursoDto save(CursoDto cursoDto) {
		CursoEntity cursoEntity = modelMapper.map(cursoDto, CursoEntity.class);
		return modelMapper.map(cursoRespository.save(cursoEntity), CursoDto.class);
	}
	
	public CursoDto update(CursoDto cursoDto) {
		CursoEntity cursoEntity = modelMapper.map(cursoDto, CursoEntity.class);
		return modelMapper.map(cursoRespository.saveAndFlush(cursoEntity), CursoDto.class);
	}
	
	public Boolean validaData(LocalDate data1, LocalDate data2, Long idCurso) {
		LocalDate dataAtual = LocalDate.now();
		Boolean retorno = data1.compareTo(dataAtual)<0;
		this.contadorIntervalo = 0;
		
		if(retorno) {
			this.message="Não será permitida a inclusão de cursos com a data de início menor que a data atual";
			return false;
		}
		
		List<CursoEntity> listaEntity = cursoRespository.findAll();
		if (listaEntity.size() > 0) {
			listaEntity.stream().forEach(x -> {
				if(x.getIdCurso() != idCurso && data1.compareTo(x.getDtInicio()) > 0 && data1.compareTo(x.getDtTermino()) < 0) {
					this.contadorIntervalo++;
				}else if(x.getIdCurso() != idCurso && data2.compareTo(x.getDtInicio()) > 0 && data2.compareTo(x.getDtTermino()) < 0) {
					this.contadorIntervalo++;
				}else if(x.getIdCurso() != idCurso && data1.compareTo(x.getDtInicio()) < 0 && data2.compareTo(x.getDtTermino()) > 0) {
					this.contadorIntervalo++;
				}else if(x.getIdCurso() != idCurso && data1.equals(x.getDtInicio())) {
					this.contadorIntervalo++;
				}
			});
		}
		if(this.contadorIntervalo > 0) {
			this.message="Existe(m) curso(s) planejados(s) dentro do período informado.";
			return false;
		}
		return true;
	}
	
	public void deleteById(Long id) throws Exception {
		if (Boolean.TRUE.equals(cursoRespository.existsById(id))) {

			final Boolean hasForeignKey = this.verifyForeignKey(Long.valueOf(id.toString()));

			if (Boolean.TRUE.equals(hasForeignKey)) {
				throw new Exception("Não é possível remover pois esse objeto contém relacionamentos");
			}

			try {
				cursoRespository.deleteById(id);
			} catch (Exception e) {
				throw new Exception("Não é possível remover");
			}
		}
	}

	private Boolean verifyForeignKey(Long id) {
		Optional<CategoriaEntity> categoria = this.categoriaRespository.findById(id);
		if(categoria != null) {
			return true;
		}
		return false;
	}
}
