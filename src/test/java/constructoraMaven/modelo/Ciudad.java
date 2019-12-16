package constructoraMaven.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ciudad")
public class Ciudad {

	@Id
	@Column(name = "cve_ciu")
	private int cve;

	@Column(name = "mun_ciu")
	private String nombre;

	@JoinColumn(name = "cve_est")
	@ManyToOne(fetch = FetchType.LAZY)
	private Estado estado;

	public Ciudad() {
	}

	public Ciudad(int cve, String nombre, Estado estado) {
		this.cve = cve;
		this.nombre = nombre;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Ciudad [cve=" + cve + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

}
