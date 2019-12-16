package constructoraMaven.modelo;

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
@Table(name = "direccion")
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_dir")
	private Integer cve;

	@Column(name = "calle_dir")
	private String calle;

	@Column(name = "tipovia_dir", columnDefinition = "set")
	private String tipoVia;

	@Column(name = "nume_dir")
	private Integer numero;

	@Column(name = "entrecalles")
	private String entreCalles;

	@Column(name = "orient_dir", columnDefinition = "set")
	private String orientacion;

	@Column(name = "referen_dir ")
	private String referencias;

	@JoinColumn(name = "cve_col")
	@ManyToOne(fetch = FetchType.LAZY)
	private Colonia colonia;

	public Direccion() {
		// TODO Auto-generated constructor stub
	}

	public Direccion(Integer cve, String calle, String tipoVia, Integer numero, String entreCalles, String orientacion,
			String referencias, Colonia colonia) {
		super();
		this.cve = cve;
		this.calle = calle;
		this.tipoVia = tipoVia;
		this.numero = numero;
		this.entreCalles = entreCalles;
		this.orientacion = orientacion;
		this.referencias = referencias;
		this.colonia = colonia;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getEntreCalles() {
		return entreCalles;
	}

	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public Colonia getColonia() {
		return colonia;
	}

	public void setColonia(Colonia colonia) {
		this.colonia = colonia;
	}

	@Override
	public String toString() {
		return "Direccion [cve=" + cve + ", calle=" + calle + ", tipoVia=" + tipoVia + ", numero=" + numero
				+ ", entreCalles=" + entreCalles + ", orientacion=" + orientacion + ", referencias=" + referencias
				+ ", colonia=" + colonia + "]";
	}

}
