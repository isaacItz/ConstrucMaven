package constructoraMaven.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
@Table(name = "trabajadorcon")
@NamedQuery(query = "from Horario h where h.trabajadorContrato.cve = :cve", name = "horarios emp")
public class TrabajadorContrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_tracon")
	private Integer cve;

	@Column(name = "fi_tracon")
	private LocalDate fechaInicio;

	@Column(name = "ff_tracon")
	private LocalDate fechaFin;

	@Column(name = "puesto_tracon", columnDefinition = "set")
	private String puesto;

	@Column(name = "sueldo_tracon")
	private Double sueldo;

	@Column(name = "nss_tracon")
	private Integer nss;

	@JoinColumn(name = "cve_per")
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Persona persona;

	public TrabajadorContrato() {
		// TODO Auto-generated constructor stub
	}

	public TrabajadorContrato(Integer cve, LocalDate fechaInicio, LocalDate fechaFin, String puesto, Double sueldo,
			int nss, Persona p) {
		super();
		this.cve = cve;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.puesto = puesto;
		this.sueldo = sueldo;
		this.nss = nss;
		this.persona = p;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public Integer getNss() {
		return nss;
	}

	public void setNss(Integer nss) {
		this.nss = nss;
	}

	public Persona getP() {
		return persona;
	}

	public void setP(Persona p) {
		this.persona = p;
	}

	@Override
	public String toString() {
		return "TrabajadorContrato [cve=" + cve + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", puesto=" + puesto + ", sueldo=" + sueldo + ", nss=" + nss + ", persona=" + persona + "]";
	}

}