package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cast.avaliacao.model.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, Long>{

}
