package br.com.empresa.projsenac.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.projsenac.exeption.BOException;
import br.com.empresa.projsenac.vo.BibliaVO;
import br.com.empresa.projsenac.vo.LivrosVO;

public class BibliaDAO implements IBibliaDAO{

	@Override
	public List<BibliaVO> listarBiblia(LivrosVO lvSelect, BigInteger capitulo,
			BigInteger versiculoDe, BigInteger versiculoAte, String textoPesquisa) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

		//Clausula from
		Root<BibliaVO> bibliaFrom = criteria.from(BibliaVO.class);
		Join<BibliaVO, LivrosVO> livrosFrom = bibliaFrom.join("livrosVO");
		
		Path<BigInteger> bibliaFrom_Capitulo = bibliaFrom.get("capitulo");
		Path<BigInteger> bibliaFrom_Versiculo = bibliaFrom.get("versiculo");
		Path<String> bibliaFrom_Texto = bibliaFrom.get("texto");
		Path<BigInteger> bibliaFrom_Sequencia = bibliaFrom.get("sequencia");
		
		//Clausula where
		Predicate bibliaWhere = cb.isNotNull(bibliaFrom);
		
		if(lvSelect != null) {
			
			bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("livro"), lvSelect.getLivro()));
		
		}
		
		if(capitulo != null && capitulo.toString().trim().length() > 0){
			
			bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("capitulo"), capitulo));

		}

		if((versiculoDe != null && versiculoDe.toString().trim().length() > 0) &&
				(versiculoAte == null || versiculoAte.toString().trim().length() == 0)){
			
			bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("versiculo"), versiculoDe));

		}
		
		if((versiculoDe != null && versiculoDe.toString().trim().length() > 0) &&
				(versiculoAte != null && versiculoAte.toString().trim().length() > 0)) {
			bibliaWhere = cb.and(bibliaWhere, cb.between(bibliaFrom.get("versiculo"), versiculoDe,versiculoAte));
		}
		
		if(textoPesquisa != null && textoPesquisa.trim().length() > 0){

			bibliaWhere = cb.and(bibliaWhere, cb.like(cb.lower(bibliaFrom.get("texto")),
					"%" + textoPesquisa.toLowerCase() + "%"));

		}
		
		//Clausula Orderby
		Order bibliaOrderByLivro = cb.asc(bibliaFrom.get("livro"));
		Order bibliaOrderByCapitulo = cb.asc(bibliaFrom.get("capitulo"));
		Order bibliaOrderByVersiculo = cb.asc(bibliaFrom.get("versiculo"));
		
		
		//Atribuindo as clausulas a consulta
		criteria.multiselect(bibliaFrom_Capitulo,bibliaFrom_Versiculo,bibliaFrom_Texto,
				bibliaFrom_Sequencia,livrosFrom);
		if(bibliaWhere != null) {
			criteria.where(bibliaWhere);
		}
		criteria.orderBy(bibliaOrderByLivro,bibliaOrderByCapitulo,bibliaOrderByVersiculo);
		
		TypedQuery<Tuple> query = em.createQuery(criteria);
		
		query.setMaxResults(300);

		List<Tuple> tuples = query.getResultList();
		
		List<BibliaVO> ret = new ArrayList<BibliaVO>();
		
		if(tuples != null) {
			for(Tuple tuple : tuples) {
				BibliaVO bibliaVO = new BibliaVO();
				bibliaVO.setCapitulo(tuple.get(bibliaFrom_Capitulo));;
				bibliaVO.setVersiculo(tuple.get(bibliaFrom_Versiculo));;
				bibliaVO.setTexto(tuple.get(bibliaFrom_Texto));;
				bibliaVO.setSequencia(tuple.get(bibliaFrom_Sequencia));;
				bibliaVO.setLivrosVO(tuple.get(livrosFrom));
				ret.add(bibliaVO);
			}
		}

		em.close();
		
		return ret;
		
	}

	public List<BibliaVO> listarBiblia(BibliaVO bbSelect) {
		BigInteger capitulo = bbSelect.getCapitulo();
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);
		

		//Clausula from
		Root<BibliaVO> bibliaFrom = criteria.from(BibliaVO.class);
		Join<BibliaVO, LivrosVO> livrosFrom = bibliaFrom.join("livrosVO");
		
		Path<BigInteger> bibliaFrom_Capitulo = bibliaFrom.get("capitulo");
		Path<BigInteger> bibliaFrom_Versiculo = bibliaFrom.get("versiculo");
		Path<String> bibliaFrom_Texto = bibliaFrom.get("texto");
		Path<BigInteger> bibliaFrom_Sequencia = bibliaFrom.get("sequencia");
		
		//Clausula where
		Predicate bibliaWhere = cb.isNotNull(bibliaFrom);
		
		if(bbSelect.getLivrosVO() != null) {
			
			bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("livro"), bbSelect.getLivrosVO().getLivro()));
		
		}
		
		if(capitulo != null && capitulo.toString().trim().length() > 0){
			
			bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("capitulo"), capitulo));

		}
		
		//Clausula Orderby
		Order bibliaOrderByLivro = cb.asc(bibliaFrom.get("livro"));
		Order bibliaOrderByCapitulo = cb.asc(bibliaFrom.get("capitulo"));
		Order bibliaOrderByVersiculo = cb.asc(bibliaFrom.get("versiculo"));
		
		
		//Atribuindo as clausulas a consulta
		criteria.multiselect(bibliaFrom_Capitulo,bibliaFrom_Versiculo,bibliaFrom_Texto,
				bibliaFrom_Sequencia,livrosFrom);
		if(bibliaWhere != null) {
			criteria.where(bibliaWhere);
		}
		criteria.orderBy(bibliaOrderByLivro,bibliaOrderByCapitulo,bibliaOrderByVersiculo);
		
		TypedQuery<Tuple> query = em.createQuery(criteria);
		
		query.setMaxResults(300);

		List<Tuple> tuples = query.getResultList();
		
		List<BibliaVO> ret = new ArrayList<BibliaVO>();
		
		if(tuples != null) {
			for(Tuple tuple : tuples) {
				BibliaVO bibliaVO = new BibliaVO();
				bibliaVO.setCapitulo(tuple.get(bibliaFrom_Capitulo));;
				bibliaVO.setVersiculo(tuple.get(bibliaFrom_Versiculo));;
				bibliaVO.setTexto(tuple.get(bibliaFrom_Texto));;
				bibliaVO.setSequencia(tuple.get(bibliaFrom_Sequencia));;
				bibliaVO.setLivrosVO(tuple.get(livrosFrom));
				ret.add(bibliaVO);
			}
		}

		em.close();
		
		return ret;
	}

}
