package br.com.cast.avaliacao.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.sun.istack.NotNull;

import lombok.Data;
/**
 * 
 * @author Leonardo de Souza Martins
 * Usando Lombok
 *
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="categoria")
@Where(clause = "fl_ativo = true")
public class CategoriaEntity implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id_categoria")
	private long idCategoria;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "ds_categoria", length = 100)
	private String dsCategoria;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "fl_ativo")
	private Boolean flAtivo=true;
	
    public Long getId() {
        return idCategoria;
    }
}
