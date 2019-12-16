package constructoraMaven.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "direccionpersona")
@NamedQuery(query = "select d from DireccionPersona dp join dp.direccion d where dp.cve = :cve order by dp.fecha", name = "ultima direccion persona")
public class DireccionPersona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_dirper")
	private Integer cve;

	@Column(name = "fecha_dirper")
	private LocalDate fecha;

	@JoinColumn(name = "cve_dir")
	@ManyToOne(fetch = FetchType.LAZY)
	private Direccion direccion;

	@JoinColumn(name = "cve_per")
	@ManyToOne(fetch = FetchType.LAZY)
	private Persona persona;

	public DireccionPersona() {
		// TODO Auto-generated constructor stub
	}

	public DireccionPersona(Integer cve, LocalDate fecha, Direccion direccion, Persona persona) {
		super();
		this.cve = cve;
		this.fecha = fecha;
		this.direccion = direccion;
		this.persona = persona;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(int cve) {
		this.cve = cve;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "DireccionPersona [cve=" + cve + ", fecha=" + fecha + ", direccion=" + direccion + ", persona=" + persona
				+ "]";
	}

}