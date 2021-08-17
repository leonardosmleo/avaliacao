package br.com.cast.avaliacao.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CategoriaDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idCategoria;
	private String dsCategoria;
}
