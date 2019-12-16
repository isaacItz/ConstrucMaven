package constructoraMaven.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@Column(name = "cve_est")
	private int cve;

	@Column(name = "nom_est")
	private String nombre;

	public Estado() {
	}

	public Estado(int cve, String nombre) {
		this.cve = cve;
		this.nombre = nombre;
	}

	public int getCve() {
		return cve;
	}

	public void setCve(int cve) {
		this.cve = cve;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Estado [cve=" + cve + ", nombre=" + nombre + "]";
	}

}
