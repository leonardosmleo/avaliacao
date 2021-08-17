package br.com.cast.avaliacao.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.avaliacao.dto.CategoriaDto;
import br.com.cast.avaliacao.model.CategoriaEntity;
import br.com.cast.avaliacao.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRespository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CategoriaDto> findAll(){
		List<CategoriaEntity> listaEntity = categoriaRespository.findAll();
		List<CategoriaDto> listaDTO = new ArrayList<CategoriaDto>();
		if (listaEntity.size() > 0) {
			listaEntity.stream().forEach(x -> {
				listaDTO.add(modelMapper.map(x, CategoriaDto.class));
			});
		}
		return listaDTO;
	}
	
	public CategoriaDto findById(Long id) {
		return modelMapper.map(categoriaRespository.getById(id), CategoriaDto.class);
	}

	public CategoriaDto save(CategoriaDto categoriaDto) {
		CategoriaEntity categoriaEntity = modelMapper.map(categoriaDto, CategoriaEntity.class);
		return modelMapper.map(categoriaRespository.save(categoriaEntity), CategoriaDto.class);
	}
	
	public CategoriaDto update(CategoriaDto categoriaDto) {
		CategoriaEntity categoriaEntity = modelMapper.map(categoriaDto, CategoriaEntity.class);
		return modelMapper.map(categoriaRespository.saveAndFlush(categoriaEntity), CategoriaDto.class);
	}
	
	@SuppressWarnings("finally")
	public void deleteById(Long id) throws Exception {
		if (Boolean.TRUE.equals(categoriaRespository.existsById(id))) {
			try {
				categoriaRespository.deleteById(id);
			} catch (Exception e) {
				throw new Exception("Não é possível remover");
			}
		}
	}
}