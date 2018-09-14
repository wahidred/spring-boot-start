package business.drh.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import business.drh.model.Employe;

@Repository
@Transactional
public class LoadDaoImpl implements LoadDao {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void load() {
		char[] tab = { 'a', 'b', 'c', 'd', 'e', 'i', 'j', 'k', 'l', 'm' };
		for (int i = 0; i < 10; i++) {
			Employe e = new Employe(tab[i] + "toto_" + i, "prenom_" + tab[9 - i]);
			em.persist(e);
			System.out.println(e);
		}
		em.flush();
	}
}
