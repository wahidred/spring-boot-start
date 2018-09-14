package business.drh.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import business.drh.model.Employe;

@Repository
@Transactional(rollbackFor = Exception.class)
public class EmployeDaoImpl implements EmployeDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;

	@Override
	public Employe findById(Long _id) {
		logger.info("IN with id = " + _id);

		Employe employe = em.find(Employe.class, _id);

		logger.info("OUT with Employe = " + employe);
		return employe;
	}

	@Override
	public Employe save(Employe _employe) {
		em.persist(_employe);
		// em.flush();
		// // throw new RuntimeException("pipoo");
		// try {
		// if (_employe != null)
		// new Exception("pipoo");
		// } catch (Exception e) {
		// throw new RuntimeException("hh", e);
		// }
		return _employe;
	}
}
