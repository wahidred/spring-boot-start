package business.drh.dao;

import java.util.List;

import business.drh.model.Salaire;

public interface SalaireDao {

	Salaire save(Salaire _salaire);

	List<Salaire> findAll();

}
