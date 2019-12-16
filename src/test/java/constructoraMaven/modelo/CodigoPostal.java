package constructoraMaven.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "codigopostal")
public class CodigoPostal {

	@Id
	@Column(name = "cp_cp")
	private int cp;

	@JoinColumn(name = "cve_ciu")
	@ManyToOne(fetch = FetchType.LAZY)
	private Ciudad ciudad;

	@OneToMany(mappedBy = "codigoPostal")
	private List<Colonia> colonias;

	public CodigoPostal() {
		// TODO Auto-generated constructor stub
	}

	public List<Colonia> getColonias() {
		return colonias;
	}

	public CodigoPostal(int cp, Ciudad ciudad) {
		this.cp = cp;
		this.ciudad = ciudad;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "CodigoPostal [cp=" + cp + ", ciudad=" + ciudad + "]";
	}

}
