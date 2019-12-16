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
@Table(name = "pagoact")
public class PagoActividad {
	@Id
	@Column(name = "cve_pagact")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cve;

	@Column(name = "fecha_pagact")
	private LocalDate fecha;

	@Column(name = "monto_pagact")
	private double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cve_act")
	private Actividad actividad;

	public PagoActividad() {

	}

	public PagoActividad(Integer cve, LocalDate fecha, double monto, Actividad actividad) {
		this.cve = cve;
		this.fecha = fecha;
		this.monto = monto;
		this.actividad = actividad;
	}

	public Integer getCveCobroAct() {
		return cve;
	}

	public void setCveCobroAct(Integer cveCobroAct) {
		this.cve = cveCobroAct;
	}

	public LocalDate getFechaCobroAct() {
		return fecha;
	}

	public void setFechaCobroAct(LocalDate fechaCobroAct) {
		this.fecha = fechaCobroAct;
	}

	public double getMontoCobroAct() {
		return monto;
	}

	public void setMontoCobroAct(double montoCobroAct) {
		this.monto = montoCobroAct;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Override
	public String toString() {
		return "PagoActividad [cve=" + cve + ", fecha=" + fecha + ", monto=" + monto + ", actividad=" + actividad + "]";
	}

}
