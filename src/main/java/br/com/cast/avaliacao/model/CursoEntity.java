package br.com.cast.avaliacao.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="curso")
@Where(clause = "fl_ativo = true")
public class CursoEntity implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "id_curso", length = 100)
	private long idCurso;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "ds_assunto", length = 100)
	private String dsAssunto;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "fl_ativo")
	private Boolean flAtivo=true;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "dt_inicio")
	private LocalDate dtInicio;
	
	@NotNull
	@Basic(optional = false)
    @Column(name = "dt_termino")
	private LocalDate dtTermino;
	
	@NotNull
	@Basic(optional = true)
    @Column(name = "qtde_alunos")
	private Integer qtdeAlunos;
	
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaEntity categoria;
	
    public Long getId() {
        return idCurso;
    }
}
