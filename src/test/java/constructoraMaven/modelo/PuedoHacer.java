package constructoraMaven.modelo;

import javax.persistence.CascadeType;
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
@Table(name = "puedohacer")
public class PuedoHacer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_pue")
	private Integer cve;

	@Column(name = "status_pue", columnDefinition = "set")
	private String status;

	@JoinColumn(name = "cve_act")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Actividad actividad;

	@JoinColumn(name = "cve_tracon")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TrabajadorContrato trabajadorContrato;

	public PuedoHacer() {
		// TODO Auto-generated constructor stub
	}

	public PuedoHacer(Integer cve, String status, Actividad actividad, TrabajadorContrato trabajadorContrato) {
		super();
		this.cve = cve;
		this.status = status;
		this.actividad = actividad;
		this.trabajadorContrato = trabajadorContrato;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public TrabajadorContrato getTrabajadorContrato() {
		return trabajadorContrato;
	}

	public void setTrabajadorContrato(TrabajadorContrato trabajadorContrato) {
		this.trabajadorContrato = trabajadorContrato;
	}

	@Override
	public String toString() {
		return "PuedoHacer [cve=" + cve + ", status=" + status + ", actividad=" + actividad + ", trabajadorContrato="
				+ trabajadorContrato + "]";
	}

}
