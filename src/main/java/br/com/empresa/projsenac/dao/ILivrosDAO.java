package br.com.empresa.projsenac.dao;

import java.util.List;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.LivrosVO;

public interface ILivrosDAO {

	public abstract List<LivrosVO> listarLivros() throws BOException;
	
}
