package constructoraMaven.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colonia")
public class Colonia {

	@Id
	@Column(name = "cve_col")
	private int cve;

	@Column(name = "nom_col")
	private String colonia;

	@Column(name = "tipoasen_col")
	private String asentamiento;

	@Column(name = "zona_col")
	private String zona;

	@JoinColumn(name = "cp_cp")
	@ManyToOne(fetch = FetchType.LAZY)
	private CodigoPostal codigoPostal;

	public Colonia() {
		// TODO Auto-generated constructor stub
	}

	public Colonia(int cve, String colonia, String asentamiento, String zona, CodigoPostal codigoPostal) {
		this.cve = cve;
		this.colonia = colonia;
		this.asentamiento = asentamiento;
		this.zona = zona;
		this.codigoPostal = codigoPostal;
	}

	public int getCve() {
		return cve;
	}

	public void setCve(int cve) {
		this.cve = cve;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getAsentamiento() {
		return asentamiento;
	}

	public void setAsentamiento(String asentamiento) {
		this.asentamiento = asentamiento;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public CodigoPostal getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(CodigoPostal codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "Colonia [cve=" + cve + ", colonia=" + colonia + ", asentamiento=" + asentamiento + ", zona=" + zona
				+ ", codigoPostal=" + codigoPostal + "]";
	}

}
