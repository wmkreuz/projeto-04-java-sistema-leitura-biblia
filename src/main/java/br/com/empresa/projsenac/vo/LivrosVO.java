package br.com.empresa.projsenac.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LIVROS")
public class LivrosVO implements Serializable{



	private static final long serialVersionUID = -7514356012435231666L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "livro", nullable = false)
	private BigInteger livro;
	
	@Basic
	@Column(name = "escritor")
	private String escritor;
	
	@Basic
	@Column(name = "sigla")
	private String sigla;
	
	@Basic
	@Column(name = "qtd_capitulos")
	private BigInteger qtde_capitulos;

	public BigInteger getLivro() {
		return livro;
	}

	public void setLivro(BigInteger livro) {
		this.livro = livro;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public BigInteger getQtde_capitulos() {
		return qtde_capitulos;
	}

	public void setQtde_capitulos(BigInteger qtde_capitulos) {
		this.qtde_capitulos = qtde_capitulos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(escritor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivrosVO other = (LivrosVO) obj;
		return Objects.equals(escritor, other.escritor);
	}
	
	@Override
	public String toString() {
		return escritor;
	}
	
}
