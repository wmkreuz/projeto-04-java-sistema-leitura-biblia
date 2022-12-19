package br.com.empresa.projsenac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.LivrosVO;

public class LivrosDAO implements ILivrosDAO{

	@Override
	public List<LivrosVO> listarLivros() throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LivrosVO> criteria = cb.createQuery(LivrosVO.class);
		
		//Clausula from
		Root<LivrosVO> livrosFrom = criteria.from(LivrosVO.class);
		
		//Atribuindo as clausulas a consulta
		criteria.select(livrosFrom);
		
		//Clausula Orderby
		Order LivrosOrderBy = cb.asc(livrosFrom.get("livro"));

		criteria.orderBy(LivrosOrderBy);
					
		TypedQuery<LivrosVO> query = em.createQuery(criteria);
				
		//query.setMaxResults(30);
				
		List<LivrosVO> listarLivros = query.getResultList();

		em.close();
				
		return listarLivros;

	}
	
}
