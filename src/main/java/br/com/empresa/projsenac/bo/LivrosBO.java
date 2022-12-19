package br.com.empresa.projsenac.bo;

import java.util.List;

import br.com.empresa.projsenac.dao.LivrosDAO;
import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.LivrosVO;

public class LivrosBO implements ILivrosBO{

private LivrosDAO livrosDAO;
	
	public LivrosBO() {
		livrosDAO = new LivrosDAO();
	}

	@Override
	public List<LivrosVO> listarLivros() throws BOException {
		
		return livrosDAO.listarLivros();
		
	}
	
}
