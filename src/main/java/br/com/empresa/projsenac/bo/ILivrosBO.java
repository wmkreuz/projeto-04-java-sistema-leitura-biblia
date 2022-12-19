package br.com.empresa.projsenac.bo;

import java.util.List;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.LivrosVO;

public interface ILivrosBO {

	public abstract List<LivrosVO> listarLivros() throws BOException;
	
}
