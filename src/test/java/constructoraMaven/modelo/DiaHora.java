package constructoraMaven.modelo;

import java.time.LocalTime;

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
@Table(name = "diahora")
public class DiaHora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_diahor")
	private Integer cve;

	@Column(name = "dia_diahor")
	private int dia;

	@Column(name = "he_diahor")
	private LocalTime horaEntrada;

	@Column(name = "hs_diahor")
	private LocalTime horaSalida;

	@JoinColumn(name = "cve_hor")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Horario horario;

	public DiaHora() {
		
	}

	public DiaHora(Integer cve, int dia, LocalTime horaEntrada, LocalTime horaSalida, Horario horario) {
		this.cve = cve;
		this.dia = dia;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.horario = horario;
	}

	
	
	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cve == null) ? 0 : cve.hashCode());
		result = prime * result + dia;
		result = prime * result + ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result + ((horaSalida == null) ? 0 : horaSalida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaHora other = (DiaHora) obj;
		if (cve == null) {
			if (other.cve != null)
				return false;
		} else if (!cve.equals(other.cve))
			return false;
		if (dia != other.dia)
			return false;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSalida == null) {
			if (other.horaSalida != null)
				return false;
		} else if (!horaSalida.equals(other.horaSalida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiaHora [cve=" + cve + ", dia=" + dia + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida
				+ ", horario=" + horario + "]";
	}

}
