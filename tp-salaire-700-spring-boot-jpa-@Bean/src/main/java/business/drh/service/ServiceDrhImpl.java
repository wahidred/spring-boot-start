package business.drh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.drh.dao.EmployeDao;
import business.drh.dao.SalaireDao;
import business.drh.model.Employe;
import business.drh.model.Salaire;

// @Component
@Service
@Transactional
public class ServiceDrhImpl implements ServiceDrh {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private @Autowired EmployeDao employeDao;
	private @Autowired SalaireDao salaireDao;

	public void payerSalaire(Long _idEmploye, int montant) {
		logger.info("IN idEmploye = " + _idEmploye + " montant = " + montant);
		Employe employe = employeDao.findById(_idEmploye);
		if (employe == null) {
			throw new RuntimeException("ça va pas la tete, l'id de l'employé n'existe pas en bd");
		}
		Salaire salaire = new Salaire(montant);
		salaire.setEmploye(employe);
		salaireDao.save(salaire);
		logger.info("OUT");
	}

}
