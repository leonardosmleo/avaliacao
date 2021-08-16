package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cast.avaliacao.model.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{

}
