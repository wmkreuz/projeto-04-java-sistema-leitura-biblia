package br.com.empresa.projsenac.service;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

public interface IServicoBeanLocal {
	
	public abstract List<LivrosVO> listarLivros() throws BOException;

	
	public abstract List<BibliaVO> listarBiblia(LivrosVO lvSelect, BigInteger capitulo,
			BigInteger versiculoDe, BigInteger versiculoAte, String textoPesquisa) throws BOException;


	public abstract List<BibliaVO> listarBiblia(BibliaVO bbSelect) throws BOException;
	
}
