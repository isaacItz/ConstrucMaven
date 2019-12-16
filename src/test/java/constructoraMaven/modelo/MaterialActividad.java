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
@Table(name = "matact")
public class MaterialActividad {

	@Id
	@Column(name = "cve_matact")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cve;

	@Column(name = "cant_matact", columnDefinition = "decimal(5,2)")
	private Double cantidadMat;

	@JoinColumn(name = "cve_pro")
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;

	@JoinColumn(name = "cve_act")
	@ManyToOne(fetch = FetchType.LAZY)
	private Actividad actividad;

	public MaterialActividad() {
		// TODO Auto-generated constructor stub
	}

	public MaterialActividad(Integer cve, Double cantidadMat, Producto producto, Actividad actividad) {
		super();
		this.cve = cve;
		this.cantidadMat = cantidadMat;
		this.producto = producto;
		this.actividad = actividad;
	}

	public Integer getCve() {
		return cve;
	}

	public void setCve(Integer cve) {
		this.cve = cve;
	}

	public Double getCantidadMat() {
		return cantidadMat;
	}

	public void setCantidadMat(Double cantidadMat) {
		this.cantidadMat = cantidadMat;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Override
	public String toString() {
		return "MaterialActividad [cve=" + cve + ", cantidadMat=" + cantidadMat + ", producto=" + producto
				+ ", actividad=" + actividad + "]";
	}

}
