package business.drh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import business.drh.model.Salaire;

@Repository
@Transactional
public class SalaireDaoImpl implements SalaireDao {

	@PersistenceContext
	private EntityManager em;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Salaire save(Salaire _salaire) {
		logger.info("IN with _salaire = " + _salaire);
		logger.info("for Employe = " + _salaire.getEmploye());

		em.persist(_salaire);

		logger.info("OUT with _salaire = " + _salaire);
		logger.info("for Employe = " + _salaire.getEmploye());
		return _salaire;
	}

	@Override
	public List<Salaire> findAll() {
		TypedQuery<Salaire> query = em.createQuery("select s from Salaire s inner join fetch s.employe e",
				Salaire.class);
		List<Salaire> list = query.getResultList();
		return list;

	}

}
