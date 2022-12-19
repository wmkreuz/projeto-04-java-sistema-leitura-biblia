package br.com.empresa.projsenac.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BIBLIA")
public class BibliaVO implements Serializable{

	private static final long serialVersionUID = -1384183596866508970L;
	
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "sequencia", nullable = false)
	private BigInteger sequencia;
	
	@Basic
	@Column(name = "livro")
	private BigInteger livro;
	
	@NotNull
	@JoinColumn(name = "livro", referencedColumnName = "livro", 
		nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private LivrosVO livrosVO;
	
	@Basic
	@Column(name = "capitulo")
	private BigInteger capitulo;
	
	@Basic
	@Column(name = "versiculo")
	private BigInteger versiculo;
	
	@Basic
	@Column(name = "texto")
	private String texto;
	
	public BigInteger getLivro() {
		return livro;
	}
	public void setLivro(BigInteger livro) {
		this.livro = livro;
	}
	
	public void setLivrosVO(LivrosVO livrosVO) {
		this.livrosVO = livrosVO;
	}
	
	public LivrosVO getLivrosVO() {
		return livrosVO;
	}
	
	public BigInteger getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(BigInteger capitulo) {
		this.capitulo = capitulo;
	}
	public BigInteger getVersiculo() {
		return versiculo;
	}
	public void setVersiculo(BigInteger versiculo) {
		this.versiculo = versiculo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public BigInteger getSequencia() {
		return sequencia;
	}
	public void setSequencia(BigInteger sequencia) {
		this.sequencia = sequencia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(sequencia);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliaVO other = (BibliaVO) obj;
		return Objects.equals(sequencia, other.sequencia);
	}
	
}
