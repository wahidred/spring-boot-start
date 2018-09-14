package business.drh.dao;

import business.drh.model.Employe;

public interface EmployeDao {

	Employe findById(Long _id);

	Employe save(Employe _employe);

}
