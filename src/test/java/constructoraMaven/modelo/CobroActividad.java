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
import javax.persistence.Table;

@Entity
@Table(name = "cobroact")
public class CobroActividad {

	@Id
	@Column(name = "cve_cobact")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cveCobroAct;

	@Column(name = "fecha_cobact")
	private LocalDate fechaCobroAct;

	@Column(name = "monto_cobact")
	private double montoCobroAct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cve_act")
	private Actividad actividad;

	public CobroActividad() {

	}

	public CobroActividad(Integer cveCobroAct, LocalDate fechaCobroAct, double montoCobroAct, Actividad actividad) {
		this.cveCobroAct = cveCobroAct;
		this.fechaCobroAct = fechaCobroAct;
		this.montoCobroAct = montoCobroAct;
		this.actividad = actividad;
	}

	public Integer getCveCobroAct() {
		return cveCobroAct;
	}

	public void setCveCobroAct(Integer cveCobroAct) {
		this.cveCobroAct = cveCobroAct;
	}

	public LocalDate getFechaCobroAct() {
		return fechaCobroAct;
	}

	public void setFechaCobroAct(LocalDate fechaCobroAct) {
		this.fechaCobroAct = fechaCobroAct;
	}

	public double getMontoCobroAct() {
		return montoCobroAct;
	}

	public void setMontoCobroAct(double montoCobroAct) {
		this.montoCobroAct = montoCobroAct;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Override
	public String toString() {
		return "CobroActividad [cveCobroAct=" + cveCobroAct + ", fechaCobroAct=" + fechaCobroAct + ", montoCobroAct="
				+ montoCobroAct + ", actividad=" + actividad + "]";
	}

}
