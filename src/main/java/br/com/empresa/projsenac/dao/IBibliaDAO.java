package br.com.empresa.projsenac.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

public interface IBibliaDAO {

	public abstract List<BibliaVO> listarBiblia(LivrosVO lvSelect, BigInteger capitulo,
			BigInteger versiculoDe, BigInteger versiculoAte, String textoPesquisa) throws BOException;

	public abstract List<BibliaVO> listarBiblia(BibliaVO bbSelect);
}
