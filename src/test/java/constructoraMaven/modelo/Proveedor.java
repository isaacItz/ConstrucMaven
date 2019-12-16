package constructoraMaven.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cve_prov")
	private Integer cve;

	@Column(name = "fecha_prov")
	private LocalDate fecha;

	@Column(name = "empresa_prov")
	private String empresa;

	public Proveedor() {
		// TODO Auto-generated constructor stub
	}

	public Proveedor(Integer cve, LocalDate fecha, String empresa) {
		super();
		this.cve = cve;
		this.fecha = fecha;
		this.empresa = empresa;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Proveedor [cve=" + cve + ", fecha=" + fecha + ", empresa=" + empresa + "]";
	}

}
