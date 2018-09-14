package business.drh.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Salaire {

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private Long id;

	private int montant;

	@Version
	private int version;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;

	public Salaire() {
		super();
	}

	public Salaire(int montant) {
		super();
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "Salaire [id=" + id + ", montant=" + montant + "]";
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
