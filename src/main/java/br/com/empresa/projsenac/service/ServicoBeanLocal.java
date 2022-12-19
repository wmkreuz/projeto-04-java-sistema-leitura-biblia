package br.com.empresa.projsenac.service;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.projsenac.bo.BibliaBO;
import br.com.empresa.projsenac.bo.IBibliaBO;
import br.com.empresa.projsenac.bo.ILivrosBO;
import br.com.empresa.projsenac.bo.LivrosBO;
import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

public class ServicoBeanLocal implements IServicoBeanLocal{

	@Override
	public List<LivrosVO> listarLivros()
			throws BOException {
		
		ILivrosBO livrosBO = new LivrosBO();
		
		return livrosBO.listarLivros();
		
	}

	@Override
	public List<BibliaVO> listarBiblia(LivrosVO lvSelect, BigInteger capitulo,
			BigInteger versiculoDe, BigInteger versiculoAte, String textoPesquisa) throws BOException {
		
		IBibliaBO bibliaBO = new BibliaBO();
		
		return bibliaBO.listarBiblia(lvSelect, capitulo, versiculoDe, versiculoAte, textoPesquisa);
		
	}

	@Override
	public List<BibliaVO> listarBiblia(BibliaVO bbSelect) throws BOException {

		IBibliaBO bibliaBO = new BibliaBO();
		
		return bibliaBO.listarBiblia(bbSelect);
		
	}

}
