package constructoraMaven.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {

	public static final String H_FIJO = "Fijo";
	public static final String H_VARIABLE = "Variable";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_hor")
	private Integer cve;

	@Column(name = "fecha_hor")
	private LocalDate fecha;

	@Column(name = "tipo_hor", columnDefinition = "set")
	private String tipo;

	@JoinColumn(name = "cve_tracon")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TrabajadorContrato trabajadorContrato;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "horario")
	private List<DiaHora> diaHoras;

	public Horario() {
		diaHoras = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	public Horario(Integer cve, LocalDate fecha, String tipo, TrabajadorContrato trabajadorContrato) {
		this.cve = cve;
		this.fecha = fecha;
		this.tipo = tipo;
		this.trabajadorContrato = trabajadorContrato;
		diaHoras = new ArrayList<>();
	}

	public void añadirDiaHora(DiaHora diaHora) {
		if (!diaHoras.contains(diaHora)) {
			diaHoras.add(diaHora);
		}
	}

	public void quitarDiaHora(DiaHora diaHora) {
		diaHoras.remove(diaHora);
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public TrabajadorContrato getTrabajadorContrato() {
		return trabajadorContrato;
	}

	public void setTrabajadorContrato(TrabajadorContrato trabajadorContrato) {
		this.trabajadorContrato = trabajadorContrato;
	}

	@Override
	public String toString() {
		return "Horario [cve=" + cve + ", fecha=" + fecha + ", tipo=" + tipo + ", trabajadorContrato="
				+ trabajadorContrato + "]";
	}

}
