package br.com.empresa.projsenac.bo;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.projsenac.dao.BibliaDAO;
import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

public class BibliaBO implements IBibliaBO{
	
	private BibliaDAO bibliaDAO;

	public BibliaBO() {
		bibliaDAO = new BibliaDAO();
	}

	@Override
	public List<BibliaVO> listarBiblia(LivrosVO lvSelect, BigInteger capitulo,
			BigInteger versiculoDe, BigInteger versiculoAte, String textoPesquisa) throws BOException {


		return bibliaDAO.listarBiblia(lvSelect, capitulo, versiculoDe, versiculoAte, textoPesquisa);
	}

	@Override
	public List<BibliaVO> listarBiblia(BibliaVO bbSelect) throws BOException {
		if(bbSelect == null || bbSelect.getSequencia() == null) {
			throw new BOException("Ocorreu um erro ao pesquisar o capítulo selecionado.");
		}
		return bibliaDAO.listarBiblia(bbSelect);
	}
	
}
