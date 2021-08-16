package br.com.cast.avaliacao.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CursoDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idCurso;
	private String dsAssunto;
	private Boolean flAtivo;
	private LocalDate dtInicio;
	private LocalDate dtTermino;
	private Integer qtdeAlunos;
    private CategoriaDto categoria;
}
